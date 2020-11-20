package ru.vsu.cs.app.services.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vsu.cs.app.db.models.UserRole;

@NoArgsConstructor
@Getter
@Setter
@JsonDeserialize
public class User {

    private Long id;
    private String email;
    private String password;
    private String fio;
    private UserRole role;

    public User(String email, String password, String fio, UserRole role) {
        this.id = null;
        this.email = email;
        this.password = password;
        this.fio = fio;
        this.role = role;
    }

}
