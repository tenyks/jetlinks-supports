package org.jetlinks.supports.protocol.codec;

import org.jetlinks.core.message.DeviceMessage;
import org.jetlinks.core.message.codec.EncodedMessage;
import org.jetlinks.core.route.Route;
import org.jetlinks.core.route.RoutePredict;

/**
 * 消息编解码声明
 *
 * @author v-lizy81
 * @date 2023/4/11 23:45
 */
public interface MessageCodecDeclaration<R extends Route, E extends EncodedMessage> {

    R   getRoute();

    RoutePredict<R, E>    getRoutePredict();

    Class<? extends DeviceMessage>  getThingMessageType();
}
