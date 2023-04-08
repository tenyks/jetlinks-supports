package org.jetlinks.supports.protocol.serial;

/**
 * （一种消息的）字段为单位的负载Writer
 *
 * @author Maxwell.Lee
 * @version 1.0
 * @since 1.2
 */
public interface PayloadWriter {
    /**
     * 将字段压入负载<br>
     * <strong>注意：如底层负载无固定结构，先写入的出现在负载流的前</strong>
     *
     * @param fieldCode     字段编码，（必要）；
     * @param fieldValue    字段的值，（必要）；
     * @return  返回本对象，方便链式写法
     */
    PayloadWriter write(String fieldCode, Object fieldValue);

    /**
     * @return  返回负载的字节流
     */
    byte[]  build();
}
