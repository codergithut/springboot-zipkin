package cn.gtmap.log.service;

import cn.gtmap.log.util.ElasticSearchRestClient;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/11
 * @description
 */
public class ElasticSearchInsertData {

    public static String elasticInsertDataToClient(Map<String, Object> data, String index, String type, RestClient client) throws IOException {

        String endponit = "/" + index + "/" + type + "/";
        Date date = new Date();
        data.put("date",date);
        return ElasticSearchRestClient.elasticDo(data, endponit, client, "POST");
    }

}
