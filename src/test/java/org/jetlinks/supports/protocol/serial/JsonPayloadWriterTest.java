package org.jetlinks.supports.protocol.serial;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class JsonPayloadWriterTest {

    @Test
    public void build() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(DateFormat.getDateTimeInstance());
        JsonPayloadWriter writer = new JsonPayloadWriter(mapper);

        writer.write("field1", "HELLO");
        writer.write("field2", 1);
        writer.write("field3", 2.022);
        writer.write("field4", 3.0123f);
        writer.write("field5", new Date(1681204822691L));
        writer.write("field6", 5L);

        byte[] buf = writer.build();
        String x = new String(buf, StandardCharsets.UTF_8);
        System.out.println(x);
        Assert.assertEquals("{\"field1\":\"HELLO\",\"field6\":5,\"field3\":2.022,\"field2\":1,\"field5\":\"2023-4-11 17:20:22\",\"field4\":3.0123}", x);
    }
}