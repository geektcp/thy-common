package com.geektcp.common.mq.model;

import lombok.Data;

/**
 * @author tanghaiyang
 * @date 2021/9/15 9:35
 */
@Data
public class TopicVo {

    private String name;

    private Integer partitions;


}
