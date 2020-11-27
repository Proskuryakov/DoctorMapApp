package ru.vsu.cs.app.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.app.services.internal.SickService;
import ru.vsu.cs.app.services.models.Sick;

import java.util.List;
import java.util.Map;

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

    @GetMapping
    List<Sick> getByParameters(@RequestParam Map<String, String> parameters) {
        return sickService.getByParameters(parameters);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    Sick createdSick(@RequestBody final Sick sick) {
        var createdSick = sickService.create(sick);
        logger.info("Create {}", createdSick);
        return createdSick;
    }

    @DeleteMapping(path = "/{id}")
    void deleteSick(@PathVariable("id") Long id) {
        if (sickService.delete(id)) {
            logger.info("Delete sick by id = {}", id);
            return;
        }
        logger.error("Delete Error. Object with such id does not exist");
        //throw new ObjectNotExistsException();
    }

    @PutMapping(consumes = "application/json")
    Sick updateSick(@RequestBody Sick sick) {
        var updateSick = sickService.update(sick);

        if (updateSick == null) {
            logger.error("Update Error. Object with such id does not exist");
            //throw new ObjectNotExistsException();
        }

        logger.info("Update: {}", updateSick);
        return updateSick;
    }

    @PutMapping(path = "/{id}")
    void changeSickIllness(@PathVariable("id") Long id, @RequestParam Map<String, String> parameters) {
        if (sickService.changeIllness(id, parameters)) {
            logger.info("Change illness dependence {} from sick {}", parameters, id);
            return;
        }
        logger.error("Error");
        //throw new ObjectNotExistsException();
    }

}
