package app.api.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import app.api.entity.Article;
import app.api.repository.ArticleRepository;

/**
 * Article service.
 * 
 * @author ground0state
 *
 */
@Service
public class ArticleService extends BaseService<Article, Long> {

    ArticleService(ArticleRepository repository) {
        super((CrudRepository<Article, Long>) repository);
    }

}
