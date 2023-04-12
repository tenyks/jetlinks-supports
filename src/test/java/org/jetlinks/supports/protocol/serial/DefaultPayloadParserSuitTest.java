package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import reactor.util.function.Tuple2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class DefaultPayloadParserSuitTest {

    @Test
    public void parse() throws IOException {
        ObjectMapper mapper = Jackson2ObjectMapperBuilder
                .json()
                .build()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        DefaultPayloadParserSuit suit = DefaultPayloadParserSuit.ofJson(mapper);

        String jsonStr = "{\"field1\":\"value1\",\"field2\":2,\"field3\":3,\"field4\":\"2023-04-08 00:00:00\",\"field5\":5.5,\"field6\":6.6}";
        Tuple2<JSONObject, PayloadParser> tuple = suit.parse("ReadPropertyMessage", jsonStr.getBytes(StandardCharsets.UTF_8));
        System.out.println(tuple.getT1());
        Assert.assertEquals(tuple.getT1().getString("field1"), "value1");
        Assert.assertEquals(tuple.getT1().getInteger("field2"), Integer.valueOf(2));
        Assert.assertEquals(tuple.getT1().getInteger("field3"), Integer.valueOf(3));
        Assert.assertEquals(tuple.getT1().getString("field4"), "2023-04-08 00:00:00");
        Assert.assertEquals(tuple.getT1().getFloat("field5"), Float.valueOf(5.5f));
        Assert.assertEquals(tuple.getT1().getFloat("field6"), Float.valueOf(6.6f));

        jsonStr = "{}";
        tuple = suit.parse("ReadPropertyMessage", jsonStr.getBytes(StandardCharsets.UTF_8));
        System.out.println(tuple.getT1());

        jsonStr = "";
        tuple = suit.parse("ReadPropertyMessage", jsonStr.getBytes(StandardCharsets.UTF_8));
        Assert.assertNull(tuple);
    }
}