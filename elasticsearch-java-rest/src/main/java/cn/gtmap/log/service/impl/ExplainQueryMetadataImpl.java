package cn.gtmap.log.service.impl;

import cn.gtmap.log.domain.query.QueryMetadata;
import cn.gtmap.log.service.ExplainQueryMetadata;
import cn.gtmap.log.util.ElasticSearchRestClient;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/8
 * @description ExplainQueryMetadata 实现类
 */
@Service
public class ExplainQueryMetadataImpl implements ExplainQueryMetadata {

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @description 于elasticsearch服务打交道的家伙，可以用线程池来处理
     */
    final RestClient client;

    public ExplainQueryMetadataImpl(RestClient client) {
        this.client = client;
    }

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @param queryMetadata 查询消息的元数据
     * @return 返回查询结果
     * @description 根据自定义查询元数据转换为查询语句进行查询并返回数据
     */
    @Override
    public String getSearchResponseByMetatdata(QueryMetadata queryMetadata) throws IOException {
        String endponit = queryMetadata.getEndpoint();

        QueryMetadata.AggregationMetaData aggregationMetaData = queryMetadata.getAggregationMetaData();

        List<QueryMetadata.MustQueryMetaData> queryMetaDatas = queryMetadata.getQueryMustMetaData();

        Map<String,Object> data = new HashMap<String,Object>();

        data.putAll(dealAggs(aggregationMetaData));
        data.putAll(dealQuery(queryMetaDatas));

        return ElasticSearchRestClient.elasticDo(data, endponit, client, "POST");
    }


    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @param aggregationMetaData 聚合函数元数据信息
     * @return 拼接查询数据Map对象
     * @description 根据元数据对统计行为进行查询语句拼装
     */
    private Map<String,Object> dealAggs(QueryMetadata.AggregationMetaData aggregationMetaData) {
        boolean flag = true;

        Map<String,Object> aggr = new HashMap<String,Object>();

        Map<String,Object> unit = new HashMap<String,Object>();

        do {
            Map<String,Object> newValue = new HashMap<String,Object>();
            Map<String,Object> aggr2 = new HashMap<String,Object>();
            Map<String,String> aggr3 = new HashMap<String,String>();

            if(flag) {
                flag = false;
                aggr.put("aggs", newValue);
                newValue.put("count", aggr2);
                aggr2.put("terms", aggr3);
                aggr3.put("field", aggregationMetaData.getFieldName());
            } else {
                unit.put("aggrs", newValue);
                newValue.put("count", aggr2);
                aggr2.put("terms", aggr3);
                aggr3.put("field", aggregationMetaData.getFieldName());
            }
            unit = newValue;
            aggregationMetaData = aggregationMetaData.getAggregationMetaData();

        } while (aggregationMetaData != null);
        return aggr;
    }

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @param queryMetaDatas 必要查询元数据
     * @return 数据封装Map对象
     * @description 更具必要查询条件拼接查询条件
     */
    private Map<String,Object> dealQuery (List<QueryMetadata.MustQueryMetaData> queryMetaDatas) {
        Map<String,Object> bool = new HashMap<String,Object>();
        Map<String,Object>  must = new HashMap<String,Object>();
        List<Object> mustObject = new ArrayList<Object>();
        Map<String,Object> query = new HashMap<String,Object>();
        query.put("query", bool);
        bool.put("bool", must);
        must.put("must", mustObject);

        for(QueryMetadata.MustQueryMetaData queryDetail : queryMetaDatas) {
            Map<String,Object> contentType = new HashMap<String,Object>();
            Map<String,Object> contentFileName = new HashMap<String,Object>();
            contentType.put(queryDetail.getFilterDo(), contentFileName);

            if(queryDetail.getSymbol().get(0).equals("equals")){
                contentFileName.put(queryDetail.getFiledName(), queryDetail.getFiledValue().get(0));
            }else if(queryDetail.getSymbol().size() > 1) {
                List<String> symbol = queryDetail.getSymbol();
                List<String> symbolValue = queryDetail.getFiledValue();
                Map<String,Object> range = new HashMap<String,Object>();
                Map<String,Object> rangeContent = new HashMap<String,Object>();
                contentType.put("range", range);
                range.put(queryDetail.getFiledName(), rangeContent);
                for(int i = 0; i < symbol.size(); i++) {
                    rangeContent.put(symbol.get(i), symbolValue.get(i));
                }

            }
            mustObject.add(contentType);
        }
        return query;
    }
}
