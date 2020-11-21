package ru.vsu.cs.app.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.vsu.cs.app.db.models.UserModel;
import ru.vsu.cs.app.db.repositories.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);

    private final UserRepository userRepository;
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public AuthenticationSuccessHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);

        var object = authentication.getPrincipal();

        if (!(object instanceof UserDetails)) {
            logger.error("Expected UserDetails but got {}", object.getClass());
            throw new ServletException("Unexpected authorization class");
        }

        UserModel userModel = userRepository.findByEmail(((UserDetails) object).getUsername());

        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(
                                userModel,
                                null,
                                Set.of(new SimpleGrantedAuthority("ROLE_" + userModel.getUserRole().getName()))
                        )
                );

        OBJECT_MAPPER.writeValue(response.getWriter(), userModel);
    }

}
