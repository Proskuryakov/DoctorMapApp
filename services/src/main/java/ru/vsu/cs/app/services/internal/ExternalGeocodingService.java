package ru.vsu.cs.app.services.internal;

import ru.vsu.cs.app.geocoding.models.Coordinates;
import ru.vsu.cs.app.services.models.ResponseCoordinates;

import java.util.Map;

public interface ExternalGeocodingService {

    ResponseCoordinates getCoordinates(Map<String, String> parameters);

}
