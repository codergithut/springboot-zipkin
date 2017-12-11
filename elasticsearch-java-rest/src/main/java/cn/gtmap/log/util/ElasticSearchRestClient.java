package cn.gtmap.log.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/11
 * @description
 */
public class ElasticSearchRestClient {

    public static String elasticDo(Map<String,Object> obj, String urlPoint, RestClient client, String method) throws IOException {
        String str = JSON.toJSONString(obj);
        Map<String, String> params = Collections.emptyMap();

        HttpEntity entity = new NStringEntity(str, ContentType.APPLICATION_JSON);

        Response response = client.performRequest(method, urlPoint, params, entity);
        HttpEntity entity1 = response.getEntity();
        return convertStreamToString(entity1.getContent());
    }


    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "/n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
