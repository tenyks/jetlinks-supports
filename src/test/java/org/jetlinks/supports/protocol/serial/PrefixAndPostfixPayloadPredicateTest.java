package org.jetlinks.supports.protocol.serial;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class PrefixAndPostfixPayloadPredicateTest {

    @Test
    public void matchPrefixAndPostfix() {
        PrefixAndPostfixPayloadPredicate predicate = new PrefixAndPostfixPayloadPredicate("{".getBytes(StandardCharsets.UTF_8), "}".getBytes(StandardCharsets.UTF_8), false);

        Assert.assertFalse(predicate.match("", "".getBytes(StandardCharsets.UTF_8)));
        Assert.assertFalse(predicate.match("", "HELLO".getBytes(StandardCharsets.UTF_8)));

        Assert.assertTrue(predicate.match("", "{}".getBytes(StandardCharsets.UTF_8)));
        Assert.assertTrue(predicate.match("", "{\"HELLO\":1}".getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    public void matchPrefix() {
        PrefixAndPostfixPayloadPredicate predicate = new PrefixAndPostfixPayloadPredicate("{".getBytes(StandardCharsets.UTF_8), null, false);

        Assert.assertFalse(predicate.match("", "".getBytes(StandardCharsets.UTF_8)));
        Assert.assertFalse(predicate.match("", "HELLO".getBytes(StandardCharsets.UTF_8)));

        Assert.assertTrue(predicate.match("", "{}".getBytes(StandardCharsets.UTF_8)));
        Assert.assertTrue(predicate.match("", "{\"HELLO\":1}".getBytes(StandardCharsets.UTF_8)));

    }
}