package ru.vsu.cs.app.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.vsu.cs.app.db.DatabaseConfig;
import ru.vsu.cs.app.db.repositories.UserRepository;
import ru.vsu.cs.app.security.converter.AuthenticationConverterImpl;
import ru.vsu.cs.app.security.handler.AuthenticationSuccessHandlerImpl;
import ru.vsu.cs.app.commons.models.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@Import(DatabaseConfig.class)
@ComponentScan(basePackageClasses = SecurityConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder("", 141_248, 512);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        builder.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        final var authenticationFilter = new AuthenticationFilter(
                authenticationManager(),
                new AuthenticationConverterImpl()
        );

        authenticationFilter.setRequestMatcher(new AntPathRequestMatcher("/auth/login", "POST"));

        authenticationFilter.setSuccessHandler(new AuthenticationSuccessHandlerImpl(userRepository));

        authenticationFilter.setFailureHandler(new AuthenticationFailureHandler() {
            private final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

            @Override
            public void onAuthenticationFailure(
                    HttpServletRequest request,
                    HttpServletResponse response,
                    AuthenticationException exception
            ) {
                logger.error("Error", exception);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        });


        http
                .csrf().csrfTokenRepository(csrfTokenRepository()).and()
                .authorizeRequests()
                .antMatchers("/auth/**").not().authenticated()
                .antMatchers("/admin/**").hasAuthority("ROLE_" + UserRole.ADMIN.name())
                .antMatchers("/**").authenticated()
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .addFilterAfter(authenticationFilter, LogoutFilter.class)
        ;

    }

    private CsrfTokenRepository csrfTokenRepository() {
        var csrfRepo = CookieCsrfTokenRepository.withHttpOnlyFalse();
        csrfRepo.setCookiePath("/");
        return csrfRepo;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedMethods("*")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

}
