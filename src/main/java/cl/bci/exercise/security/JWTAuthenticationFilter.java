package cl.bci.exercise.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter
extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    throws AuthenticationException {

        var credentialsEntity = new CredentialsEntity ();

        try {

            credentialsEntity = new ObjectMapper ().readValue (httpServletRequest.getReader (), CredentialsEntity.class);

        } catch (Exception ignored) {
        }

        val usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken (
        credentialsEntity.getEmail (),
        credentialsEntity.getPassword (),
        Collections.emptyList ()
        );

        return getAuthenticationManager ().authenticate (usernamePasswordAuthenticationToken);

    }

    @Override
    protected void successfulAuthentication (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain, Authentication authentication)
    throws IOException, ServletException {

        val userDetails = (UserDetailsImpl) authentication.getPrincipal ();

        String token = TokenUtility.createToken (userDetails.getName (), userDetails.getUsername ());

        httpServletResponse.addHeader ("Authorization", "Bearer " + token);
        httpServletResponse.getWriter ().flush ();

        super.successfulAuthentication (httpServletRequest, httpServletResponse, filterChain, authentication);

    }

}