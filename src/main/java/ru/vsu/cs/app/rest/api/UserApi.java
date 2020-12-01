package ru.vsu.cs.app.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.app.rest.exception.ObjectNotExistsException;
import ru.vsu.cs.app.services.internal.UserService;
import ru.vsu.cs.app.services.models.User;

@RestController
@RequestMapping(
        produces = "application/json"
)
public class UserApi {

    private static final Logger logger = LoggerFactory.getLogger(UserApi.class);
    private final UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "/users/{id}")
    User getById(@PathVariable("id") Long id) {
        final var user = userService.getById(id);
        if (user == null) {
            logger.error("Get Error. Object with such id does not exist");
            throw new ObjectNotExistsException();
        }

        logger.info("Return {} by id = {}", user, id);
        return user;
    }

    @PostMapping(path = "/users", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    User createUser(@RequestBody final User user) {
        User createdUser = userService.createUser(user);
        logger.info("Create user: {}", createdUser);
        return createdUser;
    }

    @GetMapping(path = "auth/profile")
    public User getCurrentUser(Authentication authentication) {
        if (authentication == null) return null;

        User currentUser = userService.getCurrentUser(authentication.getPrincipal());

        if (currentUser == null) {
            logger.info("Error");
            return null;
//            throw new ObjectNotExistsException();
        }

        logger.info("Return current {}", currentUser);
        return currentUser;
    }

}
