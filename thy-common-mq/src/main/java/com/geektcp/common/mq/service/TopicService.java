package com.geektcp.common.mq.service;

import com.geektcp.common.mq.model.CreateTopicUo;
import com.geektcp.common.mq.model.TopicVo;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author tanghaiyang on 2021/9/13 13:38.
 */
public interface TopicService {
    List<TopicVo> list() throws ExecutionException, InterruptedException;

    boolean createTopic(CreateTopicUo uo)throws InterruptedException, ExecutionException ;

    boolean deleteTopic(String name) throws ExecutionException, InterruptedException ;
}
