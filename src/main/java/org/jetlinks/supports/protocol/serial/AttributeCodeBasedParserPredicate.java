package org.jetlinks.supports.protocol.serial;

/**
 * 基于特征码的判别器
 */
public class AttributeCodeBasedParserPredicate extends AbstractParserPredicate {

    private AttributeCodeExtractor  extractor;

    /**
     * 对负载进行掩码后的期望值
     */
    private Integer expectAttrCode;

    public AttributeCodeBasedParserPredicate(PayloadParser parser, AttributeCodeExtractor extractor, Integer expectAttrCode) {
        super(parser);

        this.extractor = extractor;
        this.expectAttrCode = expectAttrCode;
    }

    @Override
    public boolean match(String uriOrTopic, byte[] payload) {
        Integer extracted = extractor.extract(payload);

        return expectAttrCode.equals(extracted);
    }

    @Override
    public void onComposited(PayloadParserSuit composite) {
        composite.addInspectHint(getParser(), extractor, expectAttrCode);
    }

    public AttributeCodeExtractor getAttributeCodeExtractor() {
        return extractor;
    }

    public Integer getExpectAttributeCode() {
        return expectAttrCode;
    }
}
