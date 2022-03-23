package io.dataease.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

import io.dataease.commons.constants.RedisConstants;
import io.dataease.controller.request.chart.ChartGroupRequest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import redis.clients.jedis.Jedis;


public class BackgroundControllerTest{
    @Test
    public void test(){
        ChartGroupRequest chartGroupRequest = new ChartGroupRequest();


        String body = HttpRequest.post("http://localhost:8081/chart/group/tree")
                .body(JSONUtil.toJsonStr(chartGroupRequest))
                .header(HttpHeaders.AUTHORIZATION,"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDc4NDQzMDksInVzZXJJZCI6MSwidXNlcm5hbWUiOiJhZG1pbiJ9.zJSy044m01BRMQKJIAUJyoSH6h9qlvoncdfdNT9fQXg")
                .execute()
                .body();
        System.out.println(body);
    }
    @Test
    public void findAllTest(){
        Jedis jedis = new Jedis(RedisConstants.IP);
        String s = jedis.get(RedisConstants.REDIS_TOKEN_KEY);
        HttpRequest header = HttpRequest.get("http://localhost:8081/background/findAll")
                .header(HttpHeaders.AUTHORIZATION, s.replace("\"",""));
        String body = header.execute()
                .body();
        System.out.println(body);
    }
}
