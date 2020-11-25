package ru.vsu.cs.app.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vsu.cs.app.db.models.IllnessModel;
import ru.vsu.cs.app.services.models.Illness;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IllnessMapper {

    @Mapping(target = "id", source = "illness.id")
    @Mapping(target = "name", source = "illness.name")
    @Mapping(target = "color", source = "illness.color")
    IllnessModel toModel(Illness illness);

    @Mapping(target = "id", source = "model.id")
    @Mapping(target = "name", source = "model.name")
    @Mapping(target = "color", source = "model.color")
    Illness fromModel(IllnessModel model);

    List<Illness> fromModel(List<IllnessModel> all);
}
