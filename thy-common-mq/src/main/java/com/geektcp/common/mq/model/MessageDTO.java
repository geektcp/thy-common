package com.geektcp.common.mq.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.geektcp.common.core.generator.IdGenerator;
import com.geektcp.common.mq.constant.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.codehaus.commons.nullanalysis.NotNull;

/**
 * @author tanghaiyang on 2021/9/13 12:05.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO<T> {

    @NotNull
    @JSONField(ordinal = 1)
    private Long id = IdGenerator.getId();

    @NotNull
    @JSONField(ordinal = 2)
    private MessageType type;

    @NotNull
    @JSONField(ordinal = 4)
    private T data;

    ///////////////////////////////////////////////////////////
    @JSONField(ordinal = 5)
    private String key;

    @JSONField(ordinal = 6)
    private Integer partition;

    @JSONField(ordinal = 7)
    private Long timestamp;

}
