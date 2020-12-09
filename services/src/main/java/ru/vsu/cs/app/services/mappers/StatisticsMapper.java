package ru.vsu.cs.app.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vsu.cs.app.db.models.CitySickCountModel;
import ru.vsu.cs.app.services.models.CitySickCount;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatisticsMapper {

    @Mapping(target = "city", source = "model.city")
    @Mapping(target = "count", source = "model.count")
    CitySickCount fromModel(CitySickCountModel model);

    List<CitySickCount> fromModelsList(List<CitySickCountModel> models);

}
