package com.aditya.project.async.service;

import com.aditya.project.async.exception.ErrorCatalog;
import com.aditya.project.async.exception.ServiceException;
import com.aditya.project.async.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class GithubLookupService {

    private final RestTemplate restTemplate;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<User> findUser(String username) {
        log.info("Looking for : " + username);
        String url = String.format("https://api.github.com/users/%s", username);
        User results = restTemplate.getForObject(url, User.class);
        if(results == null) {
            throw new ServiceException(ErrorCatalog.GITHUB_USER_NOT_FOUND_ERROR);
        }
        return CompletableFuture.completedFuture(results);
    }
}
