package io.dataease.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import io.dataease.controller.request.authModel.VAuthModelRequest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

public class ControllerTest {

    @Test
    public void test(){
        VAuthModelRequest modelRequest = new VAuthModelRequest();

        String body = HttpRequest.post("")
                .header(HttpHeaders.AUTHORIZATION,"")
                .body(JSONUtil.toJsonStr(modelRequest))
                .execute()
                .body();
        System.out.println(body);
    }
}
