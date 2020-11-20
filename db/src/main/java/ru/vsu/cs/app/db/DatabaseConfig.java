package ru.vsu.cs.app.db;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackageClasses = DatabaseConfig.class)
@EnableJpaRepositories("ru.vsu.cs.app.db.repositories")
@EntityScan("ru.vsu.cs.app.db.models")
public class DatabaseConfig {
}
