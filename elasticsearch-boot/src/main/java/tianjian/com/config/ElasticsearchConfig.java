package tianjian.com.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

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


    @Bean
    public TransportClient elasticsearchClient(){   //向spring注入es的客户端操作对象
        TransportClient transportClient = null;

        Map<String, String> map = new HashMap<String, String>();
        map.put("cluster.name", "elastictest");
        Settings.Builder settings = Settings.builder().put(map);
        try {

            transportClient = new PreBuiltTransportClient(settings.build())
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        } catch (UnknownHostException e) {
            System.out.println("创建elasticsearch客户端失败");
        }
        System.out.println("创建elasticsearch客户端成功");

        return transportClient;

    }

}
