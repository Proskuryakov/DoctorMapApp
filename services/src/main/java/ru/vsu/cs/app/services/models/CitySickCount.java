package ru.vsu.cs.app.services.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonDeserialize
@ToString
public class CitySickCount {

    String city;
    Long count;

}
