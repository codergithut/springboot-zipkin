package tianjian.com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tianjian.com.service.ElasticSearchUtil;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/5
 * @description
 */
@RestController
public class RestService {

    @Autowired
    ElasticSearchUtil elasticSearchUtil;

    @RequestMapping("/getUrl")
    public String getUrl() throws UnknownHostException {
        Map<String,String> params = new HashMap<String,String>();
        params.put("name", "http:/mi");
        return elasticSearchUtil.getStatisticDetailInfo("name", "zipkin-2017-11-28", params);
    }
}
