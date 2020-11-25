package ru.vsu.cs.app.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;
import ru.vsu.cs.app.commons.models.UserRole;
import ru.vsu.cs.app.db.models.ResponseUserModel;
import ru.vsu.cs.app.db.models.UserModel;
import ru.vsu.cs.app.services.models.User;

@Service
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "password", source = "encodePassword")
    @Mapping(target = "fio", source = "user.fio")
    @Mapping(target = "role", source = "user.role")
    UserModel toModel(User user, String encodePassword);

    @Mapping(target = "id", source = "userModel.id")
    @Mapping(target = "email", source = "userModel.email")
    @Mapping(target = "password", source = "userModel.password")
    @Mapping(target = "fio", source = "userModel.fio")
    @Mapping(target = "role", source = "userModel.role")
    User fromModel(UserModel userModel);

    @Mapping(target = "id", source = "model.id")
    @Mapping(target = "email", source = "model.email")
    @Mapping(target = "fio", source = "model.fio")
    @Mapping(target = "role", source = "model.role")
    User fromModel(ResponseUserModel model);

    @Mapping(target = "id", source = "model.id")
    @Mapping(target = "email", source = "model.email")
    @Mapping(target = "fio", source = "model.fio")
    @Mapping(target = "role", source = "role")
    User fromModel(ResponseUserModel model, UserRole role);
}
