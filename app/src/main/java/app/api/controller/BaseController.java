package app.api.controller;

import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import app.api.exception.NotFoundException;
import app.api.service.BaseService;

/**
 * Abstract controller.
 * 
 * @author ground0state
 * @param <T> type of entity
 * @param <S> type of ID
 */
public abstract class BaseController<T, S> {

    /**
     * Abstract service.
     */
    protected final BaseService<T, S> service;

    BaseController(BaseService<T, S> service) {
        this.service = service;
    }

    /**
     * Request specific entity.
     * 
     * @param id entity's id
     * @return entity
     */
    @RequestMapping(method = RequestMethod.GET, value = "{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public T get(@PathVariable("id") S id) {
        Optional<T> result = service.findById(id);
        result.orElseThrow(() -> new NotFoundException("Resource Not Found."));
        return result.get();
    }

    /**
     * Request all entities.
     * 
     * @return list of entity
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Iterable<T> get() {
        return service.findAll();
    }

    /**
     * Create an entity.
     * 
     * @param entity an entity to register
     * @return the registered entity
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public T post(@Validated @RequestBody T entity) {
        return service.save(entity);
    }

    /**
     * Delete an entity.
     * 
     * @param id entity's id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable("id") S id) {
        service.deleteById(id);
    }
}
