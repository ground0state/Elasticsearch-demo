package app.api;

import java.io.IOException;
import java.net.InetAddress;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import app.api.elasticsearch.EmbeddedElasticsearch;

/**
 * Application configuration.
 * 
 * @author ground0state
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = "app.api.repository")
@EnableElasticsearchRepositories(basePackages = "app.api.repository")
public class Config {

    @Value("${elasticsearch.cluster.name:elasticsearch}")
    private String clusterName;

    @Value("${elasticsearch.cluster.host:127.0.0.1}")
    private String HOST;

    @Value("${elasticsearch.cluster.transport:9301}")
    private int TRANSPORT;

    private final EmbeddedElasticsearch embeddedElasticsearch;

    /**
     * constructor.
     * 
     * @param embeddedElasticsearch to start embedded Elasticsearch
     */
    @Autowired
    public Config(EmbeddedElasticsearch embeddedElasticsearch) {
        this.embeddedElasticsearch = embeddedElasticsearch;
    }

    /**
     * @return Elasticsearch client
     * @throws IOException if fails to create folder
     */
    @Bean
    public Client client() throws IOException {

        embeddedElasticsearch.setup();

        Settings elasticsearchSettings =
                Settings.builder().put("cluster.name", clusterName).build();

        TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName(HOST), TRANSPORT));
        return client;
    }

    /**
     * @return Elasticsearch template
     * @throws IOException if fails to create folder
     */
    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws IOException {
        return new ElasticsearchTemplate(client());
    }
}
