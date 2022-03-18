package io.dataease.controller.chart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.dataease.ApplicationTest;
import io.dataease.base.domain.ChartViewWithBLOBs;
import io.dataease.base.domain.DatasetTableField;
import io.dataease.base.domain.Datasource;
import io.dataease.base.mapper.ext.ExtChartViewMapper;
import io.dataease.commons.constants.CommonConstants;
import io.dataease.commons.utils.BeanUtils;
import io.dataease.commons.utils.CommonBeanFactory;
import io.dataease.commons.utils.LogUtil;
import io.dataease.controller.request.chart.ChartDrillRequest;
import io.dataease.controller.request.chart.ChartExtFilterRequest;
import io.dataease.controller.request.chart.ChartExtRequest;
import io.dataease.controller.request.datasource.DatasourceRequest;
import io.dataease.dto.chart.*;
import io.dataease.dto.dataset.DataSetTableDTO;
import io.dataease.dto.dataset.DataSetTableUnionDTO;
import io.dataease.dto.dataset.DataTableInfoDTO;
import io.dataease.i18n.Translator;
import io.dataease.provider.ProviderFactory;
import io.dataease.provider.datasource.DatasourceProvider;
import io.dataease.provider.query.QueryProvider;
import io.dataease.service.chart.ChartViewService;
import io.dataease.service.dataset.DataSetTableFieldsService;
import io.dataease.service.dataset.DataSetTableService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class ChartViewControllerTest extends ApplicationTest {

    @Autowired
    private ChartViewController chartViewController;

    @Autowired
    private ExtChartViewMapper extChartViewMapper;

    @Autowired
    private DataSetTableFieldsService dataSetTableFieldsService;

    @Autowired
    private DataSetTableService dataSetTableService;
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
