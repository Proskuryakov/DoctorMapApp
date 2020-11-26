package ru.vsu.cs.app.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vsu.cs.app.db.models.AddressModel;
import ru.vsu.cs.app.geocoding.models.GeoAddress;
import ru.vsu.cs.app.services.models.Address;
import ru.vsu.cs.app.geocoding.models.Coordinates;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "region", source = "address.region")
    @Mapping(target = "city", source = "address.city")
    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "house", source = "address.house")
    @Mapping(target = "lat", source = "coordinates.lat")
    @Mapping(target = "lon", source = "coordinates.lon")
    AddressModel toModel(Address address, Coordinates coordinates);

    @Mapping(target = "region", source = "address.region")
    @Mapping(target = "city", source = "address.city")
    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "house", source = "address.house")
    AddressModel toModel(Address address);

    @Mapping(target = "region", source = "address.region")
    @Mapping(target = "city", source = "address.city")
    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "house", source = "address.house")
    @Mapping(target = "lat", source = "address.lat")
    @Mapping(target = "lon", source = "address.lon")
    Address fromModel(AddressModel address);

    @Mapping(target = "state", source = "region")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "street", source = "street")
    @Mapping(target = "house", source = "house")
    GeoAddress toGeoModel(Address address);
}
