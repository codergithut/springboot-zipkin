package cn.gtmap.log.util;

import cn.gtmap.log.ElasticSearchBoot;
import cn.gtmap.log.domain.message.RequestMessage;
import cn.gtmap.log.domain.message.StatisticsRequestMessage;
import cn.gtmap.log.service.AbstractQueryInfoMessage;
import cn.gtmap.log.service.ElasticSearchInsertData;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/7/10
 * @description 对上面代码进行单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticSearchBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class ElasticSearchLogTest {

    @Autowired
    AbstractQueryInfoMessage abstractQueryInfoMessage;


    @Test
    @Ignore
    public void StructuringAggregationTest() throws IOException {
        StatisticsRequestMessage requestMessage = new StatisticsRequestMessage();
        requestMessage.setUrlName("http:/c*");
        requestMessage.setEndTime(new Date());
        requestMessage.setEndTime(new Date());
        System.out.println(abstractQueryInfoMessage.getRequestMessage(requestMessage).getRequestInfo());
    }

    @Test
    public void TestInsertDataToElasticSearch() throws IOException {
        List<Map<String,Object>> datas = GetCsvDataUtil.getCsvDataUtil("D:\\springbootzikpin\\mircoservice-master\\" +
                "springboot+zipkin\\springboot-zipkin\\common\\src\\main\\resources\\test\\" +
                "xxx.csv", "GB2312");
        RestClient restClient = RestClient.builder(
                new HttpHost("127.0.0.1", 9200, "http")).build();
        System.out.println(ElasticSearchInsertData.elasticInsertDataToClient(datas.get(0), "test1", "type", restClient));
    }

    private RequestMessage initRequestMessage() {
        StatisticsRequestMessage requestMessage = new StatisticsRequestMessage();
        requestMessage.setToken(UUID.randomUUID().toString());
        System.out.println(JSON.toJSONString(requestMessage));
        return requestMessage;
    }

}
