package com.geektcp.common.log.aop;

import com.alibaba.fastjson.JSONObject;
import com.geektcp.common.log.feign.LogFeign;
import com.geektcp.common.log.model.SysLogDo;
import com.geektcp.common.spring.util.HttpRequestHeadUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;

/**
 * @author tanghaiyang 2021/12/6 15:01
 */
@Slf4j
@Component
@Aspect
public class LogService {
    @Autowired
    private LogFeign logFeign;

    private static ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("@annotation(com.geektcp.common.log.aop.OperatorLog)")
    public void aspect() {
        // nothing
    }

    @Before("aspect()")
    public void doBefore(final JoinPoint joinPoint) {
    }

    @AfterReturning(returning = "result", pointcut = "aspect()")
    public void doAfterReturning(final JoinPoint joinPoint, final Object result) {
        SysLogDo sysLogDo = getMethodDescription(joinPoint);
        JSONObject response = (JSONObject) JSONObject.toJSON(result);
        if (!Objects.isNull(response) && !Objects.isNull(response.getBoolean("success")) && response.getBoolean("success")) {
            sysLogDo.setSuccessful(1);
        } else {
            sysLogDo.setSuccessful(0);
        }
        sysLogDo.setOperateDate(new Date());
        sysLogDo.setUserId(HttpRequestHeadUtils.getCurUID());
        sysLogDo.setUsername(HttpRequestHeadUtils.getValueByKey("username"));
        sysLogDo.setName(HttpRequestHeadUtils.getCurName());
        sysLogDo.setTenantId(HttpRequestHeadUtils.getCurTenantId());
        sysLogDo.setIp(HttpRequestHeadUtils.getCurIp());
        sysLogDo.setCreateBy(HttpRequestHeadUtils.getCurUID());

        try {
            logFeign.insert(sysLogDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterThrowing(throwing = "ex", pointcut = "aspect()")
    public void doAfterThrowing(final JoinPoint joinPoint, Exception ex) {
        try {
            log.error("exception ex: ", ex);
        } catch (Exception e) {
            log.error("exception: ", e);
        }
    }

    private SysLogDo getMethodDescription(final JoinPoint joinPoint) {
        SysLogDo infoVo = new SysLogDo();
        final String targetName = joinPoint.getTarget().getClass().getName();
        final String methodName = joinPoint.getSignature().getName();
        final Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        }
        if (targetClass == null) {
            return infoVo;
        }
        final Method[] methods = targetClass.getMethods();

        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                final Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    String infoString = method.getAnnotation(OperatorLog.class).infoString();
                    if (StringUtils.isNotBlank(infoString)) {
                        return JSONObject.parseObject(infoString, SysLogDo.class);
                    }
                    break;
                }
            }
        }
        return infoVo;
    }
}
