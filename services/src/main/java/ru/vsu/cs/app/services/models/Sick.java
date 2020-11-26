package ru.vsu.cs.app.services.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@JsonDeserialize
@Getter
@Setter
@ToString
public class Sick {

    Long id;
    FullName fullName;
    Address address;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<Illness> illnesses;

}
