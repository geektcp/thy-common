package com.geektcp.common.spring.rest;

import com.geektcp.common.core.util.JSONUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author tanghaiyang on 2018/5/16.
 */
@Component
public class RestServiceImpl implements RestService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <Req, Resp> Resp doGet(String url, Req request, Class<Resp> responseType) throws Exception {
        URIBuilder builder = new URIBuilder(url);
        Map<String, Object> params = this.getParameters(request);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.addParameter(entry.getKey(), Objects.toString(entry.getValue(), ""));
        }
        return restTemplate.getForObject(builder.build(), responseType);
    }

    public <Req, Resp> Resp doPost(String url, Req request, Class<Resp> responseType) throws Exception {
        if (request == null) {
            return restTemplate.postForObject(url, null, responseType);
        }
        // headers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setAcceptCharset(Collections.singletonList(Charset.forName("UTF-8")));
        // body
        String jsonBody = new ObjectMapper().writeValueAsString(request);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonBody, httpHeaders);
        return restTemplate.postForObject(url, httpEntity, responseType);
    }

    @Override
    public <Req, Resp> Resp doDelete(String url, Req request, Class<Resp> responseType) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> params = this.getParameters(request);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            headers.add(entry.getKey(), Objects.toString(entry.getValue(), ""));
        }
        ResponseEntity<Resp> exchange = restTemplate.exchange(url, HttpMethod.DELETE,
                new HttpEntity<>(headers), responseType);
        return exchange.getBody();
    }

    ///////////////////////
    // private functions
    ///////////////////////
    private <IN> Map<String, Object> getParameters(IN requestQo) {
        Map<String, Object> params;
        if (requestQo instanceof Map) {
            params = new HashMap<>((Map) requestQo);
        } else {
            params = JSONUtils.toMap(requestQo);
        }
        return params;
    }
}
