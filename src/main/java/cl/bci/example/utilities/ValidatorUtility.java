package cl.bci.example.utilities;

import cl.bci.example.exceptions.EmailException;
import cl.bci.example.exceptions.PasswordException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidatorUtility {

    @Value ("${password.expression.regexp}")
    private String expression;

    public void validateEmail (String email)
    throws EmailException {

        if (!EmailValidator.getInstance ().isValid (email)) {

            throw new EmailException ();

        }

    }

    public void validatePassword (String password)
    throws PasswordException {

        Pattern pattern = Pattern.compile (this.expression);

        Matcher matcher = pattern.matcher (password);

        if (!matcher.matches ()) {

            throw new PasswordException ();

        }

    }

}