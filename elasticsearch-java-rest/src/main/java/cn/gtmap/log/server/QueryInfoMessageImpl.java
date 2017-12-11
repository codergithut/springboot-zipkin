package cn.gtmap.log.server;

import cn.gtmap.log.domain.message.RequestMessage;
import cn.gtmap.log.domain.message.ResponseMessage;
import cn.gtmap.log.domain.message.StatisticsRequestMessage;
import cn.gtmap.log.domain.query.QueryMetadata;
import cn.gtmap.log.service.AbstractQueryInfoMessage;
import cn.gtmap.log.service.ExplainQueryMetadata;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/11
 * @description
 */
public class QueryInfoMessageImpl extends AbstractQueryInfoMessage {

    @Autowired
    ExplainQueryMetadata explainQueryMetadata;


    public QueryInfoMessageImpl(ExplainQueryMetadata searchByQueryMetadata) {
        super(searchByQueryMetadata);
    }

    @Override
    public QueryMetadata converRequestMessage(RequestMessage requestMessage) {
        StatisticsRequestMessage statisticsRequestMessage = (StatisticsRequestMessage)requestMessage;

        Date startTime = statisticsRequestMessage.getBeginTime();
        Date endTime = statisticsRequestMessage.getEndTime();
        String searchUrl = statisticsRequestMessage.getUrlName();

        QueryMetadata queryMetadata = new QueryMetadata();
        queryMetadata.setEndpoint("/zipkin-*/_search?size=0");

        List<QueryMetadata.MustQueryMetaData> queryMetaDataList = new ArrayList<QueryMetadata.MustQueryMetaData>();

        QueryMetadata.AggregationMetaData aggregationMetaData = queryMetadata.new AggregationMetaData();

        aggregationMetaData.setFieldName("name");
        aggregationMetaData.setType("count");
        if(searchUrl != null) {
            QueryMetadata.MustQueryMetaData queryMetaData = queryMetadata.new MustQueryMetaData();
            queryMetaData.setFilterDo("wildcard");
            List<String> symbolValue = new ArrayList<String>();
            symbolValue.add(statisticsRequestMessage.getUrlName());
            List<String> symbols = new ArrayList<String>();
            symbols.add("equals");
            queryMetaData.setFiledName("name");
            queryMetaData.setFiledValue(symbolValue);
            queryMetaData.setSymbol(symbols);
            queryMetaDataList.add(queryMetaData);
        }

        if(startTime != null && endTime != null) {
            QueryMetadata.MustQueryMetaData queryMetaData1 = queryMetadata.new MustQueryMetaData();
            queryMetaData1.setFilterDo("range");
            List<String> symbolValue1 = new ArrayList<String>();
            symbolValue1.add(statisticsRequestMessage.getBeginTime().getTime() + "");
            symbolValue1.add(statisticsRequestMessage.getEndTime().getTime() + "");
            List<String> symbols1 = new ArrayList<String>();
            symbols1.add("gte");
            symbols1.add("lte");
            queryMetaData1.setFiledName("timestamp_millis");
            queryMetaData1.setFiledValue(symbolValue1);
            queryMetaData1.setSymbol(symbols1);
            queryMetaDataList.add(queryMetaData1);
        }

        queryMetadata.setAggregationMetaData(aggregationMetaData);

        queryMetadata.setQueryMustMetaData(queryMetaDataList);

        return queryMetadata;
    }

    @Override
    public ResponseMessage analysisSearchResponse(String searchResponse, RequestMessage requestMessage, String data) {
        ResponseMessage responseMessage = new ResponseMessage();
        String datas = data.replaceAll("/n", "");
        Map<String,String> statisticalData = new HashMap<String,String>();
        JSONObject jsonObject = JSON.parseObject(datas);
        JSONObject tt = null;
        JSONObject t = (JSONObject) jsonObject.get("aggregations");
        for(String key : t.keySet()) {
            tt = (JSONObject) t.get(key);
            break;
        }

        if(tt != null) {
            JSONArray ttt = (JSONArray) tt.get("buckets");
            for(JSONObject json : ttt.toJavaList(JSONObject.class)) {
                statisticalData.put(json.get("key").toString(), json.get("doc_count").toString());
            }
        }
        responseMessage.setRequestInfo(JSON.toJSONString(statisticalData));
        return responseMessage;
    }

}
