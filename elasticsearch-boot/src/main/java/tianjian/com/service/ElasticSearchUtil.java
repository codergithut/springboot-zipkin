package tianjian.com.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MultiMatchQuery;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/9/21
 * @description ElasticSearch java 工具方法，实现了获取特定字段的统计信息
 */
@Service
@Component
public class ElasticSearchUtil {

    @Autowired
    TransportClient client;


    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @param data 需要分析的结果
     * @return 包含统计信息的HashMap数据
     * @description 分析统计查询到的数据
     */
    private static Map<String,String> analysisJsonData(String data) {
        Map<String,String> statisticalData = new HashMap<String,String>();
        JSONObject jsonObject = JSON.parseObject(data);
        JSONObject t = (JSONObject) jsonObject.get("aggregations");
        JSONObject tt = (JSONObject)t.get("count");
        JSONArray ttt = (JSONArray) tt.get("buckets");
        for(JSONObject json : ttt.toJavaList(JSONObject.class)) {
            statisticalData.put(json.get("key").toString(), json.get("doc_count").toString());
        }
        return statisticalData;
    }

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @param statistical 需要统计的字段
     * @param index 需要统计的索引
     * @return ElasticSearch 统计查询结果
     * @description 根据统计字段和统计索引查询结果并返回
     */
    public Map<String,String> StructuringAggregation(String statistical, String index, int size) throws UnknownHostException {
        SearchResponse sr = client.prepareSearch(index)
                .addAggregation(
                        AggregationBuilders.terms("count").field(statistical)
                ).execute().actionGet();
        Map<String,String> data = analysisJsonData(sr.toString());

        for(Map.Entry<String,String> detail : data.entrySet()) {
            System.out.println("key ：" + detail.getKey() + " value ：" + detail.getValue());
        }
        return data;
    }

    public String getStatisticInfo(String statistical,String index) throws UnknownHostException {
        //AggregationBuilders.count(statistical)
        SearchResponse sr = client.prepareSearch(index)
                .addAggregation(
                        AggregationBuilders.terms("count").field(statistical)
                ).execute().actionGet();
        Map<String,String> data = analysisJsonData(sr.toString());
        for(Map.Entry<String,String> detail : data.entrySet()) {
            System.out.println("key ：" + detail.getKey() + " value ：" + detail.getValue());
        }
        System.out.println(sr);
        return sr.toString();
    }

    public String getStatisticDetailInfo(String statistical,String index, Map<String,String> param) throws UnknownHostException {
        List<PrefixQueryBuilder> prefixQueryBuilders = new ArrayList<PrefixQueryBuilder>();
        for(Map.Entry<String,String> entry : param.entrySet()) {
            prefixQueryBuilders.add(QueryBuilders.prefixQuery(entry.getKey(), entry.getValue()));
        }
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);

        if(prefixQueryBuilders.size() > 0) {
            for(PrefixQueryBuilder prefixQueryBuilder : prefixQueryBuilders) {
                searchRequestBuilder.setQuery(prefixQueryBuilder);
            }
        }

        SearchResponse sr = searchRequestBuilder
                .addAggregation(
                        AggregationBuilders.terms("count").field(statistical)
                ).execute().actionGet();
        Map<String,String> data = analysisJsonData(sr.toString());
        for(Map.Entry<String,String> detail : data.entrySet()) {
            System.out.println("key ：" + detail.getKey() + " value ：" + detail.getValue());
        }

        return sr.toString();
    }
}
