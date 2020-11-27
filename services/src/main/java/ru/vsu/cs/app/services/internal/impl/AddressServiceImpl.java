package ru.vsu.cs.app.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.app.db.models.AddressModel;
import ru.vsu.cs.app.db.repositories.AddressRepository;
import ru.vsu.cs.app.services.internal.AddressService;
import ru.vsu.cs.app.geocoding.services.GeocodingService;
import ru.vsu.cs.app.services.mappers.AddressMapper;
import ru.vsu.cs.app.services.models.Address;
import ru.vsu.cs.app.geocoding.models.Coordinates;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final GeocodingService geocodingService;
    private final AddressMapper addressMapper;

    @Autowired
    public AddressServiceImpl(
            AddressRepository addressRepository,
            GeocodingService geocodingService,
            AddressMapper addressMapper
    ) {
        this.addressRepository = addressRepository;
        this.geocodingService = geocodingService;
        this.addressMapper = addressMapper;
    }

    @Override
    public Address create(Address address) {
        var createdAddress = addressRepository.findByAddress(addressMapper.toModel(address));

        if (createdAddress == null) {
            Coordinates coordinates = geocodingService.encode(addressMapper.toGeoModel(address));
            AddressModel addressModel = addressMapper.toModel(address, coordinates);
            createdAddress = addressRepository.create(addressModel);
        }

        return addressMapper.fromModel(createdAddress);
    }

    @Override
    public boolean delete(Long addressId) {
        AddressModel addressModel = addressRepository.findById(addressId);

        if (addressModel == null) return false;

        addressRepository.delete(addressId);
        return true;
    }

    @Override
    public Address update(Address address) {

        Long count = getSickCount(address.getId());

        if (count > 1) {
            return create(address);
        }

        var createdAddress = addressRepository.findByAddress(addressMapper.toModel(address));

        if (createdAddress != null) {
            return addressMapper.fromModel(createdAddress);
        }

        Coordinates coordinates = geocodingService.encode(addressMapper.toGeoModel(address));
        AddressModel addressModel = addressMapper.toModel(address, coordinates);
        addressRepository.update(addressModel);
        address.setLat(coordinates.getLat());
        address.setLon(coordinates.getLon());

        return address;
    }

    @Override
    public Address get(Long id) {
        AddressModel addressModel = addressRepository.findById(id);
        return addressMapper.fromModel(addressModel);
    }

    @Override
    public Long getSickCount(Long addressId) {
        return addressRepository.getSickCount(addressId);
    }
}
