package ru.vsu.cs.app.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.app.db.repositories.IllnessRepository;
import ru.vsu.cs.app.db.repositories.SickRepository;
import ru.vsu.cs.app.services.internal.AddressService;
import ru.vsu.cs.app.geocoding.services.GeocodingService;
import ru.vsu.cs.app.services.internal.SickService;
import ru.vsu.cs.app.services.mappers.IllnessMapper;
import ru.vsu.cs.app.services.mappers.SickMapper;
import ru.vsu.cs.app.services.models.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SickServiceImpl implements SickService {

    private final SickRepository sickRepository;
    private final IllnessRepository illnessRepository;
    private final GeocodingService geocodingService;
    private final AddressService addressService;
    private final SickMapper sickMapper;
    private final IllnessMapper illnessMapper;

    @Autowired
    public SickServiceImpl(
            SickRepository sickRepository,
            IllnessRepository illnessRepository, GeocodingService geocodingService,
            AddressService addressService,
            SickMapper sickMapper,
            IllnessMapper illnessMapper) {
        this.sickRepository = sickRepository;
        this.illnessRepository = illnessRepository;
        this.geocodingService = geocodingService;
        this.addressService = addressService;
        this.sickMapper = sickMapper;
        this.illnessMapper = illnessMapper;
    }

    @Override
    public Sick create(Sick sick) {
        var address = addressService.create(sick.getAddress());

        var sickModel = sickMapper.toSickModel(sick, address.getId());
        sickModel = sickRepository.create(sickModel);

        var illnessesList = sick.getIllnesses().stream().map(Illness::getId).collect(Collectors.toList());

        sickRepository.addIllnessDependence(sickModel.getId(), illnessesList);

        return sickMapper.fromModel(sickModel, address, sick.getIllnesses());
    }

    @Override
    public Sick update(Sick sick) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Sick get(Long id) {
        List<Illness> illnesses = illnessMapper.fromModel(illnessRepository.getAllBySickId(id));

        Sick sick = sickMapper.fromModels(sickRepository.findById(id));

        sick.setIllnesses(illnesses);

        return sick;
    }

    @Override
    public List<Sick> getAll() {
        return null;
    }

    @Override
    public List<Sick> getAllByFullName(FullName fullName) {
        return null;
    }

    @Override
    public List<Sick> getAllByAddress(Address address) {
        return null;
    }

    @Override
    public List<Sick> getAllByIllness(List<Illness> illnesses) {
        return null;
    }

    @Override
    public List<Sick> getAll(Sick sick, List<Illness> illnesses) {
        return null;
    }

    @Override
    public List<Sick> getAll(Sick sick) {
        return null;
    }
}
