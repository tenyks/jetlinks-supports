package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * TLV(Type-Length-Value)结构的字节流解析器
 */
public class TLVPayloadParser implements PayloadParser {

    /**
     * TAG字段长度（单位：字节）
     */
    private int     tagOctets;

    /**
     * LENGTH字段长度（单位：字节）
     */
    private int     lengthOctets;

    @Override
    public ParserPredicate getPredicate() {
        return null;
    }

    @Override
    public JSONObject parse(String uriOrTopic, byte[] payload) throws IOException {
        return null;
    }
}
