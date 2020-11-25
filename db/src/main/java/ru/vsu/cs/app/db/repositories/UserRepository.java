package ru.vsu.cs.app.db.repositories;

import org.apache.ibatis.annotations.Param;
import ru.vsu.cs.app.db.models.ResponseUserModel;
import ru.vsu.cs.app.db.models.UserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserRepository {

    UserModel getUserModel(@Param("email") String email);

    ResponseUserModel getResponseUserModel(@Param("email") String email);

    ResponseUserModel createUser(UserModel userModel);

    Optional<ResponseUserModel> findById(@Param("id")Long id);
}
