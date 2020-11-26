package ru.vsu.cs.app.geocoding.models;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Getter
@Setter
public class GeoAddress {

    String country;
    String state;
    String city;
    String street;
    String house;

    @SneakyThrows
    public String getQuery() {
        StringBuilder sb = new StringBuilder();

        if (country != null)
            sb.append("&country=").append(URLEncoder.encode(country, StandardCharsets.UTF_8.name()));
        if (state != null)
            sb.append("&state=").append(URLEncoder.encode(state, StandardCharsets.UTF_8.name()));
        if (city != null)
            sb.append("&city=").append(URLEncoder.encode(city, StandardCharsets.UTF_8.name()));

        String concat = "";
        if (street != null)
            concat += street + " ";
        if (house != null)
            concat += house;
        if (!concat.equals("")) {
            sb.append("&street=").append(URLEncoder.encode(concat, StandardCharsets.UTF_8.name()));
        }

        return sb.toString();
    }

}
