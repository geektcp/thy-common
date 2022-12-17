package com.geektcp.common.spring.context;

import java.util.UUID;

/**
 *
 * @author tanghaiyang 2021-07-09
 */
public class TraceHelper {

    public static final String TRACE_ID = "traceId";

    /**
     * 生成 traceid
     *
     * @return traceId
     */
    public static String getTraceId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
