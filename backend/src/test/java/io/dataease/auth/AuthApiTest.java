package io.dataease.auth;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import io.dataease.auth.api.dto.LoginDto;
import io.dataease.auth.config.RsaProperties;
import io.dataease.auth.util.RsaUtil;
import org.junit.Test;

import java.util.HashMap;

public class AuthApiTest {

    @Test
    public void test(){

        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANL378k3RiZHWx5AfJqdH9xRNBmD9wGD2iRe41HdTNF8RUhNnHit5NpMNtGL0NPTSSpPjjI1kJfVorRvaQerUgkCAwEAAQ==";

        String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEA0vfvyTdGJkdbHkB8mp0f3FE0GYP3AYPaJF7jUd1M0XxFSE2ceK3k2kw20YvQ09NJKk+OMjWQl9WitG9pB6tSCQIDAQABAkA2SimBrWC2/wvauBuYqjCFwLvYiRYqZKThUS3MZlebXJiLB+Ue/gUifAAKIg1avttUZsHBHrop4qfJCwAI0+YRAiEA+W3NK/RaXtnRqmoUUkb59zsZUBLpvZgQPfj1MhyHDz0CIQDYhsAhPJ3mgS64NbUZmGWuuNKp5coY2GIj/zYDMJp6vQIgUueLFXv/eZ1ekgz2Oi67MNCk5jeTF2BurZqNLR3MSmUCIFT3Q6uHMtsB9Eha4u7hS31tj1UWE+D+ADzp59MGnoftAiBeHT7gDMuqeJHPL4b+kC+gzV4FGTfhR9q3tTbklZkD2A==";

        try {
            String username = RsaUtil.decryptByPrivateKey(privateKey, "fovL3ePiGGwwSu6WyhuGpCL1jVlvwF7OZqai/0qc+xRrZuzHpFfjbhraLi1hHKB4P72YQnH+lHSqswFF+2xxjg==");
            String pwd = RsaUtil.decryptByPrivateKey(privateKey,"ddldMGe0E518Pf+S5LWFihGqfJGIImhpmmvhVUlNoOjL+ndIcEkIIH4LJCrVkabgMgDel/xnZ+bZiDLtStE5lA==");
            System.out.println(username);
            System.out.println(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String demo = RsaUtil.encryptByPublicKey(publicKey, "demo");
            String dataease = RsaUtil.encryptByPublicKey(publicKey, "dataease");
            String s = RsaUtil.decryptByPrivateKey(privateKey, demo);
            String s1 = RsaUtil.decryptByPrivateKey(privateKey, dataease);
            System.out.println("name:"+demo);
            System.out.println("password:"+dataease);
            System.out.println(s);
            System.out.println(s1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void loginTest(){
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANL378k3RiZHWx5AfJqdH9xRNBmD9wGD2iRe41HdTNF8RUhNnHit5NpMNtGL0NPTSSpPjjI1kJfVorRvaQerUgkCAwEAAQ==";
        String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEA0vfvyTdGJkdbHkB8mp0f3FE0GYP3AYPaJF7jUd1M0XxFSE2ceK3k2kw20YvQ09NJKk+OMjWQl9WitG9pB6tSCQIDAQABAkA2SimBrWC2/wvauBuYqjCFwLvYiRYqZKThUS3MZlebXJiLB+Ue/gUifAAKIg1avttUZsHBHrop4qfJCwAI0+YRAiEA+W3NK/RaXtnRqmoUUkb59zsZUBLpvZgQPfj1MhyHDz0CIQDYhsAhPJ3mgS64NbUZmGWuuNKp5coY2GIj/zYDMJp6vQIgUueLFXv/eZ1ekgz2Oi67MNCk5jeTF2BurZqNLR3MSmUCIFT3Q6uHMtsB9Eha4u7hS31tj1UWE+D+ADzp59MGnoftAiBeHT7gDMuqeJHPL4b+kC+gzV4FGTfhR9q3tTbklZkD2A==";

        LoginDto loginDto = new LoginDto();
        try {
            loginDto.setUsername(RsaUtil.encryptByPublicKey(publicKey,"admin"));
            loginDto.setPassword(RsaUtil.encryptByPublicKey(publicKey,"dataease"));
        }catch (Exception e){
            e.printStackTrace();
        }
        loginDto.setLoginType(0);
        String body = HttpRequest.post("http://localhost:8081/api/auth/login")
                .body(JSONUtil.toJsonStr(loginDto))
                .execute()
                .body();
        System.out.println(body);
    }
}
