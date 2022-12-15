package com.geektcp.common.spring.constant;

import com.geektcp.common.core.constant.Status;

/**
 * @author tanghaiyang on 2020-03-28 17:41
 */

public enum CommonStatus implements Status {

    SUCCESS(200, "success"),
    FAIL(400, "fail"),
    ;

    private Integer code;
    private String desc;

    CommonStatus(int code, String desc) {
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
