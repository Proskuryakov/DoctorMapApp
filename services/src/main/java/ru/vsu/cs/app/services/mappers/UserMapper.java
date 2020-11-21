package ru.vsu.cs.app.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;
import ru.vsu.cs.app.db.models.UserModel;
import ru.vsu.cs.app.services.models.User;

@Service
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "password", source = "encodePassword")
    @Mapping(target = "fio", source = "user.fio")
    UserModel toModel(User user, String encodePassword);

    @Mapping(target = "id", source = "userModel.id")
    @Mapping(target = "email", source = "userModel.email")
    @Mapping(target = "password", source = "userModel.password")
    @Mapping(target = "fio", source = "userModel.fio")
    @Mapping(target = "role", source = "userModel.userRole.name")
    User fromModel(UserModel userModel);

}
