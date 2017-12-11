package cn.gtmap.log.domain.message;

import java.util.Date;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/11
 * @description 获取统计信息消息
 */
public class StatisticsRequestMessage extends RequestMessage {

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @description 统计开始时间
     */
    private Date beginTime;

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @description 统计结束时间
     */
    private Date endTime;

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @description 需要查询的正则式
     */
    private String urlName;

    /* set get 方法 */
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }
}
