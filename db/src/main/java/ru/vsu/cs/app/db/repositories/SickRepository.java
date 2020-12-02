package ru.vsu.cs.app.db.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.vsu.cs.app.db.models.AddressModel;
import ru.vsu.cs.app.db.models.IllnessModel;
import ru.vsu.cs.app.db.models.SickModel;
import ru.vsu.cs.app.db.models.SickWithAddressModel;

import java.util.List;

@Mapper
public interface SickRepository {

    SickModel create(SickModel sickModel);

    void addIllnessDependence(@Param("sickId") Long sickId, @Param("illnessIdList") List<Long> illnessIdList);

    void update(SickModel sickModel);

    void delete(Long id);

    void deleteIllnessDependence(@Param("sickId") Long sickId, @Param("illnessIdList") List<Long> illnessIdList);

    SickWithAddressModel findById(Long id);

    List<SickWithAddressModel> getAllByParameters(
            @Param("sickModel") SickModel sickModel,
            @Param("addressModel") AddressModel addressModel,
            @Param("illnessIdList") List<Long> illnessIdList,
            @Param("isIllnessTogether") boolean isIllnessTogether);
}
