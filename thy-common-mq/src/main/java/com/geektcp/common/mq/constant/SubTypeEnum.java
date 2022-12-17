package com.geektcp.common.mq.constant;

/**
 * @author tanghaiyang 2021/10/19 14:12
 */
public enum SubTypeEnum {

    NOT_PAY("NOT_PAY", "wait pay"),
    SUCCESS_PAY("SUCCESS_PAY", "successful pay"),
    FAILED_PAY("FAILED_PAY", "failed to pay "),

    ;

    private String code;
    private String desc;

    SubTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}

