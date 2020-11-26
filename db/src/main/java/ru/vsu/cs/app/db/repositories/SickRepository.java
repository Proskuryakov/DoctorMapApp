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

    SickWithAddressModel findById(Long id);

    List<SickWithAddressModel> getAllByParameters(
            SickModel sickModel,
            AddressModel addressModel,
            List<Long> illnessIdList
    );

    default List<SickWithAddressModel> getAllByFIO(SickModel sickModel) {
        return getAllByParameters(sickModel, null, null);
    }

    default List<SickWithAddressModel> getAllByAddress(AddressModel addressModel) {
        return getAllByParameters(null, addressModel, null);
    }

    default List<SickWithAddressModel> getAllByIllness(List<Long> illnessIdList) {
        return getAllByParameters(null, null, illnessIdList);
    }

    default List<SickWithAddressModel> getAll() {
        return getAllByParameters(null, null, null);
    }

}
