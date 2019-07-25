package kekstarter.controllers;

import kekstarter.dto.ResponseTextDto;
import kekstarter.dto.UsersDto;
import kekstarter.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

    private final UsersService usersService;

    @PostMapping("registration")
    public ResponseTextDto addUser (@RequestBody UsersDto usersDto) {
        return usersService.addUser(usersDto);
    }

    @GetMapping("activation/{code}")
    public void activation(@PathVariable String code) {
        usersService.activateUser(code);
    }

    @GetMapping("{id}")
    public UsersDto getUserById(final @PathVariable long id){
        return usersService.getUserById(id);
    }

    @PostMapping("{id}")
    public ResponseTextDto editUser(@RequestBody UsersDto usersDto, @PathVariable long id) {
        return usersService.editUser(usersDto, id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public void deleteUserById(final @PathVariable long id) {
        usersService.deleteUserById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/userlist")
    public List<UsersDto> getUsers() {
        return usersService.getUsers();
    }
}
