package cn.gtmap.log.service;

import cn.gtmap.log.domain.query.QueryMetadata;
import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/8
 * @description 根据搜素条件进行数据ES数据搜索
 */
public interface ExplainQueryMetadata {
    String getSearchResponseByMetatdata(QueryMetadata queryMetadata) throws IOException;
}
