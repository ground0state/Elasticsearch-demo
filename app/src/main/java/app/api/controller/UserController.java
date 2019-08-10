package app.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.api.entity.User;
import app.api.service.UserService;

/**
 * User controller.
 * 
 * @author ground0state
 *
 */
@RestController
@RequestMapping("api/v1/user")
public class UserController extends BaseController<User, Long> {

    UserController(UserService service) {
        super(service);
    }

}
