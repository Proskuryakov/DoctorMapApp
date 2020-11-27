package ru.vsu.cs.app.db.repositories;

import org.apache.ibatis.annotations.Mapper;
import ru.vsu.cs.app.db.models.AddressModel;

@Mapper
public interface AddressRepository {

    AddressModel create(AddressModel addressModel);

    void delete(Long id);

    void update(AddressModel addressModel);

    AddressModel findByAddress(AddressModel addressModel);

    AddressModel findById(Long id);

    Long getSickCount(Long addressId);
}
