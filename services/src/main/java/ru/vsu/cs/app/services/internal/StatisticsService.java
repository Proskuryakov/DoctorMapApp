package ru.vsu.cs.app.services.internal;

import ru.vsu.cs.app.services.models.CitySickCount;

import java.util.List;
import java.util.Map;
public interface StatisticsService {

    List<CitySickCount> getCityStatistics(Map<String, String> parameters);

}
