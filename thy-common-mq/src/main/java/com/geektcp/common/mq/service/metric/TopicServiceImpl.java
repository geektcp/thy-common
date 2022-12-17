package com.geektcp.common.mq.service.metric;

import cn.hutool.core.collection.CollectionUtil;
import com.geektcp.common.mq.model.CreateTopicUo;
import com.geektcp.common.mq.model.TopicVo;
import com.geektcp.common.mq.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author tanghaiyang on 2021/9/13 13:39.
 */
@Service
@Slf4j
public class TopicServiceImpl implements TopicService {

    /**
     *
     */
    @Autowired
    private KafkaAdminClient kafkaAdminClient;

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @Override
    public List<TopicVo> list() throws ExecutionException, InterruptedException {
        List<TopicVo> list = new ArrayList<>();
        Set<String> topics = listTopicToSet();
        DescribeTopicsResult describeTopicsResult = kafkaAdminClient.describeTopics(topics);
        KafkaFuture<Map<String, TopicDescription>> mapKafkaFuture = describeTopicsResult.all();
        Map<String, TopicDescription> map = mapKafkaFuture.get();
        topics.forEach(name ->{
            TopicVo topicVo = new TopicVo();
            topicVo.setName(name);
            TopicDescription topicDescription = map.get(name);
            if(topicDescription != null){
                topicVo.setPartitions(topicDescription.partitions().size());
            }
            list.add(topicVo);
        });
        kafkaAdminClient.close();
       return list;
    }


    @Override
    public boolean createTopic(CreateTopicUo uo){
        String name = uo.getName();
        if(StringUtils.isBlank(name)){
            return false;
        }
        Set<String> topicSet = null;
        try {
            topicSet = listTopicToSet();
        } catch (Exception e) {
            return false;
        }
        if(topicSet.contains(name)){
            log.error("已存在topic={}", name);
            return false;
        }
        int paratition = uo.getParatitionCount();
        if(paratition <= 0){
            paratition = 1;
        }
        Short replicationFactor = uo.getReplicationFactor();
        if(replicationFactor == null || replicationFactor <= 0){
            replicationFactor = (short) 1;
        }
        NewTopic topic = new NewTopic(name,paratition, replicationFactor);
        CreateTopicsResult createTopicsResult = kafkaAdminClient.createTopics(Arrays.asList(topic));
        boolean flag = false;
        try {
            KafkaFuture<Void> future = createTopicsResult.all();
            future.get();
            // 如果future 是异常  future.isCompletedExceptionally() = true
            flag = !future.isCompletedExceptionally();
        }catch (Exception e){
            return false;
        }
        kafkaAdminClient.close();
        return flag;
    }

    @Override
    public boolean deleteTopic(String name) {
        List<String> topicNames = Arrays.asList(name);
        Set<String> topicSet = null;
        try {
            topicSet = listTopicToSet();
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e) {
            return false;
        }
        if(!topicSet.contains(name)){
            return false;
        }
        DeleteTopicsResult result = kafkaAdminClient.deleteTopics(topicNames);
        KafkaFuture<Void> future = result.all();
        boolean flag = false;
        try {
            future.get();
            flag = !future.isCompletedExceptionally();
        } catch (Exception e) {
            return false;
        }
        if(!flag){
            return flag;
        }
        List<MessageListenerContainer> containers = (List<MessageListenerContainer>) registry.getAllListenerContainers();
        if(CollectionUtil.isEmpty(containers)){
            return true;
        }
        lable : for (MessageListenerContainer container : containers) {
            if(!container.isRunning()){
                continue;
            }
            Collection<TopicPartition> assignedPartitions = container.getAssignedPartitions();
            for (TopicPartition assignedPartition : assignedPartitions) {
                String topic = assignedPartition.topic();
                if(StringUtils.equals(topic, name)){
                    container.stop();
                    break lable;
                }
            }
        }
        return true;
    }

    private Set<String> listTopicToSet() throws InterruptedException, ExecutionException {
        ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
        listTopicsOptions.listInternal(false);
        ListTopicsResult result = kafkaAdminClient.listTopics(listTopicsOptions);
        Set<String> topics = result.names().get();
        return topics;
    }

}
