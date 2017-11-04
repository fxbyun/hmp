package org.springside.modules.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

/**
 * @author weiyang
 */
public class WebsocketUtil {

    private static final Logger log = LoggerFactory.getLogger(WebsocketUtil.class);

    /**
     * 向所有的session发送数据
     *
     * @param webSocketSessionMap websocketsession
     * @param pushData            需要push的数据的map对象,自动转为json
     */
    public static void sendAllSession(Map<String, WebSocketSession> webSocketSessionMap, Map<String, Object> pushData) {
        log.info("发送到数据到session,session条数[{}]", webSocketSessionMap.size());
        String jsonData = null;
        try {
            jsonData = new ObjectMapper().writeValueAsString(pushData);
        } catch (JsonProcessingException e) {
            log.error("json转换失败", e);
        }
        if (jsonData != null) {
            TextMessage pushDataTextMessage = new TextMessage(jsonData);

            webSocketSessionMap.forEach((s, webSocketSession) -> {
                try {
                    webSocketSession.sendMessage(pushDataTextMessage);
                    log.debug("session:" + webSocketSession.getId() + "推送成功");
                } catch (IOException e) {
                    log.error("session:" + webSocketSession.getId() + " 推送失败", e);
                }
            });
        }
    }

    public static void sendSession(WebSocketSession session, Map<String, Object> pushData) {
        if (session == null) {
            return;
        }
        if (!session.isOpen()) {
            // session已关闭
            return;
        }

        String jsonData = null;
        try {
            jsonData = new ObjectMapper().writeValueAsString(pushData);
        } catch (JsonProcessingException e) {
            log.error("json转换失败", e);
        }

        if (jsonData != null) {
            TextMessage pushDataTextMessage = new TextMessage(jsonData);
            try {
                session.sendMessage(pushDataTextMessage);
            } catch (IOException e) {
                log.error("session:" + session.getId() + " 推送失败", e);
            }

        }

    }
}
