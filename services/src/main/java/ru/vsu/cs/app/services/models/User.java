package ru.vsu.cs.app.services.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.vsu.cs.app.commons.models.UserRole;

@NoArgsConstructor
@Setter
@Getter
@ToString
@JsonDeserialize
public class User {

    private Long id;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
