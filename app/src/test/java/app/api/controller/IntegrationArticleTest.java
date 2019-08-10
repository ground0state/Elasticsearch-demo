package app.api.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import app.api.Application;
import app.api.entity.Article;
import app.api.repository.ArticleRepository;


/**
 * Integration test.
 * 
 * @author ground0state
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
public class IntegrationArticleTest {

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    private int port;

    @Autowired
    private ElasticsearchOperations elasticsearchTemplate;

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * Set up for test.
     * 
     * <pre>
     * Create index and register test data.
     * Delete index after each tests.
     * </pre>
     * 
     * @throws IOException if fails to create index
     */
    @Before
    public void setup() throws IOException {
        long id = ThreadLocalRandom.current().nextLong(1, 100000);
        String data = RandomStringUtils.randomAlphabetic(20);

        elasticsearchTemplate.createIndex(Article.class);
        Article article = new Article(id, data);
        articleRepository.save(article);
    }

    /**
     * Shutdown Elasticsearch.
     * 
     * @throws Exception if fails to shutdown Elasticsearch
     */
    @After
    public void teardown() throws Exception {
        elasticsearchTemplate.deleteIndex(Article.class);
    }

    private String createURLWithPort(String uri, int port) {
        return "http://localhost:" + Integer.toString(port) + uri;
    }


    /**
     * Post test.
     * 
     * @throws Exception if fails test
     */
    @Test
    public void testCreateArticle() throws Exception {
        long id = ThreadLocalRandom.current().nextLong(1, 100000);
        String data = RandomStringUtils.randomAlphabetic(20);

        Article article = new Article(id, data);
        HttpEntity<Article> entity = new HttpEntity<Article>(article, headers);
        ResponseEntity<Article> response = restTemplate.exchange(
                createURLWithPort("/api/v1/article", port), HttpMethod.POST, entity, Article.class);
        Article actual = response.getBody();
        assertThat(actual.getContent(), is(data));
    }

}
