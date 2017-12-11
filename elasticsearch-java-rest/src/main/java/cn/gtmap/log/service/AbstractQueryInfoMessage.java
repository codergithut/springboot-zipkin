package cn.gtmap.log.service;

import cn.gtmap.log.domain.message.RequestMessage;
import cn.gtmap.log.domain.message.ResponseMessage;
import cn.gtmap.log.domain.query.QueryMetadata;

import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/7
 * @description
 */
public abstract class AbstractQueryInfoMessage {

    ExplainQueryMetadata searchByQueryMetadata;

    public AbstractQueryInfoMessage(ExplainQueryMetadata searchByQueryMetadata) {
        this.searchByQueryMetadata = searchByQueryMetadata;
    }

    public void setSearchByQueryMetadata(ExplainQueryMetadata searchByQueryMetadata) {
        this.searchByQueryMetadata = searchByQueryMetadata;
    }

    public ResponseMessage getRequestMessage(RequestMessage requestMessage) throws IOException {
        QueryMetadata queryMetadata = converRequestMessage(requestMessage);
        String response = searchByQueryMetadata.getSearchResponseByMetatdata(queryMetadata);
        return analysisSearchResponse(response, requestMessage, response);
    }

    public abstract QueryMetadata converRequestMessage(RequestMessage requestMessage);

    public abstract ResponseMessage analysisSearchResponse(String searchResponse, RequestMessage requestMessage, String data);



}
