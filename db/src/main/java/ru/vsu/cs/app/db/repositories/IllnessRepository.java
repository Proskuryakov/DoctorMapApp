package ru.vsu.cs.app.db.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.vsu.cs.app.db.models.IllnessModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface IllnessRepository {

    Optional<IllnessModel> findById(@Param("id") Long id);

    IllnessModel create(IllnessModel model);

    void update(IllnessModel model);

    void delete(@Param("id") Long id);

    List<IllnessModel> getAll();

    List<IllnessModel> getAllBySickId(@Param("sickId") Long sickId);

    List<IllnessModel> getAllLikeName(@Param("name") String name);
}
