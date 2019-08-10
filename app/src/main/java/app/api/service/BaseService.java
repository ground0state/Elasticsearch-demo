package app.api.service;

import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import app.api.exception.NotFoundException;

/**
 * Abstract service.
 * 
 * @author ground0state
 *
 * @param <T> type of entity
 * @param <S> type of ID
 */
@Service
public abstract class BaseService<T, S> {

    /**
     * JpaRepository.
     */
    protected final CrudRepository<T, S> repository;

    BaseService(CrudRepository<T, S> repository) {
        this.repository = repository;
    }

    /**
     * Read the specific entity.
     * 
     * <pre>
     * Raise NotFoundException if request entity is nothing.
     * </pre>
     * 
     * @param id entity's id
     * @return entity
     */
    public Optional<T> findById(S id) {
        return repository.findById(id);
    }

    /**
     * Read all entities.
     * 
     * @return list of entity
     */
    public Iterable<T> findAll() {
        return repository.findAll();
    }

    /**
     * Create an entity.
     * 
     * @param entity an entity to register
     * @return the registered entity
     */
    public T save(T entity) {
        return repository.save(entity);
    }

    /**
     * Delete an entity.
     * 
     * @param id entity's id
     */
    public void deleteById(S id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Resource Not Found.");
        }
    }

}
