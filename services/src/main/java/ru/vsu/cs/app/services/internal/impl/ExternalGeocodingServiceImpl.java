package ru.vsu.cs.app.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.app.geocoding.models.Coordinates;
import ru.vsu.cs.app.geocoding.models.GeoAddress;
import ru.vsu.cs.app.geocoding.services.GeocodingService;
import ru.vsu.cs.app.services.internal.ExternalGeocodingService;
import ru.vsu.cs.app.services.models.ResponseCoordinates;

import java.util.Map;

@Service
public class ExternalGeocodingServiceImpl implements ExternalGeocodingService {

    private final GeocodingService geocodingService;

    @Autowired
    public ExternalGeocodingServiceImpl(GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    @Override
    public ResponseCoordinates getCoordinates(Map<String, String> parameters) {

        if (parameters.isEmpty()) return null;

        GeoAddress geoAddress = new GeoAddress();

        geoAddress.setCountry(parameters.get("country"));
        geoAddress.setState(parameters.get("region"));
        geoAddress.setCity(parameters.get("city"));
        geoAddress.setStreet(parameters.get("street"));
        geoAddress.setHouse(parameters.get("house"));

        Coordinates coordinates = geocodingService.encode(geoAddress);


        if (coordinates == null) return null;

        return new ResponseCoordinates(coordinates.getLat(), coordinates.getLon());
    }

}
