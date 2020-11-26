package ru.vsu.cs.app.services.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@JsonDeserialize
@Getter
@Setter
@ToString
public class Address {

    Long id;
    String region;
    String city;
    String street;
    String house;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Double lat;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Double lon;

}
