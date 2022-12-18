package com.geektcp.common.log.feign;

import com.alibaba.fastjson.JSONObject;
import com.geektcp.common.log.model.SysLogDo;
import com.geektcp.common.spring.model.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tanghaiyang 2022.8.10
 */
@FeignClient(name = "thy-log")
@Service
public interface LogFeign {

    @PostMapping("/syslog/add")
    ResponseDTO<JSONObject> insert(@RequestBody SysLogDo uo);
}
