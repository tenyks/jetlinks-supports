package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * （一种消息的）字段为单位的负载Writer
 *
 * @author Maxwell.Lee
 * @version 1.0
 * @since 1.2
 */
public interface PayloadWriter {
    /**
     * 将字段压入负载<br>
     * <strong>注意：如底层负载无固定结构，先写入的出现在负载流的前</strong>
     *
     * @param fieldCode     字段编码，（必要）；
     * @param fieldValue    字段的值，（必要）；
     * @return  返回本对象，方便链式写法
     */
    PayloadWriter write(String fieldCode, Object fieldValue);

    /**
     * 便捷方法
     */
    default PayloadWriter write(JSONObject message) {
        for (String key : message.keySet()) {
            Object val = message.get(key);

            write(key, val);
        }

        return this;
    }

    /**
     * 便捷方法
     */
    default byte[] writeAndBuild(JSONObject message) throws IOException {
        return this.write(message).build();
    }

    /**
     * @return  返回负载的字节流
     */
    byte[]  build() throws IOException;
}
