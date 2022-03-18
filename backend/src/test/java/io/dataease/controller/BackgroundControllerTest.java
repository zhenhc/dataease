package io.dataease.controller;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import io.dataease.ApplicationTest;
import io.dataease.base.domain.SysBackgroundImage;
import io.dataease.controller.background.BackgroundController;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class BackgroundControllerTest extends ApplicationTest {

    @Resource
    public BackgroundController backgroundController;

    @Test
    public void test(){
        Map<String, List<SysBackgroundImage>> all = backgroundController.findAll();
        System.out.println(JSONUtil.parseObj(all));
    }
}
