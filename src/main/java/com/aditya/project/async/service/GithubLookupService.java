package com.aditya.project.async.service;

import com.aditya.project.async.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class GithubLookupService {

    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(GithubLookupService.class);

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<User> findUser(String username) throws InterruptedException {
        logger.info("Looking for : " + username);
        String url = String.format("https://api.github.com/users/%s", username);
        User results = restTemplate.getForObject(url, User.class);
        return CompletableFuture.completedFuture(results);
    }
}
