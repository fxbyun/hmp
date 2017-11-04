package com.qiaobei.hmp.modules.webSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by IntelliJ IDEA 2016.4
 * IDEA汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 *
 * @author 凉生
 *         Date 2017/1/17 0017.
 *         Time 11:14.
 */

public class WebsocketEndPoint implements WebSocketConfigurer {
    final
    WebSocketHandlerImpl webSocketHandler;
    final
    HandshakeInterceptor handshakeInterceptor;

    @Autowired
    public WebsocketEndPoint(WebSocketHandlerImpl webSocketHandler, HandshakeInterceptor handshakeInterceptor) {
        this.webSocketHandler = webSocketHandler;
        this.handshakeInterceptor = handshakeInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/logs.do").setAllowedOrigins("*").addInterceptors(handshakeInterceptor);
    }
}
