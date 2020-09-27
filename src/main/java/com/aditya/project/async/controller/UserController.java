package com.aditya.project.async.controller;

import com.aditya.project.async.model.User;
import com.aditya.project.async.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public User getUserdetails(@PathVariable String username) {
        return userService.findGithubUser(username);
    }
}
