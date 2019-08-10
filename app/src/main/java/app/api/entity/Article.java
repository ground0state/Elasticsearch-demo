package app.api.entity;

import javax.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Article.
 * 
 * @author ground0state
 *
 */
@Document(indexName = "blog")
public class Article {

    @Id
    private Long id;
    private String content;

    /**
     * Constructor.
     */
    public Article() {}

    /**
     * Constructor.
     * 
     * @param id article id
     * @param content article content
     */
    public Article(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * @return article id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id article id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return article content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content article content
     */
    public void setContent(String content) {
        this.content = content;
    }
}
