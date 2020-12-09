package ru.vsu.cs.app.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.app.rest.exception.ObjectNotExistsException;
import ru.vsu.cs.app.services.internal.StatisticsService;
import ru.vsu.cs.app.services.models.CitySickCount;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(
        value = "/statistics",
        produces = "application/json"
)
public class StatisticsApi {

    private final Logger logger = LoggerFactory.getLogger(StatisticsApi.class);
    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsApi(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    List<CitySickCount> getCityStatistics(@RequestParam Map<String, String> parameters) {
        List<CitySickCount> illnessCountList = statisticsService.getCityStatistics(parameters);
        if(illnessCountList == null){
            logger.warn("Error get city statistics");
            throw new ObjectNotExistsException();
        }
        logger.info("Return statistics {}", illnessCountList);
        return illnessCountList;
    }

}
