package cn.gtmap.log.util;

import cn.gtmap.log.ElasticSearchBoot;
import cn.gtmap.log.domain.message.RequestMessage;
import cn.gtmap.log.domain.message.StatisticsRequestMessage;
import cn.gtmap.log.service.AbstractQueryInfoMessage;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.UUID;


/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/7/10
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticSearchBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class ElasticSearchLogTest {

    @Autowired
    AbstractQueryInfoMessage abstractQueryInfoMessage;


    @Test
    public void StructuringAggregationTest() throws IOException {
        StatisticsRequestMessage requestMessage = new StatisticsRequestMessage();
        System.out.println(abstractQueryInfoMessage.getRequestMessage(requestMessage));
    }

    private RequestMessage initRequestMessage() {
        StatisticsRequestMessage requestMessage = new StatisticsRequestMessage();
        requestMessage.setToken(UUID.randomUUID().toString());
        System.out.println(JSON.toJSONString(requestMessage));
        return requestMessage;
    }

}
