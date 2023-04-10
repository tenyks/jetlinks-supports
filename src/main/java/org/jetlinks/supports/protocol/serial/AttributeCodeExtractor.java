package org.jetlinks.supports.protocol.serial;

public interface AttributeCodeExtractor {

    String extract(byte[] payload);

    boolean isAffine(AttributeCodeExtractor other);

}
