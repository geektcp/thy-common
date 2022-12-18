package com.geektcp.common.log.constant;

import com.geektcp.common.core.constant.Status;

/**
 * @author tanghaiyang 2021/6/23 14:32
 */
public enum LogExceptionStatus implements Status {
    SMS_ALREADY_SEND(10300001, "ok"),
    SMS_ALREADY_OVERDUE(10300002, "failed"),
    ;

    private int code;
    private String desc;

    LogExceptionStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
