package app.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.api.entity.User;


/**
 * User repository.
 * 
 * @author ground0state
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
