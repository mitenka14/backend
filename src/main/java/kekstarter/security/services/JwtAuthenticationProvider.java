package kekstarter.security.services;


import kekstarter.models.Users;
import kekstarter.repositories.UsersRepo;
import kekstarter.security.exceptions.ExpiredTokenAuthenticationException;
import kekstarter.security.exceptions.InvalidTokenAuthenticationException;
import kekstarter.security.models.JwtAuthenticationToken;
import kekstarter.security.models.JwtUserDetails;
import kekstarter.security.models.TokenPayload;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final long MILLIS_IN_SECOND = 1000L;

    private final UsersRepo usersRepo;
    private final AuthenticationHelper authenticationHelper;

    @Override
    public Authentication authenticate(final Authentication authRequest) {
        // Getting string token from authentication request object
        String token = StringUtils.trimToNull((String) authRequest.getCredentials());

        //  Deserialize token
        TokenPayload tokenPayload = authenticationHelper.decodeToken(token);

        // Checking if token already expired and throwing an AuthenticationException in this case
        checkIsExpired(tokenPayload.getExp());

        // Getting user id from token
        Long userEntityId = tokenPayload.getUserId();
        if (Objects.isNull(userEntityId)) {
            throw new InvalidTokenAuthenticationException("Token does not contain a user id.");
        }

        // Getting user from database
        Users user = usersRepo.findById(userEntityId)
                .orElseThrow(() -> new InvalidTokenAuthenticationException("Token does not contain existed user id."));

        // Return authenticated Authentication
        JwtUserDetails userDetails = new JwtUserDetails(user);
        return new JwtAuthenticationToken(userDetails);
    }

    private void checkIsExpired(final Long tokenExpirationTime) {
        if ((System.currentTimeMillis() / MILLIS_IN_SECOND) > tokenExpirationTime) {
            throw new ExpiredTokenAuthenticationException();
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}