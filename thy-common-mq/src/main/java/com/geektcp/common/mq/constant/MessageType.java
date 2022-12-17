package com.geektcp.common.mq.constant;

import com.geektcp.common.core.constant.Status;

/**
 * @author tanghaiyang on 2021/9/13 13:49.
 */
public enum MessageType implements Status {

    MSG_PAY(1, "pay "),
    MSG_USER(2, "user"),
    MSG_ETL(3, "graph"),
    MSG_DIAGRAM(4, "diagram"),
    MSG_OPERATE(5, "operate"),

    ;

    private Integer code;
    private String desc;

    MessageType(int code, String desc) {
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

