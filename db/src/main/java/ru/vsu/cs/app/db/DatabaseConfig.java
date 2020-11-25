package ru.vsu.cs.app.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = DatabaseConfig.class)
@MapperScan(basePackages = "ru.vsu.cs.app.db.repositories")
public class DatabaseConfig {
}
