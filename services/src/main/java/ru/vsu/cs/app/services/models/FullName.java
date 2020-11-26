package ru.vsu.cs.app.services.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonDeserialize
@Getter
@Setter
@ToString
public class FullName {

    String surname;
    String namePatronymic;

}
