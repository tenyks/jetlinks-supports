package org.jetlinks.supports.protocol.codec;

import com.alibaba.fastjson.JSONObject;
import org.jetlinks.core.message.DeviceMessage;
import org.jetlinks.core.message.codec.EncodedMessage;
import org.jetlinks.core.route.DownstreamRoutePredict;
import org.jetlinks.core.route.Route;
import org.jetlinks.core.route.UpstreamRoutePredict;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 设备消息与物模型消息（映射）声明
 *
 * @author v-lizy81
 * @date 2023/4/11 23:45
 */
public interface MessageCodecDeclaration<R extends Route, E extends EncodedMessage> {

    @Nonnull
    R   getRoute();

    @Nullable
    UpstreamRoutePredict<R, E>                  getUpstreamRoutePredict();

    DownstreamRoutePredict<R, DeviceMessage>    getDownstreamRoutePredict();

    @Nonnull
    Class<? extends DeviceMessage>              getThingMessageType();

    @Nonnull
    MessageContentType                          getPayloadContentType();

    /**
     * 便捷方法
     * @see UpstreamRoutePredict#isAcceptable(org.jetlinks.core.route.Route, org.jetlinks.core.message.codec.EncodedMessage, com.alibaba.fastjson.JSONObject)
     */
    @Deprecated
    default boolean isRouteAcceptable(@Nonnull E message, @Nullable JSONObject parsedMsg) {
        return this.isRouteAcceptableUpstream(message, parsedMsg);
    }

    default boolean isRouteAcceptableUpstream(@Nonnull E message, @Nullable JSONObject parsedMsg) {
        UpstreamRoutePredict<R, E> predict = getUpstreamRoutePredict();
        if (predict == null) return false;

        return predict.isAcceptable(getRoute(), message, parsedMsg);
    }

    default boolean isRouteAcceptableDownload(@Nonnull DeviceMessage message) {
        DownstreamRoutePredict<R, DeviceMessage> predict = getDownstreamRoutePredict();
        if (predict == null) return false;

        return predict.isAcceptable(getRoute(), message);
    }

    /**
     * 便捷方法，创建物模型消息实例
     * @return  相应物模型消息类型的实例
     */
    default DeviceMessage createThingMessage() throws IllegalAccessException, InstantiationException {
        return getThingMessageType().newInstance();
    }

}
