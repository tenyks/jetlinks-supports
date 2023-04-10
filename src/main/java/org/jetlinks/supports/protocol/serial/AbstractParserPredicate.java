package org.jetlinks.supports.protocol.serial;

/**
 * @author 李仲允
 * @date 2023/4/10 23:51
 */
public abstract class AbstractParserPredicate implements ParserPredicate {

    private final PayloadParser     parser;

    protected AbstractParserPredicate(PayloadParser parser) {
        this.parser = parser;
    }

    @Override
    public PayloadParser getParser() {
        return null;
    }
}
