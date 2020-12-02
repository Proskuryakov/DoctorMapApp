package ru.vsu.cs.app.services.internal;

import ru.vsu.cs.app.services.models.Illness;

import java.util.List;
import java.util.Map;

public interface IllnessService {

    Illness create(Illness illness);

    Illness get(Long id);

    boolean delete(Long id);

    Illness update(Illness illness);

    List<Illness> getAll();

    List<Illness> getAll(Long sickId);

    List<Illness> getAll(Map<String, String> parameters);

}
