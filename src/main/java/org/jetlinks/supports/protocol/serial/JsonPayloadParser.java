package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonPayloadParser implements PayloadParser {

    private final ObjectMapper    mapper;

    private final ParserPredicate   predicate;

    public JsonPayloadParser(ObjectMapper mapper) {
        if (mapper == null) {
            throw new IllegalArgumentException("参数不全：缺少mapper参数[0x21JPP1258]");
        }
        this.mapper = mapper;
        this.predicate = new PrefixAndPostfixPayloadPredicate(this, "{".getBytes(StandardCharsets.UTF_8),
                "}".getBytes(StandardCharsets.UTF_8), false);
    }

    @Override
    public ParserPredicate getPredicate() {
        return predicate;
    }

    @Override
    public JSONObject parse(String uriOrTopic, byte[] payload) throws IOException {
        return mapper.readValue(payload, JSONObject.class);
    }
}
