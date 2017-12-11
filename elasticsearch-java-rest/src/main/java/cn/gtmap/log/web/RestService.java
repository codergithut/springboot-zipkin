package cn.gtmap.log.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/5
 * @description
 */
@RestController
public class RestService {


    @RequestMapping("/getUrl")
    public String getUrl() {
        return null;
    }
}
