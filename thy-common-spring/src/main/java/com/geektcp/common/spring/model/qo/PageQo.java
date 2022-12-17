package com.geektcp.common.spring.model.qo;

import com.geektcp.common.spring.constant.Direction;
import com.geektcp.common.spring.constant.Sort;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanghaiyang on 2018/5/3.
 */
@Data
@NoArgsConstructor
@ApiModel(value = "page qo", description = "para")
public class PageQo implements Serializable {

    private static final long serialVersionUID = 2737186511678863902L;

    @Min(value = 0)
    @ApiModelProperty(value = "page nunmber", example = "0")
    protected int pageNo = 0;

    @Min(value = 5)
    @ApiModelProperty(value = "page size", example = "10")
    protected int pageSize = 10;

    @ApiModelProperty(value = "sort")
    protected List<Sort> sorts = new ArrayList<>();

    public void addSort(String property, Direction direction) {
        this.sorts.add(new Sort(property, direction));
    }

    public PageQo(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
