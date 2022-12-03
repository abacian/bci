package cl.bci.exercise.security;

import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthorizationFilter
extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
    throws ServletException, IOException {

        val bearerToken = httpServletRequest.getHeader ("Authorization");

        if (bearerToken != null && bearerToken.startsWith ("Bearer ")) {

            val token = bearerToken.replace ("Bearer ", "");

            val usernamePasswordAuthenticationToken = TokenUtility.getAuthentication (token);

            SecurityContextHolder.getContext ().setAuthentication (usernamePasswordAuthenticationToken);

        }

        filterChain.doFilter (httpServletRequest, httpServletResponse);

    }

}