package kekstarter.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class ExpiredTokenAuthenticationException extends AuthenticationException {

    public ExpiredTokenAuthenticationException() {
        super("Authentication token is expired.");
    }

}