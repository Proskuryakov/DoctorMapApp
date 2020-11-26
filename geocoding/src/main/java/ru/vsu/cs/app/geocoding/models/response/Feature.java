package ru.vsu.cs.app.geocoding.models.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@JsonDeserialize
@Getter
@Setter
public class Feature {

    private Geometry geometry;

}
