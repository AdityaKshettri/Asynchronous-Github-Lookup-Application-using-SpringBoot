package com.aditya.project.async.service;

import com.aditya.project.async.model.User;
import com.aditya.project.async.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findGithubUser(String username);

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findAll();

    void save(UserEntity userEntity);

    void delete(UserEntity userEntity);
}
