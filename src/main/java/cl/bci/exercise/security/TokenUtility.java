package cl.bci.exercise.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.val;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

@Service
public class TokenUtility {

    private static Long duration;
    private static String secret;

    public static String createToken (String username, String email) {

        val properties = new Properties ();

        val url = TokenUtility.class.getClassLoader ().getResource ("application.properties");

        try {

            assert url != null;

            properties.load (new FileInputStream (url.getPath ()));

            duration = Long.parseLong (properties.get ("spring.security.duration").toString ());

            secret = properties.get ("spring.security.secret").toString ();

        } catch (IOException ignored) {
        }

        val expirationTime = duration * 1000;

        val expirationDate = new Date (System.currentTimeMillis () + expirationTime);

        val stringObjectMap = new HashMap <String, Object> (0);

        stringObjectMap.put ("username", username);

        return Jwts.builder ()
        .setSubject (email)
        .setExpiration (expirationDate)
        .addClaims (stringObjectMap)
        .signWith (Keys.hmacShaKeyFor (secret.getBytes ()))
        .compact ();

    }

    public static UsernamePasswordAuthenticationToken getAuthentication (String token) {

        try {

            val claims = Jwts.parserBuilder ()
            .setSigningKey (secret.getBytes ())
            .build ()
            .parseClaimsJws (token)
            .getBody ();

            val email = claims.getSubject ();

            return new UsernamePasswordAuthenticationToken (email, null, Collections.emptyList ());

        } catch (JwtException e) {

            return null;

        }

    }

}