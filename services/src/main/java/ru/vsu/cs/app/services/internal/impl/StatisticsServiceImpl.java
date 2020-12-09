package ru.vsu.cs.app.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.app.db.models.CitySickCountModel;
import ru.vsu.cs.app.db.repositories.StatisticsRepository;
import ru.vsu.cs.app.services.internal.StatisticsService;
import ru.vsu.cs.app.services.mappers.StatisticsMapper;
import ru.vsu.cs.app.services.models.CitySickCount;

import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final StatisticsMapper statisticsMapper;

    @Autowired
    public StatisticsServiceImpl(
            StatisticsRepository statisticsRepository,
            StatisticsMapper statisticsMapper
    ) {
        this.statisticsRepository = statisticsRepository;
        this.statisticsMapper = statisticsMapper;
    }

    @Override
    public List<CitySickCount> getCityStatistics(Map<String, String> parameters) {
        boolean isDescendingSort;
        Long illnessId;

        if(parameters.get("illness") == null){
            illnessId = null;
        }else{
            try {
                illnessId = Long.valueOf(parameters.get("illness"));
            } catch (Exception e) {
                return null;
            }
        }

        try {
            int intDescendingParameter = Integer.parseInt(parameters.get("descending"));
            isDescendingSort = intDescendingParameter == 1;
        } catch (Exception e) {
            isDescendingSort = true;
        }

        List<CitySickCountModel> citySickCountModels =
                statisticsRepository.getCityIllnessCount(illnessId, isDescendingSort);

        return statisticsMapper.fromModelsList(citySickCountModels);

    }
}
