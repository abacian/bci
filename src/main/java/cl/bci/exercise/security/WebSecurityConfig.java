package cl.bci.exercise.security;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain filterChain (HttpSecurity httpSecurity, AuthenticationManager authenticationManager)
    throws Exception {

        val jwtAuthenticationFilter = new JWTAuthenticationFilter ();

        jwtAuthenticationFilter.setAuthenticationManager (authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl ("/login");

        httpSecurity.authorizeRequests ().antMatchers ("/").permitAll ();
        httpSecurity.authorizeRequests ().antMatchers ("/h2/**", "/h2-console/**").permitAll ();
        httpSecurity.authorizeRequests ().antMatchers ("/swagger/**", "/swagger-ui/**", "/api-docs/**").permitAll ();
        httpSecurity.csrf ().disable ().authorizeRequests ().anyRequest ().authenticated ();
        httpSecurity.httpBasic ();
        httpSecurity.sessionManagement ().sessionCreationPolicy (SessionCreationPolicy.STATELESS);
        httpSecurity.addFilter (jwtAuthenticationFilter).addFilterBefore (jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers ().frameOptions ().sameOrigin ();

        return httpSecurity.build ();

    }

    @Bean
    AuthenticationManager authenticationManager (HttpSecurity httpSecurity)
    throws Exception {

        return httpSecurity.getSharedObject (AuthenticationManagerBuilder.class)
        .userDetailsService (userDetailsService)
        .passwordEncoder (passwordEncoder ())
        .and ()
        .build ();

    }

    @Bean
    PasswordEncoder passwordEncoder () {

        return new BCryptPasswordEncoder ();

    }

}