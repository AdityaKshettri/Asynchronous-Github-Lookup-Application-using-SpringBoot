package com.aditya.project.async.controller;

import com.aditya.project.async.dto.UserDto;
import com.aditya.project.async.dto.UsersDto;
import com.aditya.project.async.rest.UserRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRestService userRestService;

    @GetMapping("")
    public UsersDto getAllUsers() {
        return userRestService.getAllUsers();
    }

    @GetMapping("/{username}")
    public UserDto getUserDetails(@PathVariable String username) {
        return userRestService.getUserDetails(username);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userRestService.deleteUser(username);
    }
}
