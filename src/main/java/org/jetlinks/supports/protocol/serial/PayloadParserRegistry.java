package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;
import reactor.util.function.Tuple2;

/**
 * 负载解析器Registry
 *
 * @author Maxwell.Lee
 * @version 1.0
 * @since 1.2
 */
public interface PayloadParserRegistry {

    void registry(String uriOrTopic, PayloadParser parser);

    /**
     * 添加掩码判别提示（用于加速解析器的匹配）
     * @param parser        目标解析器，（必要）；
     * @param mask          掩码，（必要）；
     * @param offset        负载偏移位置，（必要）；
     * @param length        负载抽取长度，（必要），如抽取长度不足4字节，在前补0；
     * @param expected      对负载进行掩码后的期望值，（必要）；
     */
    void addMaskInspectHint(PayloadParser parser, int mask, int offset, int length, int expected);

    /**
     * 将字节串解析为字段组合的对象
     * @param uriOrTopic    请求的URI或topic，（必要）
     * @param payload       负载的字节串，可能为二进制、HEX字符串、BASE64字符串或JSON字符串
     * @return  解析结果和解析器的Tuple，如果没有适用的解析器返回空值
     */
    Tuple2<JSONObject, PayloadParser> parse(String uriOrTopic, byte[] payload);
}
