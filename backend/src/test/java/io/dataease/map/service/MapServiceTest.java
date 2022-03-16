package io.dataease.map.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.dataease.ApplicationTest;
import io.dataease.map.dto.entity.AreaEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MapServiceTest extends ApplicationTest {

    @Autowired
    private MapService mapService;

    @Test
    public void test(){
        List<AreaEntity> areaEntityList = mapService.areaEntities();
        JSONArray jsonArray = (JSONArray) JSON.toJSON(areaEntityList);
        System.out.println(jsonArray);
    }
}
