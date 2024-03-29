package kekstarter.services.impl;

import kekstarter.dto.JsonException;
import kekstarter.dto.ResponseTextDto;
import kekstarter.dto.loginDto.LoginRequestDto;
import kekstarter.dto.loginDto.LoginResponseDto;
import kekstarter.models.User;
import kekstarter.repositories.UsersRepo;
import kekstarter.responseMessage.ResponseMessages;
import kekstarter.security.SecurityHelper;
import kekstarter.security.models.JwtUserDetails;
import kekstarter.security.services.AuthenticationHelper;
import kekstarter.services.AuthenticationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationsServiceImpl implements AuthenticationsService {

    private final UsersRepo usersRepo;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationHelper authenticationHelper;

    @Override
    public LoginResponseDto login(final LoginRequestDto loginRequestDto) {
        try {
            String username = Optional.ofNullable(loginRequestDto.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("Username should be passed."));
            String password = Optional.ofNullable(loginRequestDto.getPassword())
                    .orElseThrow(() -> new BadCredentialsException("Password should be passed."));
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            final Authentication authResult = this.authenticationManager.authenticate(authRequest);
            if (authResult.isAuthenticated()) {
                JwtUserDetails userDetails = (JwtUserDetails) authResult.getPrincipal();
                User user = usersRepo.findById((long) userDetails.getId());
                if (user.getBlocked()) {
                    return new LoginResponseDto(null, new ResponseTextDto(ResponseMessages.USER_BLOCKED), user);
                }
                String token = this.authenticationHelper.generateToken(userDetails.getId());
                return new LoginResponseDto(token, new ResponseTextDto(ResponseMessages.SUCCESS), user);
            } else {
                throw new JsonException("Authentication failed");
            }
        } catch (BadCredentialsException exception) {
            return new LoginResponseDto(null, new ResponseTextDto(ResponseMessages.ERROR), null);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public String getName() {
        Authentication authentication = SecurityHelper.getAuthenticationWithCheck();

        return authentication.getName();
    }

}
