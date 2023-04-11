package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;

/**
 * JSON格式的负载Writer
 */
public class JsonPayloadWriter implements PayloadWriter {

    private final ObjectMapper  mapper;

    private final JSONObject  buf;

    public JsonPayloadWriter(ObjectMapper mapper) {
        this.mapper = mapper;
        this.buf = new JSONObject();
    }

    @Override
    public PayloadWriter write(String fieldCode, Object fieldValue) {
        buf.put(fieldCode, fieldValue);
        return this;
    }

    @Override
    public byte[] build() throws JsonProcessingException {
        String jsonStr = mapper.writeValueAsString(buf);
        return jsonStr.getBytes(StandardCharsets.UTF_8);
    }
}
