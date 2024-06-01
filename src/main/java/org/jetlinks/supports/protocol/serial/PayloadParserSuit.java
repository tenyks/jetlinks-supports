package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;
import reactor.util.function.Tuple2;

import java.io.IOException;

/**
 * 一套负载解析器，用于将多个负载解析器组合使用
 *
 * @author Maxwell.Lee
 * @version 1.0
 * @since 1.2
 */
public interface PayloadParserSuit {

    PayloadParserSuit add(String uriOrTopic, PayloadParser parser);

    /**
     * 添加特征码判别提示（用于加速解析器的匹配）
     * @param parser                目标解析器，（必要）；
     * @param extractor             特征码抽取器，（必要）；
     * @param expectAttrCode        期望的特征码，（必要）；
     */
    void addInspectHint(PayloadParser parser, AttributeCodeExtractor extractor, Integer expectAttrCode);

    /**
     * 将字节串解析为字段组合的对象
     * @param uriOrTopic    请求的URI或topic，（必要）
     * @param payload       负载的字节串，可能为二进制、HEX字符串、BASE64字符串或JSON字符串
     * @return  解析结果和解析器的Tuple，如果没有适用的解析器返回空值
     */
    Tuple2<JSONObject, PayloadParser> parse(String uriOrTopic, byte[] payload) throws IOException;

}
