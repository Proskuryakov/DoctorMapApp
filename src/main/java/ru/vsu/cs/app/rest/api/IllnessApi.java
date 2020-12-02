package ru.vsu.cs.app.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.app.rest.exception.ObjectNotExistsException;
import ru.vsu.cs.app.services.internal.IllnessService;
import ru.vsu.cs.app.services.models.Illness;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(
        value = "/illness",
        produces = "application/json"
)
public class IllnessApi {

    private final Logger logger = LoggerFactory.getLogger(UserApi.class);
    private final IllnessService illnessService;

    @Autowired
    public IllnessApi(IllnessService illnessService) {
        this.illnessService = illnessService;
    }

    @GetMapping(path = "/{id}")
    Illness getById(@PathVariable("id") Long id) {
        var illness = illnessService.get(id);

        if (illness == null) {
            logger.error("Get Error. Object with such id does not exist");
            //throw new ObjectNotExistsException();
            // todo Сделать ловлю ошибок и отправка сообщения об ошибке в ответ на запрос
        }

        logger.info("Return {}", illness);
        return illness;

    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    Illness createIllness(@RequestBody final Illness illness) {
        var createIllness = illnessService.create(illness);
        logger.info("Create {}", createIllness);
        return createIllness;
    }

    @DeleteMapping(path = "/{id}")
    void deleteIllness(@PathVariable("id") Long id) {
        if (illnessService.delete(id)) {
            logger.info("Delete illness by id = {}", id);
            return;
        }
        logger.error("Delete Error. Object with such id does not exist");
        //throw new ObjectNotExistsException();
    }

    @PutMapping(consumes = "application/json")
    Illness updateIllness(@RequestBody Illness illness) {
        var updateIllness = illnessService.update(illness);

        if (updateIllness == null) {
            logger.error("Update Error. Object with such id does not exist");
            //throw new ObjectNotExistsException();
        }

        logger.info("Update: {}", updateIllness);
        return updateIllness;
    }

    @GetMapping
    List<Illness> getAll(@RequestParam Map<String, String> parameters){
        final var illnessList = illnessService.getAll(parameters);

        if(illnessList == null){
            logger.error("GET request error. Illness do not exist");
            throw new ObjectNotExistsException();
        }
        logger.info("Return all illness");
        return illnessList;
    }

}
