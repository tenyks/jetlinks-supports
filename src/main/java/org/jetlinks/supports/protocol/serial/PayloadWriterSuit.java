package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;

/**
 * @author v-lizy81
 * @date 2023/4/12 00:02
 */
public interface PayloadWriterSuit {

    byte[] buildPayload(String messageType, JSONObject message);

}
