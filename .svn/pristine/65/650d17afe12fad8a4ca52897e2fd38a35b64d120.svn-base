package com.qiaobei.hmp.modules.webSocket;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.service.DoctorService;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springside.modules.utils.WebsocketUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/12/9 0009
 * Time 11:16
 */
public class WebSocketHandlerImpl implements org.springframework.web.socket.WebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(WebSocketHandlerImpl.class);
    private static final Map<String, List<WebSocketSession>> sessionMapList = new HashMap<>();
    private static final Map<String, WebSocketSession> sessionMap = new HashMap<>();

    @Resource(name = "doctorServiceCode")
    DoctorService doctorService;

    public void CallAdvingAndNusurByDoctor(Doctor doctor, String... type) {
        Map infoMap = Maps.newConcurrentMap();
        Map callSessionMap = Maps.newConcurrentMap();
        String docName = doctor.getName();
        if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
            doctor = doctorService.getDoctor(doctor.getPrimaryDoctorId());
        }
        if (doctor != null) {
            if (sessionMapList.get(doctor.getId() + "") != null) {
                sessionMapList.get(doctor.getId() + "").forEach(webSocketSession -> {
                    if (webSocketSession.isOpen()) {
                        callSessionMap.put(webSocketSession.getId(), webSocketSession);
                    }
                });
                if (callSessionMap.size() != 0) {
                    infoMap.put("user", docName);
                    if (type.length > 0 && type[0].equals("setting")) {
                        infoMap.put("msg", "广告位发生变化");
                        infoMap.put("type", type[0]);
                    } else {
                        infoMap.put("type", "call");
                        infoMap.put("msg", "排队信息发生变化");
                    }
                    WebsocketUtil.sendAllSession(callSessionMap, infoMap);
                }
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("用户: " + session.getId() + " 连接webSocket....");
//        String sessionId = String.valueOf(session.getAttributes().get("sessionId"));
        //将用户放入到sessionMap
        sessionMap.put(session.getId(), session);
        //获取用户的url 截取监控医生ID 丢入 sessionDoctorIdMapWebSocketSessionList
        String urlArr[] = session.getUri().toString().split("=");
        if (urlArr.length == 2 && NumberUtils.isNumber(urlArr[1])) {
            String doctorId = urlArr[1];
            log.info("webSocket初始化绑定医生ID:{}", doctorId);
            if (sessionMapList.get(doctorId) == null || sessionMapList.get(doctorId).size() == 0) {
                sessionMapList.put(doctorId, Lists.newArrayList(session));
            } else {
                sessionMapList.get(doctorId).add(session);
            }
            //给客户端反馈信息
            Map infoMap = Maps.newConcurrentMap();
            Doctor doctor = doctorService.getDoctor(Long.valueOf(doctorId));
            if (doctor != null) {
                String doctorName = doctor.getName();
                if (doctor.getDoctorType() == Doctor.Doctor_Type.Clinic_Boss) {
                    doctorName = doctorName + "," + doctorService.findSubDoctor(doctor).stream().map(Doctor::getName).collect(Collectors.joining(","));
                }
                infoMap.put("msg", "广告机连接成功.\n服务器在线人数为: " + sessionMap.size() + ".\n 您当前绑定的医生为:\n[ " + doctorName + " ]");
                infoMap.put("user", "服务器信息");
                infoMap.put("type", "init");
                WebSocketMessage<String> webSocketMessage = new TextMessage(new ObjectMapper().writeValueAsString(infoMap));
                session.sendMessage(webSocketMessage);
                log.info("目前服务器监听医生有:{}", sessionMapList);
            }
        }

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        Map msgMap = (Map) JSON.parse((String) message.getPayload());
        System.out.println("接受到用户发送来的信息:" + msgMap.get("doctor"));
        if (sessionMap.size() > 1 && msgMap.get("doctor") != null && NumberUtils.isNumber(String.valueOf(msgMap.get("doctor"))) && !msgMap.get("doctor").equals("0")) {
            Doctor doctor = doctorService.getDoctor((Long) msgMap.get("doctor"));

            if (doctor != null) {
                String docName = doctor.getName();
                if (doctor.getDoctorType() == Doctor.Doctor_Type.Sub_Doctor) {
                    doctor = doctorService.getDoctor(doctor.getPrimaryDoctorId());
                }
                Map infoMap = Maps.newConcurrentMap();
                Map sessionMapNotMe = Maps.newConcurrentMap();
                infoMap.put("user", docName);
                String type = (String) msgMap.get("type");
                if ("setting".equals(type)) {
                    infoMap.put("msg", "广告位发生变化");
                    infoMap.put("type", type);
                } else {
                    infoMap.put("type", "call");
                    infoMap.put("msg", "排队信息发生变化");
                }
                Doctor finalDoctor = doctor;
                sessionMapList.forEach((doctorId, webSocketSessionList) -> {
                    if (doctorId.equals(finalDoctor.getId())) {
                        webSocketSessionList.forEach(webSocketSession -> {
                            if (!session.getId().equals(webSocketSession.getId())) {
                                sessionMapNotMe.put(webSocketSession.getId(), webSocketSession);
                            }
                        });
                    }
                });
                if (sessionMapNotMe.size() > 0)
                    WebsocketUtil.sendAllSession(sessionMapNotMe, infoMap);

                //管理员
            }
        } else if (msgMap.get("doctor").equals("0")) {
            Map infoMap = Maps.newConcurrentMap();
            Map sessionMapNotMe = Maps.newConcurrentMap();
            infoMap.put("user", "管理员");
            String type = (String) msgMap.get("type");
            if ("setting".equals(type)) {
                infoMap.put("msg", "广告位发生变化");
                infoMap.put("type", type);
            } else {
                infoMap.put("type", "call");
                infoMap.put("msg", "排队信息发生变化");
            }
            sessionMapList.forEach((doctorId, webSocketSessionList) -> webSocketSessionList.forEach(webSocketSession -> {
                if (!session.getId().equals(webSocketSession.getId())) {
                    sessionMapNotMe.put(webSocketSession.getId(), webSocketSession);
                }
            }));
            if (sessionMapNotMe.size() > 0)
                WebsocketUtil.sendAllSession(sessionMapNotMe, infoMap);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("用户: " + session.getId() + " -  连接关闭webSocket....");
        sessionMap.remove(session.getId());
        sessionMapList.forEach((s, webSocketSessionsList) -> webSocketSessionsList.remove(session));
        log.info("会话还剩下{}人", sessionMap.size());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


}
