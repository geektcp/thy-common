package com.geektcp.common.spring.model.vo;

import lombok.Data;

/**
 * @author tanghaiyang  2020/1/2 13:35
 **/
@Data
public class ProgressVo {
    private Integer status;
    private Integer totalSize = 0;
    private Integer currentSize = 0;
    private String title;
    private String key;
    private Long startTime;
    private Long endTime;
    private String msg;
    private String data;
}
