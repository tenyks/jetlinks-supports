package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * （一种消息的）负载解析器
 *
 * @author Maxwell.Lee
 * @version 1.0
 * @since 1.2
 */
public interface PayloadParser {

    /**
     * @return  当前解析器的适用判别器
     */
    ParserPredicate getPredicate();

    /**
     * 将字节串解析为字段组合的对象
     * @param uriOrTopic    请求的URI或topic，（必要）
     * @param payload       负载的字节串，可能为二进制、HEX字符串、BASE64字符串或JSON字符串
     * @return  解析后的由字段组成的对象
     */
    JSONObject parse(String uriOrTopic, byte[] payload) throws IOException;

}
