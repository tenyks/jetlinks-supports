package org.jetlinks.supports.protocol.codec;

/**
 * 用于表示消息负载的内容类型
 *
 * @author v-lizy81
 * @version 1.0.0
 * @date 2024/6/1
 * @since V3.1.0
 */
public enum MessageContentType {
    TLV("TLV", "TLV字节串"),
    JSON("JSON", "JSON字符串"),
    STRUCT("STRUCT", "StructLike字节串")
    ;

    private final String    code;

    private final String    name;

    MessageContentType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
