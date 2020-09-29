package com.aditya.project.async.service;

import com.aditya.project.async.dao.UserDao;
import com.aditya.project.async.exception.ErrorCatalog;
import com.aditya.project.async.exception.ServiceException;
import com.aditya.project.async.model.User;
import com.aditya.project.async.model.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final GithubLookupService githubLookupService;

    private final UserDao userDao;

    public User findGithubUser(String username) {
        try {
            CompletableFuture<User> user = githubLookupService.findUser(username);
            if (user.get() == null) {
                throw new ServiceException(ErrorCatalog.GITHUB_USER_NOT_FOUND_ERROR);
            }
            return user.get();
        } catch (ServiceException e) {
            log.error("User not found in Github with username : " + username);
            throw e;
        } catch (Exception e) {
            log.error("Error finding user in Github with username : " + username);
            throw new ServiceException(ErrorCatalog.GITHUB_API_ERROR, e);
        }
    }

    public Optional<UserEntity> findByUsername(String username) {
        try {
            return userDao.findByUsername(username);
        } catch (Exception e) {
            log.error("Error finding user with username : " + username);
            throw new ServiceException(ErrorCatalog.APPLICATION_ERROR, e);
        }
    }

    public List<UserEntity> findAll() {
        try {
            return userDao.findAll();
        } catch (Exception e) {
            log.error("Error finding users");
            throw new ServiceException(ErrorCatalog.APPLICATION_ERROR, e);
        }
    }

    public void save(UserEntity userEntity) {
        try {
            Optional<UserEntity> oldUserEntity = userDao.findByUsername(userEntity.getUsername());
            if (oldUserEntity.isPresent()) {
                throw new ServiceException(ErrorCatalog.USER_ALREADY_EXISTS_ERROR);
            }
            userDao.save(userEntity);
        } catch (ServiceException e) {
            log.error("User already exist with username : " + userEntity.getUsername());
            throw e;
        } catch (Exception e) {
            log.error("Error saving user with username : " + userEntity.getUsername());
            throw new ServiceException(ErrorCatalog.APPLICATION_ERROR, e);
        }
    }

    public void delete(String username) {
        try {
            Optional<UserEntity> userEntity = userDao.findByUsername(username);
            if (userEntity.isEmpty()) {
                throw new ServiceException(ErrorCatalog.USER_NOT_FOUND_ERROR);
            }
            userDao.delete(userEntity.get());
        } catch (ServiceException e) {
            log.error("User not found with username : " + username);
            throw e;
        } catch (Exception e) {
            log.error("Error deleting the user with username : " + username);
            throw new ServiceException(ErrorCatalog.APPLICATION_ERROR, e);
        }
    }
}
