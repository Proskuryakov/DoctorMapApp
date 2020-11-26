package ru.vsu.cs.app.db.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SickWithAddressModel {

    Long id;
    Long addressId;
    String surname;
    String namePatronymic;
    String region;
    String city;
    String street;
    String house;
    Double lat;
    Double lon;

}
