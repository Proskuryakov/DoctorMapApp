package ru.vsu.cs.app.services.internal;

import ru.vsu.cs.app.services.models.Address;
import ru.vsu.cs.app.services.models.FullName;
import ru.vsu.cs.app.services.models.Illness;
import ru.vsu.cs.app.services.models.Sick;

import java.util.List;

public interface SickService {

    Sick create(Sick sick);

    Sick update(Sick sick);

    boolean delete(Long id);

    Sick get(Long id);

    List<Sick> getAll();

    List<Sick> getAllByFullName(FullName fullName);

    List<Sick> getAllByAddress(Address address);

    List<Sick> getAllByIllness(List<Illness> illnesses);

    List<Sick> getAll(Sick sick, List<Illness> illnesses);

    List<Sick> getAll(Sick sick);

}
