package cn.gtmap.log.domain.message;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/7
 * @description
 */
public class ResponseMessage {

    private String returnCode;

    private String staut;

    private String requestInfo;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getStaut() {
        return staut;
    }

    public void setStaut(String staut) {
        this.staut = staut;
    }

    public String getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(String requestInfo) {
        this.requestInfo = requestInfo;
    }
}
