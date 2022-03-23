package io.dataease.commons.utils;

import org.junit.Test;

import java.util.UUID;

public class CodingUtilTest {

    private static final String secretKey = "www.fit2cloud.co";
    private static final String iv = "1234567890123456";
    /**
     * AES加密和解密
     */
    @Test
    public void aesTest(){
        String str = "zhenhc";
        String aesEncrypt = CodingUtil.aesEncrypt(str, secretKey, iv);
        System.out.println("aesEncrypt："+aesEncrypt);
        String aesDecrypt = CodingUtil.aesDecrypt(aesEncrypt, secretKey, iv);
        System.out.println("aesDecrypt："+aesDecrypt);
    }

    @Test
    public void base64Test(){
        String encoding = CodingUtil.base64Encoding("zhenhc");
        String decoding = CodingUtil.base64Decoding(encoding);
        System.out.println("encoding:"+encoding);
        System.out.println("decoding:"+decoding);
    }

    @Test
    public void shortUuidTest(){
        String shortUuid = CodingUtil.shortUuid();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println("shortUuid:"+shortUuid);
        System.out.println("longUuid:"+uuid);
    }
}
