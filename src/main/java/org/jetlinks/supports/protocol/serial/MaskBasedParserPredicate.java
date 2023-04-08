package org.jetlinks.supports.protocol.serial;

import org.jetlinks.core.utils.BytesUtils;

/**
 * 基于掩码的判别器
 */
public class MaskBasedParserPredicate implements ParserPredicate {

    private PayloadParser   parser;

    /**
     * 掩码
     */
    private int mask;

    /**
     * 负载偏移位置
     */
    private int offset;

    /**
     * 负载抽取长度，如抽取长度不足4字节，在前补0
     */
    private int length;

    /**
     * 对负载进行掩码后的期望值
     */
    private int expected;

    public MaskBasedParserPredicate(PayloadParser parser, int mask, int offset, int length, int expected) {
        this.parser = parser;
        this.mask = mask;
        this.offset = offset;
        this.length = length;
        this.expected = expected;
    }

    @Override
    public boolean match(String uriOrTopic, byte[] payload) {
        int extracted = BytesUtils.beToInt(payload, offset, length);
        int real = extracted & mask;

        return real == expected;
    }

    @Override
    public void onRegistered(PayloadParserRegistry registry) {
        registry.addMaskInspectHint(parser, mask, offset, length, expected);
    }
}
