package cn.gtmap.log.common;

import cn.gtmap.log.domain.message.RequestMessage;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/7
 * @description
 */
public class CommonTest {
    @Test
    @Ignore
    public void commonTest() {
        RequestMessage requestMessage = new RequestMessage();
        System.out.println(JSON.toJSONString(requestMessage));

    }

    @Test
    @Ignore
    public void testJavaClient() throws IOException {

        RestClient restClient = RestClient.builder(
                new HttpHost("10.1.1.147", 9200, "http"),
                new HttpHost("10.1.1.147", 9201, "http")).build();
        Map<String, String> params = Collections.emptyMap();
        String jsonString = "{\n" +
                "    \"aggs\" : { \n" +
                "        \"popular_colors\" : { \n" +
                "            \"terms\" : { \n" +
                "              \"field\" : \"name\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        String jsonString1 = "{\n" +
                "    \"query\" : {\n" +
                "        \"wildcard\" : {\n" +
                "            \"name\" : \"http:*\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"aggs\" : {\n" +
                "        \"colors\" : {\n" +
                "            \"terms\" : {\n" +
                "              \"field\" : \"name\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        System.out.println(jsonString);

        HttpEntity entity = new NStringEntity(jsonString1, ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest("POST", "/zipkin-*/_search?size=0", params, entity);
        HttpEntity entity1 = response.getEntity();
        System.out.println(convertStreamToString(entity1.getContent()));

    }

    public String convertStreamToString(InputStream is) {
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
