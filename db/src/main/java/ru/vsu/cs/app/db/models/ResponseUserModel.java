package ru.vsu.cs.app.db.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.vsu.cs.app.commons.models.UserRole;

@Getter
@Setter
@ToString
public class ResponseUserModel {

    Long id;
    String email;
    String fio;
    UserRole role;

}
