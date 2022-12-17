package com.geektcp.common.spring.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tanghaiyang on 2016/2/27 10:10.
 */
public class IPUtils {

    private static final String UNKNOWN = "unknown";

    private IPUtils() {
    }

    /**
     * get really source IP
     * use multi nginx server to reverse http request,we can not get really Ip
     * with request.getRemoteAddr(), or other header:
     * Proxy-Client-IP
     * WL-Proxy-Client-IP
     * X-Real-IP
     * x-forwarded-for
     *
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    public static String getIp() {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        return IPUtils.getIp(request);
    }

}
