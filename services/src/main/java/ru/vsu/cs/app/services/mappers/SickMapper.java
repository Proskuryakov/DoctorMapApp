package ru.vsu.cs.app.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vsu.cs.app.db.models.IllnessModel;
import ru.vsu.cs.app.db.models.SickModel;
import ru.vsu.cs.app.db.models.SickWithAddressModel;
import ru.vsu.cs.app.services.models.Address;
import ru.vsu.cs.app.services.models.Illness;
import ru.vsu.cs.app.services.models.Sick;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SickMapper {

    @Mapping(target = "id", source = "sickModel.id")
    @Mapping(target = "fullName.surname", source = "sickModel.surname")
    @Mapping(target = "fullName.namePatronymic", source = "sickModel.namePatronymic")
    @Mapping(target = "address.id", source = "sickModel.addressId")
    @Mapping(target = "address.region", source = "address.region")
    @Mapping(target = "address.city", source = "address.city")
    @Mapping(target = "address.street", source = "address.street")
    @Mapping(target = "address.house", source = "address.house")
    @Mapping(target = "address.lat", source = "address.lat")
    @Mapping(target = "address.lon", source = "address.lon")
    Sick fromModel(SickModel sickModel, Address address);

    @Mapping(target = "id", source = "sick.id")
    @Mapping(target = "addressId", source = "newAddressId")
    @Mapping(target = "surname", source = "sick.fullName.surname")
    @Mapping(target = "namePatronymic", source = "sick.fullName.namePatronymic")
    SickModel toSickModel(Sick sick, Long newAddressId);

    @Mapping(target = "id", source = "sickWithAddressModel.id")
    @Mapping(target = "fullName.surname", source = "sickWithAddressModel.surname")
    @Mapping(target = "fullName.namePatronymic", source = "sickWithAddressModel.namePatronymic")
    @Mapping(target = "address.id", source = "sickWithAddressModel.addressId")
    @Mapping(target = "address.region", source = "sickWithAddressModel.region")
    @Mapping(target = "address.city", source = "sickWithAddressModel.city")
    @Mapping(target = "address.street", source = "sickWithAddressModel.street")
    @Mapping(target = "address.house", source = "sickWithAddressModel.house")
    @Mapping(target = "address.lat", source = "sickWithAddressModel.lat")
    @Mapping(target = "address.lon", source = "sickWithAddressModel.lon")
    Sick fromModels(SickWithAddressModel sickWithAddressModel);

    @Mapping(target = "id", source = "sickModel.id")
    @Mapping(target = "fullName.surname", source = "sickModel.surname")
    @Mapping(target = "fullName.namePatronymic", source = "sickModel.namePatronymic")
    @Mapping(target = "address.id", source = "sickModel.addressId")
    @Mapping(target = "address.region", source = "address.region")
    @Mapping(target = "address.city", source = "address.city")
    @Mapping(target = "address.street", source = "address.street")
    @Mapping(target = "address.house", source = "address.house")
    @Mapping(target = "address.lat", source = "address.lat")
    @Mapping(target = "address.lon", source = "address.lon")
    @Mapping(target = "illnesses", source = "illnesses")
    Sick fromModel(SickModel sickModel, Address address, List<Illness> illnesses);
}
