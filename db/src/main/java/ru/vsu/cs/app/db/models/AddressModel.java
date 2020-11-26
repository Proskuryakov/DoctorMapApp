package ru.vsu.cs.app.db.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModel {

    Long id;
    String region;
    String city;
    String street;
    String house;
    Double lat;
    Double lon;

}
