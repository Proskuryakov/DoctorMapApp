package ru.vsu.cs.app.db.models;

import lombok.Data;
import ru.vsu.cs.app.commons.models.UserRole;

@Data
public class UserModel {

    Long id;
    String email;
    String password;
    String fio;
    UserRole role;

}
