package ru.vsu.cs.app.geocoding.services.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vsu.cs.app.geocoding.models.GeoAddress;
import ru.vsu.cs.app.geocoding.models.response.Geometry;
import ru.vsu.cs.app.geocoding.models.response.Response;
import ru.vsu.cs.app.geocoding.services.GeocodingService;
import ru.vsu.cs.app.geocoding.models.Coordinates;

import java.net.URL;

@Service
public class GeocodingServiceImpl implements GeocodingService {

    private final ObjectMapper objectMapper;

    @Value("${nominatim.url.search}")
    private String searchUrl;
    @Value("${nominatim.url.start-param}")
    private String startParam;

    @Autowired
    public GeocodingServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Override
    public Coordinates encode(GeoAddress geoAddress) {
        String query = startParam + geoAddress.getQuery();

        URL url = new URL(searchUrl + query);


        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        var response = objectMapper.readValue(url, Response.class);

        if (response != null && response.getFeatures() != null && response.getFeatures().length != 0) {
            Geometry g = response.getFeatures()[0].getGeometry();
            return new Coordinates(g.getLat(), g.getLon());
        }

        return null;
    }


}
