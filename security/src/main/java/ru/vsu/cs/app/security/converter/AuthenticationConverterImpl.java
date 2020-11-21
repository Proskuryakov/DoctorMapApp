package ru.vsu.cs.app.security.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import ru.vsu.cs.app.security.models.EmailPasswordRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenticationConverterImpl implements AuthenticationConverter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationConverterImpl.class);

    @Override
    public Authentication convert(HttpServletRequest request) {

        EmailPasswordRequest emailPasswordRequest;

        try (var inputStream = request.getInputStream()) {
            emailPasswordRequest = OBJECT_MAPPER.readValue(inputStream, EmailPasswordRequest.class);
        } catch (IOException e) {
            logger.error("Cannot read login request", e);
            return null;
        }

        return new UsernamePasswordAuthenticationToken(
                emailPasswordRequest.getEmail(),
                emailPasswordRequest.getPassword()
        );

    }
}
