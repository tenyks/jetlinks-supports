package org.jetlinks.supports.protocol.serial;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaskBasedParserPredicateTest {

    @Test
    public void match() throws DecoderException {
        byte[] buf = Hex.decodeHex("0A0B0C0D0E0F");

        MaskBasedParserPredicate predicate = new MaskBasedParserPredicate(null, 0xFF00, 1, 2, 0x0B00);

        boolean rst = predicate.match("", buf);
        System.out.println("rst = " + rst);
        Assert.assertTrue(rst);

        buf = Hex.decodeHex("000A0B0C0D0E0F");
        rst = predicate.match("", buf);
        System.out.println("rst = " + rst);
        Assert.assertFalse(rst);
    }
}