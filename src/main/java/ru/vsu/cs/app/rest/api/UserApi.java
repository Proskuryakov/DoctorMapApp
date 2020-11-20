package ru.vsu.cs.app.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.app.services.internal.UserService;
import ru.vsu.cs.app.services.models.User;

@RestController
@RequestMapping(
        value = "/users",
        produces = "application/json"
)
public class UserApi {

    private static final Logger logger = LoggerFactory.getLogger(UserApi.class);
    private final UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "/{id}")
    User getById(@PathVariable("id") Long id) {
        final var user = userService.getById(id);
        if (user == null) {
            logger.error("Get Error. Object with such id does not exist");
            //throw new ObjectNotExistsException();
            // todo Сделать ловлю ошибок и отправка сообщения об ошибке в ответ на запрос
        }

        logger.info("Return {} by id = {}", user, id);
        return user;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    User createUser(@RequestBody final User user) {
        logger.info("Create user: {}", user);
        return userService.createUser(user);
    }

}
