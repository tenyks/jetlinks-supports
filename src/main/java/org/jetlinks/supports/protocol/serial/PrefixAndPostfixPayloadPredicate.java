package org.jetlinks.supports.protocol.serial;

import org.jetlinks.core.utils.BytesUtils;

/**
 * @author 李仲允
 * @date 2023/4/10 22:14
 */
public class PrefixAndPostfixPayloadPredicate extends AbstractParserPredicate {

    private final byte[] prefix;

    private final byte[] postfix;

    private final boolean ignoreBlank;

    /**
     * @param prefix      前缀（可空，表示不检查）
     * @param postfix     后缀（可空，表示不检查）
     * @param ignoreBlank 是否忽略开头和结尾的空字符
     */
    public PrefixAndPostfixPayloadPredicate(PayloadParser parser, byte[] prefix, byte[] postfix, boolean ignoreBlank) {
        super(parser);
        this.prefix = prefix;
        this.postfix = postfix;
        this.ignoreBlank = ignoreBlank;
    }

    @Override
    public boolean match(String uriOrTopic, byte[] payload) {
        boolean flag;
        if (prefix != null) {
            flag = BytesUtils.startsWith(payload, prefix);
            if (!flag) return false;
        }
        if (postfix != null) {
            flag = BytesUtils.endsWith(payload, postfix);
            if (!flag) return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "PrefixAndPostfixPayloadPredicate{" +
                "prefix='" + prefix + '\'' +
                ", postfix='" + postfix + '\'' +
                ", ignoreBlank=" + ignoreBlank +
                '}';
    }
}
