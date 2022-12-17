package com.geektcp.common.spring.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.PageInfo;
import com.geektcp.common.core.constant.Status;
import com.geektcp.common.spring.constant.CommonStatus;
import com.geektcp.common.spring.model.qo.PageQo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author haiyang on 3/10/20 9:55 AM.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "page data", description = "return content")
public class PageResponseDTO<T> {

    @ApiModelProperty(value = "status code")
    private Object code = 200;

    @ApiModelProperty(value = "description")
    private String msg;

    @ApiModelProperty(value = "response data")
    private PagePayload<T> data;

    public PageResponseDTO() {
        this(0, 1, 10);
    }

    public PageResponseDTO(long total, int pageNo, int pageSize) {
        this(null, total, pageNo, pageSize);
    }

    public void setPage(List<T> data, long total, int pageNo, int pageSize) {
        if (this.data == null) {
            this.data = new PagePayload<>();
        }
        this.data.setTotal(total);
        this.data.setPageNum(pageNo);
        this.data.setPageSize(pageSize);
        this.data.setList(data);
    }

    public PageResponseDTO(List<T> data, PageInfo<T> pageInfo) {
        initializePageResponseDTO();
        this.setPage(data, pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize());
    }

    private void initializePageResponseDTO() {
        code = CommonStatus.SUCCESS.getCode();
        msg = CommonStatus.SUCCESS.getDesc();
    }

    public PageResponseDTO(Status status, List<T> data, PageInfo<T> pageInfo) {
        initializePageResponseDTO();
        if(Objects.isNull(pageInfo)){
            pageInfo = new PageInfo<>();
        }
        if(Objects.isNull(data)){
            data = new ArrayList<>();
        }
        this.setPage(data, pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize());
        this.code = status.getCode();
        this.msg = status.getDesc();
    }

    public PageResponseDTO(List<T> data, long total, int pageNo, int pageSize) {
        initializePageResponseDTO();
        if(Objects.isNull(data)){
            data = new ArrayList<>();
        }
        this.setPage(data, total, pageNo, pageSize);
    }

    public PageResponseDTO<T> success() {
        return new PageResponseDTO<>();
    }

    public PageResponseDTO<T> success(List<T> data, long total, PageQo pageQo) {
        if(Objects.isNull(pageQo)){
            pageQo = new PageQo();
        }
        return new PageResponseDTO<>(data, total, pageQo.getPageNo(), pageQo.getPageSize());
    }

    public static <T> PageResponseDTO<T> success(List<T> data, long total, int pageNo, int pageSize) {
        PageResponseDTO<T> response = new PageResponseDTO<>();
        response.setMsg("error");
        response.setCode(500);
        response.setPage(data, data.size(), 0, 10);
        return response;
    }

    public static <T> PageResponseDTO<T> error() {
        PageResponseDTO<T> response = new PageResponseDTO<>();
        response.setMsg("error");
        response.setCode(500);
        return response;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value = "payload data", description = "content")
    public static class PagePayload<T> {

        @ApiModelProperty(value = "total", example = "100")
        @JSONField(ordinal = 1)
        private long total;

        @ApiModelProperty(value = "current number", example = "1")
        @JSONField(ordinal = 2)
        private int pageNum = 1;

        @ApiModelProperty(value = "page size", example = "10")
        @JSONField(ordinal = 3)
        private int pageSize = 10;

        @ApiModelProperty(value = "total page", example = "10")
        @JSONField(ordinal = 4)
        private int pages;

        @ApiModelProperty(value = "payload")
        @JSONField(ordinal = 5)
        private List<T> list;

        public long getPages() {
            if (Objects.isNull(pageSize) || pageSize == 0) {
                return 0L;
            }
            return (total + pageSize - 1) / pageSize;
        }

    }
}
