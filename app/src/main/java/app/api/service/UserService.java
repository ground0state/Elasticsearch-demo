package app.api.service;

import org.springframework.stereotype.Service;
import app.api.entity.User;
import app.api.repository.UserRepository;

/**
 * User service.
 * 
 * @author ground0state
 *
 */
@Service
public class UserService extends BaseService<User, Long> {

    UserService(UserRepository repository) {
        super(repository);
    }
}
