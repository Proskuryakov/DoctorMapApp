package ru.vsu.cs.app.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SecurityConfig.class)
public class SecurityConfig {
}
