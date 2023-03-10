package com.geektcp.common.mq.service.producer;

import cn.hutool.json.JSONUtil;
import com.geektcp.common.mq.configuration.TopicConfig;
import com.geektcp.common.mq.constant.MessageType;
import com.geektcp.common.mq.model.MessageDTO;
import com.geektcp.common.mq.service.CommonProducer;
import com.geektcp.common.mq.service.KafkaProductCountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author tanghaiyang on 2021/9/13 11:09.
 */
@Slf4j
@Service
public class CommonProducerImpl implements CommonProducer {

    private static final String SYNC = "SYNC";
    private static final String ASYNC = "SYNC";

    /**
     *
     */
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private TopicConfig topicConfig;

    @Override
    public Boolean syncSend(MessageDTO<?> dto) {
        boolean validateFlag = validateParam(dto);
        if (!validateFlag) {
            return validateFlag;
        }
        String topic = getTopicByType(dto.getType());
//        Integer partition = dto.getPartition();
        Object data = dto.getData();
//        String key = dto.getKey();
        boolean flag = true;
        try {
            kafkaTemplate.send(topic, data).get();
        } catch (Exception e) {
            flag = false;
        }
//        send(topic, partition, key, data, SYNC);
        if (flag) {
            KafkaProductCountService.countConcurrentMap(topic);
        }
        return flag;
    }

    @Override
    public Boolean asyncSend(MessageDTO dto) {
        boolean validateFlag = validateParam(dto);
        if (!validateFlag) {
            return validateFlag;
        }
        String topic = getTopicByType(dto.getType());
        if (topic == null) {
            return false;
        }
//        Integer partition = dto.getPartition();
        Object data = dto.getData();
//        String key = dto.getKey();
        boolean flag = kafkaTemplate.executeInTransaction(kt -> {
                kafkaTemplate.send(topic, data);
                return true;
            });
        if (flag) {
            KafkaProductCountService.countConcurrentMap(topic);
        }
        return flag;
    }


    @Override
    public Boolean bulkSend(MessageDTO msg) {
        return false;
    }

    /**
     * ??????????????????topic
     *
     * @param type ??????
     * @return topic
     */
    private String getTopicByType(MessageType type) {
        if (MessageType.MSG_PAY.equals(type)) {
            return topicConfig.payNotifyTopic;
        }
        if (MessageType.MSG_USER.equals(type)) {
            return topicConfig.authInfoTopic;
        }
        if (MessageType.MSG_ETL.equals(type)) {
            return topicConfig.graphDataTopic;
        }
        if (MessageType.MSG_DIAGRAM.equals(type)) {
            return topicConfig.designDiagramTopic;
        }
        if (MessageType.MSG_OPERATE.equals(type)) {
            return topicConfig.logOperateTopic;
        }
        log.error("??????????????????");
        return null;
    }

    /**
     * ????????????
     *
     * @param dto ??????
     * @return true ???????????? ???false ????????????
     */
    private boolean validateParam(MessageDTO dto) {
        if (dto == null) {
            log.error("??????????????????");
            return false;
        }
        MessageType type = dto.getType();
        if (type == null) {
            log.error("????????????????????????");
            return false;
        }
        Object data = dto.getData();
        if (data == null) {
            log.error("?????????????????????");
            return false;
        }
        String key = dto.getKey();
        Integer partition = dto.getPartition();
        boolean notBlank = StringUtils.isNotBlank(key) && partition != null;
        boolean blank = StringUtils.isBlank(key) && partition == null;
        if (!notBlank && !blank) {
            // key ???partition ????????????????????? ??????????????????
            log.error("?????????key???????????????????????????????????????");
            return false;
        }
        log.info("?????????????????????={}", JSONUtil.toJsonStr(dto));
        return true;
    }

//    /**
//     * ???????????????kafka
//     *
//     * @param topic     ??????
//     * @param partition ??????
//     * @param key       key
//     * @param data      ??????
//     * @param type      1-???????????? 2-????????????
//     * @return ???????????? true ????????? false ??????
//     */
//    public boolean send(String topic, Integer partition, String key, Object data, String type) {
//        if (partition != null) {
//            boolean flag = kafkaTemplate.executeInTransaction(kt -> {
//                try {
//                    if (SYNC.equals(type)) {
//                        //  ??????
//                        if (data instanceof List) {
//                            // ????????????
//                            for (Object d : (List<Object>) data) {
//                                kafkaTemplate.send(topic, partition, key, d).get();
//                            }
//                        } else {
//                            kafkaTemplate.send(topic, partition, key, data).get();
//                        }
//                    } else {
//                        // ??????
//                        if (data instanceof List) {
//                            // ????????????
//                            for (Object d : (List<Object>) data) {
//                                kafkaTemplate.send(topic, partition, key, d);
//                            }
//                        } else {
//                            kafkaTemplate.send(topic, partition, key, data);
//                        }
//                    }
//                } catch (Exception e) {
//                    log.error("??????????????????", e);
//                    log.error("??????????????????,topic: {}, partition: {}, key: {}", topic, partition, key);
//                    return false;
//                }
//                return true;
//            });
//            return flag;
//        } else {
//            boolean flag = kafkaTemplate.executeInTransaction(kt -> {
//                try {
//                    if (SYNC.equals(type)) {
//                        //  ??????
//                        if (data instanceof List) {
//                            // ????????????
//                            for (Object d : (List<Object>) data) {
//                                kafkaTemplate.send(topic, d).get();
//                            }
//                        } else {
//                            kafkaTemplate.send(topic, data).get();
//                        }
//                    } else {
//                        // ??????
//                        if (data instanceof List) {
//                            // ????????????
//                            for (Object d : (List<Object>) data) {
//                                kafkaTemplate.send(topic, d);
//                            }
//                        } else {
//                            kafkaTemplate.send(topic, data);
//                        }
//                    }
//                } catch (Exception e) {
//                    log.error("??????????????????", e);
//                    log.error("??????????????????,topic: {}, partition: {}, key: {}", topic, partition, key);
//                    return false;
//                }
//                return true;
//            });
//            return flag;
//        }
//    }

}
