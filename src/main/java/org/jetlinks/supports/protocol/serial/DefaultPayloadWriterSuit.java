package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DefaultPayloadWriterSuit implements PayloadWriterSuit {

    private Map<String, PayloadWriter> writerIdx;

    private PayloadWriter   defaultWriter;

    public DefaultPayloadWriterSuit() {
        this.writerIdx = new HashMap<>();
    }

    @Override
    public PayloadWriterSuit add(String topicOrMsgType, PayloadWriter writer) {
        if (topicOrMsgType == null || writer == null) {
            throw new IllegalArgumentException("参数不全。[0x30DPWS2165]");
        }

        if (topicOrMsgType.equals("*") || topicOrMsgType.equals("/*")) {
            defaultWriter = writer;
        } else {
            writerIdx.put(topicOrMsgType, writer);
        }
        return this;
    }

    @Override
    public byte[] buildPayload(String topicOrMsgType, JSONObject message) throws IOException {
        if (topicOrMsgType == null || message == null) {
            throw new IllegalArgumentException("参数不全。[0x30DPWS3165]");
        }

        PayloadWriter writer = writerIdx.get(topicOrMsgType);
        if (writer == null) {
            writer = defaultWriter;
            if (writer == null) {
                return null;
            }
        }

        return writer.writeAndBuild(message);
    }
}
