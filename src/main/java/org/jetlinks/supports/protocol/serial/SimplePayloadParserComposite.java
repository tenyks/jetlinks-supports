package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;
import reactor.util.function.Tuple2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过解析器的亲缘性合并解析判断计算
 */
public class SimplePayloadParserComposite implements PayloadParserComposite {

    private Map<String, List<PayloadParser>>    parserIdx;

    public SimplePayloadParserComposite() {
        parserIdx = new HashMap<>();
    }

    @Override
    public void add(String uriOrTopic, PayloadParser parser) {

    }

    @Override
    public void addInspectHint(PayloadParser parser, AttributeCodeExtractor extractor, String expectAttrCode) {

    }

    @Override
    public Tuple2<JSONObject, PayloadParser> parse(String uriOrTopic, byte[] payload) {

        return null;
    }
}
