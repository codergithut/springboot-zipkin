package cn.gtmap.log.config;

import cn.gtmap.log.server.QueryInfoMessageImpl;
import cn.gtmap.log.service.AbstractQueryInfoMessage;
import cn.gtmap.log.service.ExplainQueryMetadata;
import cn.gtmap.log.service.impl.ExplainQueryMetadataImpl;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/5
 * @description
 */
@Configuration
public class ElasticsearchConfig {

    @Value("${spring.elasticsearch.host}")
    private String host;
    @Value("${spring.elasticsearch.port}")
    private int port;

    @Value("${spring.elasticsearch.cluster}")
    private String cluster;


    @Bean
    public RestClient elasticsearchClient(){   //向spring注入es的客户端操作对象
        RestClient restClient = RestClient.builder(
                new HttpHost(host, 9200, "http")).build();
        return restClient;

    }

    @Bean
    public AbstractQueryInfoMessage getAbstractQueryInfoMessage() {
        ExplainQueryMetadata defaultSearchByQueryMetadata = new ExplainQueryMetadataImpl(elasticsearchClient());
        return new QueryInfoMessageImpl(defaultSearchByQueryMetadata);
    }

}
