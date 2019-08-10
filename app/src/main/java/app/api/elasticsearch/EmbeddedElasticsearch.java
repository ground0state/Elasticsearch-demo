package app.api.elasticsearch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.annotation.PreDestroy;
import org.codelibs.elasticsearch.runner.ElasticsearchClusterRunner;
import org.codelibs.elasticsearch.runner.ElasticsearchClusterRunner.Configs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Embedded Elasticsearch for unit test.
 * 
 * @author ground0state
 *
 */
@Component
public class EmbeddedElasticsearch {

    @Value("${elasticsearch.cluster.name:elasticsearch}")
    private String clusterName;
    private static final int NUM_OF_NODES = 1;
    private static final String DEFAULT_BASE_PATH = "db/elasticsearch";
    private static ElasticsearchClusterRunner runner;

    /**
     * Start embedded Elasticsearch.
     * 
     * <pre>
     * SpringJUnit4ClassRunner has a feature: it caches all started contexts and destroys them only at the end of running of all test cases.
     * So, if runner is null, runner starts. Unable to start in duplicate.
     * </pre>
     * 
     * @throws IOException if fails to create folder
     */
    public void setup() throws IOException {
        Path basePath = Paths.get(DEFAULT_BASE_PATH);
        Files.createDirectories(basePath);

        if (runner == null) {
            runner = new ElasticsearchClusterRunner();
            Configs config = ElasticsearchClusterRunner.newConfigs();
            runner.build(config.basePath(basePath.toString()).clusterName(clusterName)
                    .numOfNode(NUM_OF_NODES).disableESLogger());
            runner.ensureYellow();
        }
    }

    /**
     * Shutdown embedded Elasticsearch when system stops.
     * 
     * <pre>
     * Shutdown Elasticsearch before spring removes bean from the application context.
     * </pre>
     */
    @PreDestroy
    public void teardown() {
        try {
            runner.close();
        } catch (final IOException e) {
            runner.print(e.getLocalizedMessage());
        }
        runner.clean();
    }

}
