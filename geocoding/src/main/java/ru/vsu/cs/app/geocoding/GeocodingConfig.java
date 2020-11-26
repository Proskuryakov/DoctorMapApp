package ru.vsu.cs.app.geocoding;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackageClasses = GeocodingConfig.class)
public class GeocodingConfig {

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
