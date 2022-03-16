package io.dataease.controller.chart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.dataease.ApplicationTest;
import io.dataease.base.mapper.ext.ExtChartViewMapper;
import io.dataease.commons.constants.CommonConstants;
import io.dataease.controller.request.chart.ChartExtRequest;
import io.dataease.dto.chart.ChartViewDTO;
import io.dataease.service.chart.ChartViewService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

public class ChartViewControllerTest extends ApplicationTest {

    @Autowired
    private ChartViewController chartViewController;

    @Autowired
    private ExtChartViewMapper extChartViewMapper;

    @Autowired
    private ChartViewService chartViewService;
    @Test
    public void test(){
        String userId = "1";
        String id = "84b444e1-0088-44f9-acdc-cc39018413bc";
        ChartExtRequest request = new ChartExtRequest();
        ChartViewDTO chartViewDTO = extChartViewMapper.searchOneWithPrivileges(userId, id);
        //JSONObject jsonObject = (JSONObject) JSON.toJSON(chartViewDTO);
        //System.out.println(jsonObject);
        //chartViewController.getData();
        // 如果是从仪表板获取视图数据，则仪表板的查询模式，查询结果的数量，覆盖视图对应的属性
        if (CommonConstants.VIEW_QUERY_FROM.PANEL.equals(request.getQueryFrom()) && CommonConstants.VIEW_RESULT_MODE.CUSTOM.equals(request.getResultMode())) {
            chartViewDTO.setResultMode(request.getResultMode());
            chartViewDTO.setResultCount(request.getResultCount());
        }
        /*Class<ChartViewService> chartViewServiceClass = ChartViewService.class;
        try {
            ChartViewService chartViewService = chartViewServiceClass.newInstance();
            Method calcData = chartViewServiceClass.getDeclaredMethod("calcData", ChartViewDTO.class, ChartExtRequest.class, Boolean.class);
            ChartViewDTO chartView = (ChartViewDTO) calcData.invoke(chartViewService,chartViewDTO,request,request.isCache());
            JSONObject jsonObject = (JSONObject) JSON.toJSON(chartView);
            System.out.println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            ChartViewDTO viewDTO = chartViewService.calcData(chartViewDTO, request, request.isCache());
            JSONObject jsonObject = (JSONObject) JSON.toJSON(viewDTO);
            System.out.println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
