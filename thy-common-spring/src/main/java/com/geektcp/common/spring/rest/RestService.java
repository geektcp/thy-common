package com.geektcp.common.spring.rest;

/**
 * @author tanghaiyang on 2018/5/16.
 */
public interface RestService {

    /**
     * Call restful service with http get method.
     *
     * @param url
     * @param request
     * @param responseType
     * @return
     * @throws Exception
     */
    <Req, Resp> Resp doGet(String url, Req request, Class<Resp> responseType) throws Exception;

    /**
     * Call restful service with http post method.
     *
     * @param url
     * @param request
     * @param responseType
     * @return
     * @throws Exception
     */
    <Req, Resp> Resp doPost(String url, Req request, Class<Resp> responseType) throws Exception;

    /**
     * Call restful service with http delete method.
     *
     * @param url
     * @param request
     * @param responseType
     * @return
     * @throws Exception
     */
    <Req, Resp> Resp doDelete(String url, Req request, Class<Resp> responseType) throws Exception;
}
