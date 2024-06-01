package org.jetlinks.supports.protocol;

import org.jetlinks.core.message.DeviceMessage;
import org.jetlinks.core.message.codec.EncodedMessage;
import org.jetlinks.core.route.Route;
import org.jetlinks.core.route.UpstreamRoutePredict;
import org.jetlinks.supports.protocol.codec.MessageCodecDeclaration;
import org.jetlinks.supports.protocol.codec.MessageContentType;

import javax.annotation.Nonnull;

/**
 * @author v-lizy81
 * @date 2023/4/12 00:20
 */
public class SimpleMessageCodecDeclaration<R extends Route, E extends EncodedMessage> implements MessageCodecDeclaration<R, E> {

    private R route;

    private UpstreamRoutePredict<R, E>      upstreamRoutePredict;

    private Class<? extends DeviceMessage>  thingMessageType;

    private MessageContentType              payloadContentType;

    @Override
    public R getRoute() {
        return route;
    }

    @Override
    public UpstreamRoutePredict<R, E> getUpstreamRoutePredict() {
        return upstreamRoutePredict;
    }

    @Override
    public Class<? extends DeviceMessage> getThingMessageType() {
        return thingMessageType;
    }

    @Nonnull
    @Override
    public MessageContentType getPayloadContentType() {
        return payloadContentType;
    }

    public SimpleMessageCodecDeclaration<R, E>    route(R route) {
        this.route = route;
        return this;
    }

    public SimpleMessageCodecDeclaration<R, E>    upstreamRoutePredict(UpstreamRoutePredict<R, E> routePredict) {
        this.upstreamRoutePredict = routePredict;
        return this;
    }

    public SimpleMessageCodecDeclaration<R, E>    thingMessageType(Class<? extends DeviceMessage> thingMessageType) {
        this.thingMessageType = thingMessageType;
        return this;
    }

    public SimpleMessageCodecDeclaration<R, E>    payloadContentType(MessageContentType type) {
        this.payloadContentType = type;
        return this;
    }

    @Override
    public String toString() {
        return "SimpleMessageCodecDeclaration{" +
                "route=" + route +
                ", upstreamRoutePredict=" + upstreamRoutePredict +
                ", thingMessageType=" + thingMessageType +
                ", payloadContentType=" + payloadContentType +
                '}';
    }
}
