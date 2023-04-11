package org.jetlinks.supports.protocol.serial;

public interface AttributeCodeExtractor {

    Integer extract(byte[] payload);

    boolean isAffine(AttributeCodeExtractor other);

}
