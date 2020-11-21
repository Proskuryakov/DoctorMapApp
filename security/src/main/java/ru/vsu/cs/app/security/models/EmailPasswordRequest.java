package ru.vsu.cs.app.security.models;

import lombok.Getter;

@Getter
public class EmailPasswordRequest {

    private String email;
    private String password;

}
