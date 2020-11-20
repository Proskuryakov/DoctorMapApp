package ru.vsu.cs.app.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.vsu.cs.app.db.DatabaseConfig;

@Configuration
@Import(DatabaseConfig.class)
@ComponentScan(basePackageClasses = ServiceConfig.class)
public class ServiceConfig {

}
