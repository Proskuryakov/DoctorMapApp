package ru.vsu.cs.app.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.app.db.models.AddressModel;
import ru.vsu.cs.app.db.models.SickModel;
import ru.vsu.cs.app.db.models.SickWithAddressModel;
import ru.vsu.cs.app.db.repositories.SickRepository;
import ru.vsu.cs.app.services.internal.AddressService;
import ru.vsu.cs.app.services.internal.IllnessService;
import ru.vsu.cs.app.services.internal.SickService;
import ru.vsu.cs.app.services.mappers.AddressMapper;
import ru.vsu.cs.app.services.mappers.SickMapper;
import ru.vsu.cs.app.services.models.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SickServiceImpl implements SickService {

    private final AddressService addressService;
    private final IllnessService illnessService;
    private final SickRepository sickRepository;
    private final SickMapper sickMapper;
    private final AddressMapper addressMapper;

    @Autowired
    public SickServiceImpl(
            AddressService addressService,
            IllnessService illnessService,
            SickRepository sickRepository,
            SickMapper sickMapper,
            AddressMapper addressMapper
    ) {
        this.addressService = addressService;
        this.illnessService = illnessService;
        this.sickRepository = sickRepository;
        this.sickMapper = sickMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public Sick create(Sick sick) {
        var address = addressService.create(sick.getAddress());

        var sickModel = sickMapper.toModel(sick, address.getId());
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
        List<Illness> illnesses = illnessService.getAll(id);

        Sick sick = sickMapper.fromModel(sickRepository.findById(id));

        sick.setIllnesses(illnesses);

        return sick;
    }

    @Override
    public List<Sick> getByParameters(Map<String, String> parameters) {
        Address address = fillAddress(parameters);
        FullName fullName = fillFullName(parameters);
        List<Long> illnessIdList = fillIllnessIdList(parameters);

        AddressModel addressModel = address != null ? addressMapper.toModel(address) : null;
        SickModel sickModel = fullName != null ? sickMapper.toModel(fullName) : null;

        List<SickWithAddressModel> sickModelList =
                sickRepository.getAllByParameters(sickModel, addressModel, illnessIdList);

        List<Sick> sicks = sickMapper.fromModelList(sickModelList);
        concatIllness(parameters, sicks);

        return sicks;
    }

    private String getDecode(Map<String, String> parameters, String key) {
        try {
            return URLDecoder.decode(parameters.get(key), StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            return null;
        }
    }

    private Address fillAddress(Map<String, String> parameters) {
        Address address = new Address();

        address.setRegion(getDecode(parameters, "region"));
        address.setCity(getDecode(parameters, "city"));
        address.setStreet(getDecode(parameters, "street"));
        address.setHouse(getDecode(parameters, "house"));

        if (address.getRegion() == null &&
                address.getCity() == null &&
                address.getHouse() == null &&
                address.getStreet() == null) {
            return null;
        }
        return address;
    }

    private FullName fillFullName(Map<String, String> parameters) {
        FullName fullName = new FullName();

        fullName.setSurname(getDecode(parameters, "surname"));
        String name = getDecode(parameters, "name");
        String patronymic = getDecode(parameters, "patronymic");
        String namePatronymic = "";
        namePatronymic += name != null ? name + " " : "";
        namePatronymic += patronymic != null ? patronymic : "";
        if (namePatronymic.equals("")) namePatronymic = null;
        fullName.setNamePatronymic(namePatronymic);

        if (fullName.getNamePatronymic() == null && fullName.getSurname() == null) {
            return null;
        }

        return fullName;
    }

    private List<Long> fillIllnessIdList(Map<String, String> parameters) {
        String illnessStr = getDecode(parameters, "illness");

        if (illnessStr == null) {
            return null;
        }

        return Arrays.stream(illnessStr.split(" ")).map(Long::parseLong).distinct().collect(Collectors.toList());
    }

    private void concatIllness(Map<String, String> parameters, List<Sick> sicks) {
        if (parameters.containsKey("concat-illness")) {
            sicks.forEach(sick -> sick.setIllnesses(illnessService.getAll(sick.getId())));
        }
    }
}
