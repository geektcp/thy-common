package com.geektcp.common.spring.constant;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tanghaiyang on 2018/5/3.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "sort rule", description = "desc or asc")
public class Sort {

    @JSONField(ordinal = 1)
    @ApiModelProperty(value = "sort field", example = "id")
    private String property;

    @JSONField(ordinal = 2)
    @ApiModelProperty(value = "direction：ASC,DESC", example = "DESC")
    private Direction direction;

}
