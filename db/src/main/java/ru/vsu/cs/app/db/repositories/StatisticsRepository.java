package ru.vsu.cs.app.db.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.vsu.cs.app.db.models.CitySickCountModel;

import java.util.List;

@Mapper
public interface StatisticsRepository {

    List<CitySickCountModel> getCityIllnessCount(
            @Param("illnessId") Long illnessId,
            @Param("isDescendingSort") boolean isDescendingSort
    );

}
