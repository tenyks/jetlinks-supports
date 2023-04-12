package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DefaultPayloadWriterSuit implements PayloadWriterSuit {

    private Map<String, PayloadWriter> writerIdx;

    public DefaultPayloadWriterSuit() {
        this.writerIdx = new HashMap<>();
    }

    @Override
    public PayloadWriterSuit add(String topicOrMsgType, PayloadWriter writer) {
        writerIdx.put(topicOrMsgType, writer);
        return this;
    }

    @Override
    public byte[] buildPayload(String topicOrMsgType, JSONObject message) {
        PayloadWriter writer = writerIdx.get(topicOrMsgType);
        if (writer == null) return null;



        return new byte[0];
    }
}
