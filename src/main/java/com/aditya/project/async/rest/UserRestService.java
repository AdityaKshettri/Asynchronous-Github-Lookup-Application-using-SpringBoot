package com.aditya.project.async.rest;

import com.aditya.project.async.dto.UserDto;
import com.aditya.project.async.dto.UsersDto;
import com.aditya.project.async.model.User;
import com.aditya.project.async.mapper.UserMapper;
import com.aditya.project.async.model.UserEntity;
import com.aditya.project.async.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRestService {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserDto getUserDetails(String username) {
        Optional<UserEntity> userEntity = userService.findByUsername(username);
        if(userEntity.isPresent()) {
            return userMapper.map(userEntity.get());
        } else {
            User user = userService.findGithubUser(username);
            UserEntity newUserEntity = userMapper.map(user, username);
            userService.save(newUserEntity);
            return userMapper.map(newUserEntity);
        }
    }

    public UsersDto getAllUsers() {
        List<UserDto> userDtos = userMapper.map(userService.findAll());
        return new UsersDto(userDtos);
    }

    public void deleteUser(String username) {
        Optional<UserEntity> userEntity = userService.findByUsername(username);
        if(userEntity.isPresent()) {
            userService.delete(userEntity.get());
        }
    }
}
