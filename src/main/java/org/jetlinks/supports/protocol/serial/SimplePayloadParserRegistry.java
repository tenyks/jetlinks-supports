package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;
import reactor.util.function.Tuple2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimplePayloadParserRegistry implements PayloadParserRegistry {

    private Map<String, List<PayloadParser>>    parserIdx;

    public SimplePayloadParserRegistry() {
        parserIdx = new HashMap<>();
    }

    @Override
    public void registry(String uriOrTopic, PayloadParser parser) {

    }

    @Override
    public void addMaskInspectHint(PayloadParser parser, int mask, int offset, int length, int expected) {

    }

    @Override
    public Tuple2<JSONObject, PayloadParser> parse(String uriOrTopic, byte[] payload) {
        return null;
    }
}
