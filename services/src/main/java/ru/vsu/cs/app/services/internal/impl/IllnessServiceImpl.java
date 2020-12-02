package ru.vsu.cs.app.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.app.db.repositories.IllnessRepository;
import ru.vsu.cs.app.services.internal.IllnessService;
import ru.vsu.cs.app.services.mappers.IllnessMapper;
import ru.vsu.cs.app.services.models.Illness;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class IllnessServiceImpl implements IllnessService {

    private final IllnessRepository illnessRepository;
    private final IllnessMapper illnessMapper;

    @Autowired
    public IllnessServiceImpl(IllnessRepository illnessRepository, IllnessMapper illnessMapper) {
        this.illnessRepository = illnessRepository;
        this.illnessMapper = illnessMapper;
    }

    @Override
    public Illness create(Illness illness) {
        var model = illnessMapper.toModel(illness);
        var res = illnessRepository.create(model);
        return illnessMapper.fromModel(res);
    }

    @Override
    public Illness get(Long id) {

        var model = illnessRepository.findById(id).orElse(null);

        if (model == null) {
            return null;
        }

        return illnessMapper.fromModel(model);
    }

    @Override
    public boolean delete(Long id) {
        var illness = illnessRepository.findById(id).orElse(null);

        if (illness == null)
            return false;

        illnessRepository.delete(id);
        return true;
    }

    @Override
    public Illness update(Illness illness) {

        var model = illnessRepository.findById(illness.getId()).orElse(null);

        if (model == null)
            return null;

        var newModel = illnessMapper.toModel(illness);
        illnessRepository.update(newModel);
        return illness;
    }

    @Override
    public List<Illness> getAll() {
        return illnessMapper.fromModel(illnessRepository.getAll());
    }

    @Override
    public List<Illness> getAll(Long sickId) {
        return illnessMapper.fromModel(illnessRepository.getAllBySickId(sickId));
    }

    @Override
    public List<Illness> getAll(Map<String, String> parameters) {
        if (parameters.isEmpty()) return getAll();
        try {
            String name = URLDecoder.decode(parameters.get("name"), StandardCharsets.UTF_8.name());
            return illnessMapper.fromModel(illnessRepository.getAllLikeName(name));
        } catch (Exception e) {
            return null;
        }
    }
}
