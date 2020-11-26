package ru.vsu.cs.app.geocoding.services;

import ru.vsu.cs.app.geocoding.models.GeoAddress;
import ru.vsu.cs.app.geocoding.models.Coordinates;

public interface GeocodingService {

    Coordinates encode(GeoAddress geoAddress);

}
