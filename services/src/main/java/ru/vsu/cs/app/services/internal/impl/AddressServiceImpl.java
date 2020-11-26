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
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Address address) {

    }

    @Override
    public Address get(Long id) {
        return null;
    }
}
