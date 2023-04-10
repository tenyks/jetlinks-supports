package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * 类C语言Struct结构的负载解析器，与华为云的可视化配置编解码插件类同
 */
public class StructLikePayloadParser implements PayloadParser {

    @Override
    public ParserPredicate getPredicate() {
        return null;
    }

    @Override
    public JSONObject parse(String uriOrTopic, byte[] payload) throws IOException {
        return null;
    }
}
