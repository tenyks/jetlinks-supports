package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private PayloadParser defaultParser;

    private List<Tuple2<AttributeCodeExtractor, Map<Integer, PayloadParser>>> orderPath;

    public DefaultPayloadParserSuit() {
        parserMap = new HashMap<>();
        orderPath = new ArrayList<>();
    }

    public static DefaultPayloadParserSuit ofJson(ObjectMapper mapper) {
        return new DefaultPayloadParserSuit().add("*", new JsonPayloadParser(mapper));
    }

    @Override
    public DefaultPayloadParserSuit add(String uriOrTopic, PayloadParser parser) {
        if (uriOrTopic == null || parser == null) {
            throw new IllegalArgumentException("参数不全。[0x30DPPS3765]");
        }
        if (uriOrTopic.equals("*") || uriOrTopic.equals("/*")) {
            this.defaultParser = parser;
            return this;
        }

        List<PayloadParser> list = parserMap.computeIfAbsent(uriOrTopic, (k) -> new ArrayList<>());
        list.add(parser);

        parser.getPredicate().onComposited(this);

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
        if (uriOrTopic == null || payload == null) {
            throw new IllegalArgumentException("参数不全。[0x30DPPS6665]");
        }

        if (orderPath.isEmpty()) {
            //无特征码加速的情况

            List<PayloadParser> candidateParsers = parserMap.get(uriOrTopic);

            PayloadParser matchedParser = null;
            if (candidateParsers == null || candidateParsers.isEmpty()) {
                if (defaultParser!= null && defaultParser.getPredicate().match(uriOrTopic, payload)) {
                    matchedParser = defaultParser;
                }
            } else {
                //效率最慢的方式：逐个遍历解析器，并判断是否适用
                for (PayloadParser parser: candidateParsers) {
                    if (parser.getPredicate().match(uriOrTopic, payload)) {
                        matchedParser = parser;
                    }
                }
            }
            if (matchedParser == null) return null;

            JSONObject obj = matchedParser.parse(uriOrTopic, payload);
            return Tuples.of(obj, matchedParser);
        }

        // 有特征码加速的情况
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
