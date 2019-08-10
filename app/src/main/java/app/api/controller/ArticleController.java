package app.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.api.entity.Article;
import app.api.service.ArticleService;

/**
 * Article controller.
 * 
 * @author ground0state
 *
 */
@RestController
@RequestMapping("api/v1/article")
public class ArticleController extends BaseController<Article, Long> {

    ArticleController(ArticleService service) {
        super(service);
    }
}
