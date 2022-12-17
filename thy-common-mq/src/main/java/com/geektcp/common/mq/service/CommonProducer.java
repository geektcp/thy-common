package com.geektcp.common.mq.service;

import com.geektcp.common.mq.model.MessageDTO;

/**
 * @author tanghaiyang on 2021/9/13 11:09.
 */
public interface CommonProducer {

    Boolean syncSend(MessageDTO<?> msg);

    Boolean asyncSend(MessageDTO<?> msg);

    Boolean bulkSend(MessageDTO<?> msg);

}
