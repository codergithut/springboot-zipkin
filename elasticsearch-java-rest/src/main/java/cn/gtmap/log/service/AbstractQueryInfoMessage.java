package cn.gtmap.log.service;

import cn.gtmap.log.domain.message.RequestMessage;
import cn.gtmap.log.domain.message.ResponseMessage;
import cn.gtmap.log.domain.query.QueryMetadata;

import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/7
 * @description 根据请求消息对消息进行拆解和封装，并请求elasticsearch服务数据，并封装返回结果
 */
public abstract class AbstractQueryInfoMessage {

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @description 对消息进行解析服务(已提供了个可以用的，后面继续丰富)
     */
    ExplainQueryMetadata searchByQueryMetadata;

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @param requestMessage 请求的消息对象
     * @return 封装的返回消息对象
     * @description 根据业务进行对数据进行封装，并解析返回结果返回返回消息
     */
    public ResponseMessage getRequestMessage(RequestMessage requestMessage) throws IOException {
        QueryMetadata queryMetadata = converRequestMessage(requestMessage);
        String response = searchByQueryMetadata.getSearchResponseByMetatdata(queryMetadata);
        return analysisSearchResponse(response, requestMessage, response);
    }

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @param requestMessage 需要转换的消息，转换为自定义消息类型
     * @return 自定义查询消息对象
     * @description 根据请求消息封装查询信息源数据
     */
    public abstract QueryMetadata converRequestMessage(RequestMessage requestMessage);

    /**
     * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
     * @param searchResponse
     * @return 响应消息结果
     * @description 根据查询结果封装消息
     */
    public abstract ResponseMessage analysisSearchResponse(String searchResponse, RequestMessage requestMessage, String data);

    /* set get 方法 */
    public AbstractQueryInfoMessage(ExplainQueryMetadata searchByQueryMetadata) {
        this.searchByQueryMetadata = searchByQueryMetadata;
    }

    public void setSearchByQueryMetadata(ExplainQueryMetadata searchByQueryMetadata) {
        this.searchByQueryMetadata = searchByQueryMetadata;
    }



}
