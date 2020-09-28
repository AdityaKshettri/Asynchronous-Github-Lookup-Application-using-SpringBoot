package com.aditya.project.async.service.impl;

import com.aditya.project.async.dao.UserDao;
import com.aditya.project.async.model.User;
import com.aditya.project.async.model.UserEntity;
import com.aditya.project.async.service.GithubLookupService;
import com.aditya.project.async.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final GithubLookupService githubLookupService;

    private final UserDao userDao;

    @Override
    public User findGithubUser(String username) {
        try {
            CompletableFuture<User> user = githubLookupService.findUser(username);
            log.info("Found User with username : " + username);
            return user.get();
        } catch (Exception e) {
            log.error("User not found with username : " + username);
            return null;
        }
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        try {
            return userDao.findByUsername(username);
        } catch (Exception e) {
            log.error("Error finding user with username : " + username);
            return Optional.empty();
        }
    }

    @Override
    public List<UserEntity> findAll() {
        try {
            return userDao.findAll();
        } catch (Exception e) {
            log.error("Error finding all users");
            return null;
        }
    }

    @Override
    public void save(UserEntity userEntity) {
        try {
            userDao.save(userEntity);
        } catch (Exception e) {
            log.error("Error saving the user");
        }
    }

    @Override
    public void delete(UserEntity userEntity) {
        try {
            userDao.delete(userEntity);
        } catch (Exception e) {
            log.error("Error deleting the user");
        }
    }
}
