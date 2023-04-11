package org.jetlinks.supports.protocol;

import org.jetlinks.core.message.DeviceMessage;
import org.jetlinks.core.message.codec.EncodedMessage;
import org.jetlinks.core.route.Route;
import org.jetlinks.core.route.RoutePredict;
import org.jetlinks.supports.protocol.codec.MessageCodecDeclaration;

/**
 * @author v-lizy81
 * @date 2023/4/12 00:20
 */
public class SimpleMessageCodecDeclaration<R extends Route, E extends EncodedMessage> implements MessageCodecDeclaration<R, E> {

    private R route;
    private RoutePredict<R, E> routePredict;
    private Class<? extends DeviceMessage> thingMessageType;

    @Override
    public R getRoute() {
        return route;
    }

    @Override
    public RoutePredict<R, E> getRoutePredict() {
        return routePredict;
    }

    @Override
    public Class<? extends DeviceMessage> getThingMessageType() {
        return thingMessageType;
    }

    public SimpleMessageCodecDeclaration<R, E>    route(R route) {
        this.route = route;
        return this;
    }

    public SimpleMessageCodecDeclaration<R, E>    routePredict(RoutePredict<R, E> routePredict) {
        this.routePredict = routePredict;
        return this;
    }

    public SimpleMessageCodecDeclaration<R, E>    thingMessageType(Class<? extends DeviceMessage> thingMessageType) {
        this.thingMessageType = thingMessageType;
        return this;
    }
}
