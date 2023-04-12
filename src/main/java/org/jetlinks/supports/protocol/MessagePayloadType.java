package org.jetlinks.supports.protocol;

/**
 * （设备）消息负载类型
 */
public enum MessagePayloadType {
    JSON("JSON", "JSON字符串"),
    BINARY("BINARY", "二进制"),
    BINARY_HEX("BIN_HEX", "HEX字符串表示的二进制"),
    BINARY_BASE64("BIN_B64", "BASE64字符串表示的二进制")
    ;

    private String  code;

    private String  name;

    MessagePayloadType(String code, String name) {
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
