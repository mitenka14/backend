package kekstarter.security.services;

import kekstarter.models.Users;
import kekstarter.repositories.UsersRepo;
import kekstarter.dto.JsonException;
import kekstarter.security.models.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users byUsername = this.usersRepo.findByUsername(username);

        return Optional.ofNullable(byUsername)
                .map(JwtUserDetails::new)
                .orElseThrow(() -> new JsonException("User nor found."));
    }
}
