package com.geektcp.common.spring.model.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;

/**
 * @author tanghaiyang on 2018/12/26.
 */
@Data
public class PageQoBase {
    @Valid
    @ApiModelProperty(value = "page")
    protected PageQo page = new PageQo();
}
