package org.jetlinks.supports.protocol.serial;

import org.jetlinks.core.utils.BytesUtils;

/**
 * 基于掩码计算的特征码抽取器
 */
public class MaskBasedAttributeCodeExtractor implements AttributeCodeExtractor {

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

    public MaskBasedAttributeCodeExtractor(int mask, int offset, int length) {
        this.mask = mask;
        this.offset = offset;
        this.length = length;
    }

    @Override
    public int extract(byte[] payload) {
        int extracted = BytesUtils.beToInt(payload, offset, length);
        int real = extracted & mask;

        return (real);
    }

    @Override
    public boolean isAffine(AttributeCodeExtractor other) {
        if (other instanceof MaskBasedAttributeCodeExtractor) {
            MaskBasedAttributeCodeExtractor that = (MaskBasedAttributeCodeExtractor) other;
            return (this.mask == that.mask && this.offset == that.offset && this.length == that.length);
        }

        return false;
    }
}
