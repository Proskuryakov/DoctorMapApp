package ru.vsu.cs.app.db.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SickModel {

    Long id;
    Long addressId;
    String surname;
    String namePatronymic;

}
