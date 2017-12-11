package cn.gtmap.log.domain.message;

import java.util.Date;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/7
 * @description
 */
public class RequestMessage {

    private Date request;

    private String serverName;

    private String ip;

    private String token;

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
