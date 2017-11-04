package com.qiaobei.hmp.support;

import com.qiaobei.hmp.modules.entity.SystemLog;
import com.qiaobei.hmp.service.LogService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogAop {

    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);
    @Resource
    private LogService logService;

    //Controller层切点
    @Pointcut("@annotation(com.qiaobei.hmp.support.LogAnnotation)")
    public void controllerAspect() {
    }

    /**
     * 操作异常记录
     *
     * @param point
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint point, Throwable e) {
        SystemLog log = new SystemLog();
        Map<String, Object> map = null;
        String accountName = null;
        String ip = null;
        try {
            ip = SecurityUtils.getSubject().getSession().getHost();
        } catch (Exception ee) {
            ip = "无法获取登录用户Ip";
        }
        try {
            map = getControllerMethodDescription(point);
            accountName = SecurityUtils.getSubject().getPrincipal().toString();
            if (StringUtils.isEmpty(accountName)) {
                accountName = "无法获取登录用户信息！";
            }
        } catch (Exception ee) {
            accountName = "无法获取登录用户信息！";
        }
        log.setAccount(accountName);
        if (map != null && map.get("module") != null)
            if (map != null) {
                if (map.get("module") != null)
                    log.setModule((String) map.get("module"));
                log.setMethods("<font color=\"red\">执行方法异常:-->" + map.get("methods") + "</font>");
            }
        log.setDescription("<font color=\"red\">执行方法异常:-->" + e + "</font>");
        log.setUserIP(ip);
        log.setActionTime("0");
        log.setOperTime(new Date());
        logService.save(log);
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param point 切点
     */
    @Around("controllerAspect()")
    public Object doController(ProceedingJoinPoint point) {
        Object result = null;
        // 执行方法名
//        String methodName = point.getSignature().getName();
//        String className = point.getTarget().getClass().getSimpleName();
        SystemLog log = new SystemLog();
        Map<String, Object> map = null;
        String accountName = null;
        Long start = 0L;
        Long end = 0L;
        Long time = 0L;
        String ip = null;
        try {
            ip = SecurityUtils.getSubject().getSession().getHost();
        } catch (Exception e) {
            ip = "无法获取登录用户Ip";
        }
        try {
            accountName = SecurityUtils.getSubject().getPrincipal().toString();
            if (StringUtils.isEmpty(accountName)) {
                accountName = "无法获取登录用户信息！";
            }
        } catch (Exception e) {
            accountName = "无法获取登录用户信息！";
        }
        // 当前用户
        try {
            map = getControllerMethodDescription(point);
            // 执行方法所消耗的时间
            start = System.currentTimeMillis();
            result = point.proceed();
            end = System.currentTimeMillis();
            time = end - start;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        log.setAccount(accountName);
        log.setModule((String) map.get("module"));
        log.setMethods((String) map.get("methods"));
        log.setDescription((String) map.get("description"));
        log.setUserIP(ip);
        log.setActionTime(time.toString());
        log.setOperTime(new Date());
        logService.save(log);
        return result;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public Map<String, Object> getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    map.put("module", method.getAnnotation(LogAnnotation.class).module());
                    map.put("methods", method.getAnnotation(LogAnnotation.class).methods());
                    String descr = method.getAnnotation(LogAnnotation.class).description();
                    if (StringUtils.isEmpty(descr)) descr = "执行成功!";
                    map.put("description", descr);
                    break;
                }
            }
        }
        return map;
    }
}
