package org.jetlinks.supports.protocol.serial;

/**
 * 解析器是否适用于目标负载的判别器
 *
 * @author Maxwell.Lee
 * @version 1.0
 * @since 1.2
 */
public interface ParserPredicate {

    /**
     * 根据输入判别当前解析器是否适用于当前的负载
     * @param uriOrTopic    目标URI或topic，（必要）
     * @param payload       负载的字节流，（必要）
     * @return  如果适用返回true，否则返回false
     */
    boolean match(String uriOrTopic, byte[] payload);

    /**
     * 当前解析器已注册到指定的Registry <br>
     * 可用于向Registry添加性能加速元信息
     * @param registry      解析器注册Registry，（必要）
     */
    void onRegistered(PayloadParserRegistry registry);

}
