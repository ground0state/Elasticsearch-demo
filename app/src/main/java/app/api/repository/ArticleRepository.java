package app.api.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import app.api.entity.Article;

/**
 * Elasticsearch repository.
 * 
 * @author ground0state
 *
 */
@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {
}
