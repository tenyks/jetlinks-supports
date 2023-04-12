package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;

/**
 * 主题或消息负载Writer组合
 *
 * @author v-lizy81
 * @date 2023/4/12 00:02
 */
public interface PayloadWriterSuit {

    PayloadWriterSuit add(String topicOrMsgType, PayloadWriter writer);

    byte[] buildPayload(String topicOrMsgType, JSONObject message);

}
