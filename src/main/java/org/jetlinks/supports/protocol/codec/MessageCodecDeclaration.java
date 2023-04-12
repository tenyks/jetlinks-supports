package org.jetlinks.supports.protocol.codec;

import com.alibaba.fastjson.JSONObject;
import org.jetlinks.core.message.DeviceMessage;
import org.jetlinks.core.message.codec.EncodedMessage;
import org.jetlinks.core.route.LwM2MRoute;
import org.jetlinks.core.route.Route;
import org.jetlinks.core.route.UpstreamRoutePredict;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.Null;

/**
 * 消息编解码声明
 *
 * @author v-lizy81
 * @date 2023/4/11 23:45
 */
public interface MessageCodecDeclaration<R extends Route, E extends EncodedMessage> {

    @Nonnull
    R   getRoute();

    @Nullable
    UpstreamRoutePredict<R, E> getUpstreamRoutePredict();

    @Nonnull
    Class<? extends DeviceMessage>  getThingMessageType();

    /**
     * 便捷方法
     * @see UpstreamRoutePredict#isAcceptable(org.jetlinks.core.route.Route, org.jetlinks.core.message.codec.EncodedMessage, com.alibaba.fastjson.JSONObject)
     */
    default boolean isRouteAcceptable(E message, JSONObject parsedMsg) {
        UpstreamRoutePredict<R, E> upstreamRoutePredict = getUpstreamRoutePredict();
        if (upstreamRoutePredict == null) return false;

        return upstreamRoutePredict.isAcceptable(getRoute(), message, parsedMsg);
    }

    /**
     * 便捷方法，创建物模型消息实例
     * @return  相应物模型消息类型的实例
     */
    default DeviceMessage createThingMessage() throws IllegalAccessException, InstantiationException {
        return getThingMessageType().newInstance();
    }

    default String  getMessageType() {
        R route = getRoute();
        if (route instanceof LwM2MRoute) {
            return ((LwM2MRoute) route).getMessageType();
        }

        return null;
    }
}
