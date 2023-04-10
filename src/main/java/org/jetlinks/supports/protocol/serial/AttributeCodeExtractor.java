package org.jetlinks.supports.protocol.serial;

public interface AttributeCodeExtractor {

    int extract(byte[] payload);

    boolean isAffine(AttributeCodeExtractor other);

}
