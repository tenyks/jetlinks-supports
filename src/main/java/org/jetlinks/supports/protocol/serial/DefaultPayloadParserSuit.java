package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过解析器的亲缘性合并解析判断计算
 */
public class DefaultPayloadParserSuit implements PayloadParserSuit {

    private Map<String, List<PayloadParser>> parserMap;

    private List<Tuple2<AttributeCodeExtractor, Map<Integer, PayloadParser>>> orderPath;

    public DefaultPayloadParserSuit() {
        parserMap = new HashMap<>();
        orderPath = new ArrayList<>();
    }

    @Override
    public PayloadParserSuit add(String uriOrTopic, PayloadParser parser) {
        List<PayloadParser> list = parserMap.computeIfAbsent(uriOrTopic, (k) -> new ArrayList<>());
        list.add(parser);
        return this;
    }

    @Override
    public void addInspectHint(PayloadParser parser, AttributeCodeExtractor extractor, Integer expectAttrCode) {
        for (Tuple2<AttributeCodeExtractor, Map<Integer, PayloadParser>> item : orderPath) {
            if (item.getT1().isAffine(extractor)) {
                item.getT2().put(expectAttrCode, parser);
                return ;
            }
        }

        Map<Integer, PayloadParser> map = new HashMap<>();
        map.put(expectAttrCode, parser);
        orderPath.add(Tuples.of(extractor, map));
    }

    @Override
    public Tuple2<JSONObject, PayloadParser> parse(String uriOrTopic, byte[] payload) throws IOException {
        for (Tuple2<AttributeCodeExtractor, Map<Integer, PayloadParser>> item : orderPath) {
            Integer attrCode = item.getT1().extract(payload);

            PayloadParser parser = item.getT2().get(attrCode);
            if (parser != null) {
                JSONObject obj = parser.parse(uriOrTopic, payload);

                return Tuples.of(obj, parser);
            }
        }

        return null;
    }
}
