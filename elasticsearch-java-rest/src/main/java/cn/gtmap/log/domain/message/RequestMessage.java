package cn.gtmap.log.domain.message;

import java.util.Date;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/7
 * @description 基础消息请求
 */
public class RequestMessage {

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @description 请求时间
     */
    private Date request;

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @description 请求服务的名称
     */
    private String serverName;

    private String ip;

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @description 请求的token如果需要的话
     */
    private String token;

    /* set get 方法 */
    public Date getRequest() {
        return request;
    }

    public void setRequest(Date request) {
        this.request = request;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
