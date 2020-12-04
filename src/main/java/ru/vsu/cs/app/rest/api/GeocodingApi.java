package ru.vsu.cs.app.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.app.rest.exception.ObjectNotExistsException;
import ru.vsu.cs.app.services.internal.ExternalGeocodingService;
import ru.vsu.cs.app.services.models.ResponseCoordinates;
import ru.vsu.cs.app.services.models.User;

import java.util.Map;

@RestController
@RequestMapping(
        produces = "application/json"
)
public class GeocodingApi {


    private static final Logger logger = LoggerFactory.getLogger(GeocodingApi.class);
    private final ExternalGeocodingService externalGeocodingService;

    @Autowired
    public GeocodingApi(ExternalGeocodingService externalGeocodingService) {
        this.externalGeocodingService = externalGeocodingService;
    }

    @GetMapping(path = "/geocoding")
    ResponseCoordinates getCoordinates(@RequestParam Map<String, String> parameters) {

        ResponseCoordinates coordinates = externalGeocodingService.getCoordinates(parameters);
        if (coordinates == null) {
            logger.error("Get Error. Object with such parameters does not exist");
            throw new ObjectNotExistsException();
        }

        logger.info("Return {} by parameters = {}", coordinates, parameters);
        return coordinates;
    }

}
