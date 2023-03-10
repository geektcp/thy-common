package com.geektcp.common.mq.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geektcp.common.mq.model.MessageVo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tanghaiyang on 2021/9/13 12:05.
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayMessageVo implements MessageVo {


}
