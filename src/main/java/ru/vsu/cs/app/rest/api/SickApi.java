package ru.vsu.cs.app.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.app.services.internal.SickService;
import ru.vsu.cs.app.services.models.Illness;
import ru.vsu.cs.app.services.models.Sick;

@RestController
@RequestMapping(
        value = "/sick",
        produces = "application/json"
)
public class SickApi {

    private final Logger logger = LoggerFactory.getLogger(IllnessApi.class);
    private final SickService sickService;

    @Autowired
    public SickApi(SickService sickService) {
        this.sickService = sickService;
    }

    @GetMapping(path = "/{id}")
    Sick getById(@PathVariable("id") Long id) {
        var sick = sickService.get(id);

        if (sick == null) {
            logger.error("Get Error. Object with such id does not exist");
            //throw new ObjectNotExistsException();
            // todo Сделать ловлю ошибок и отправка сообщения об ошибке в ответ на запрос
        }

        logger.info("Return {}", sick);
        return sick;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    Sick createIllness(@RequestBody final Sick sick) {
        var createdSick = sickService.create(sick);
        logger.info("Create {}", createdSick);
        return createdSick;
    }

}
