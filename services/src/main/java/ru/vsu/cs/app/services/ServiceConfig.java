package ru.vsu.cs.app.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.vsu.cs.app.db.DatabaseConfig;
import ru.vsu.cs.app.geocoding.GeocodingConfig;
import ru.vsu.cs.app.security.SecurityConfig;

@Configuration
@Import({DatabaseConfig.class, SecurityConfig.class, GeocodingConfig.class})
@ComponentScan(basePackageClasses = ServiceConfig.class)
public class ServiceConfig {
}
