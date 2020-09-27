package com.aditya.project.async.service;

import com.aditya.project.async.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final GithubLookupService githubLookupService;

    public User findGithubUser(String username) {
        try {
            CompletableFuture<User> user = githubLookupService.findUser(username);
            logger.info("Found User with username : " + username);
            return user.get();
        } catch (Exception e) {
            logger.error("User not found with username : " + username);
            return null;
        }
    }
}
