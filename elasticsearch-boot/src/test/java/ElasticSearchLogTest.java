import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tianjian.com.ElasticSearchBoot;
import tianjian.com.service.ElasticSearchUtil;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


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
    ElasticSearchUtil elasticSearchUtil;


    @Test
    public void StructuringAggregationTest() throws UnknownHostException {
        Map<String,String> params = new HashMap<String,String>();
        params.put("name", "http:/mi");
        elasticSearchUtil.getStatisticDetailInfo("name", "zipkin-2017-11-28", params);
    }

}
