package io.dataease.controller.panel;

import cn.hutool.http.HttpRequest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

public class ViewApiTest {

    @Test
    public void test(){
        String body = HttpRequest.post("http://localhost:8081/api/panelView/tree")
                .header(HttpHeaders.AUTHORIZATION,"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDc4NDQzMDksInVzZXJJZCI6MSwidXNlcm5hbWUiOiJhZG1pbiJ9.zJSy044m01BRMQKJIAUJyoSH6h9qlvoncdfdNT9fQXg")
                .execute()
                .body();
        System.out.println(body);
    }
}
