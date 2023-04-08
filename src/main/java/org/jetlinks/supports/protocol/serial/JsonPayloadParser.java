package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonPayloadParser<T> implements PayloadParser {

    private ObjectMapper    mapper;

    public JsonPayloadParser(ObjectMapper mapper) {
        if (mapper == null) {
            throw new IllegalArgumentException("参数不全：缺少mapper参数。0x21JPP1258");
        }
        this.mapper = mapper;
    }

    @Override
    public JSONObject parse(String uriOrTopic, byte[] payload) throws IOException {
        return mapper.readValue(payload, JSONObject.class);
    }
}
