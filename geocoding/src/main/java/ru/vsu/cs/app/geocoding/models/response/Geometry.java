package ru.vsu.cs.app.geocoding.models.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@JsonDeserialize
@Getter
@Setter
public class Geometry {

    /**
     * [0] - lon
     * [1] - lat
     */
    private Double[] coordinates;

    public Double getLon(){
        return coordinates[0];
    }

    public Double getLat(){
        return coordinates[1];
    }

}
