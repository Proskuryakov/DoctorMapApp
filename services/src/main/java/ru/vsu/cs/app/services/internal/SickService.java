package ru.vsu.cs.app.services.internal;

import ru.vsu.cs.app.services.models.Address;
import ru.vsu.cs.app.services.models.FullName;
import ru.vsu.cs.app.services.models.Illness;
import ru.vsu.cs.app.services.models.Sick;

import java.util.List;
import java.util.Map;

public interface SickService {

    Sick create(Sick sick);

    Sick update(Sick sick);

    boolean delete(Long id);

    Sick get(Long id);

    List<Sick> getByParameters(Map<String, String> parameters);
}
