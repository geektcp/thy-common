package com.geektcp.common.log.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author tanghaiyang 2021/5/10 15:34
 */
@Data
public class SysLogDo {
    private String serviceName;

    private String operateType;

    private String functionType;

    private String description;

    private Integer successful = 1;

    private String userId;

    private String username;

    private String name;

    @ApiModelProperty(value = "ip")
    private String ip;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date operateDate;

    private String tenantId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    private String createBy;
}
