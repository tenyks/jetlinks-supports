package org.jetlinks.supports.protocol.serial;

import javax.annotation.Nullable;

/**
 * 基于特征码的判别器
 */
public class AttributeCodeBasedParserPredicate implements ParserPredicate {

    private AttributeCodeExtractor  extractor;

    /**
     * 对负载进行掩码后的期望值
     */
    private int expected;

    public AttributeCodeBasedParserPredicate(AttributeCodeExtractor extractor, int expected) {
        this.extractor = extractor;
        this.expected = expected;
    }

    @Override
    public boolean match(String uriOrTopic, byte[] payload) {
        int extracted = extractor.extract(payload);

        return (extracted == expected);
    }

    @Nullable
    @Override
    public AttributeCodeExtractor getAttributeCodeExtractor() {
        return extractor;
    }
}
