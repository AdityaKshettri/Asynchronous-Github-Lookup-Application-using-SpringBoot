package com.aditya.project.async.mapper;

import com.aditya.project.async.dto.UserDto;
import com.aditya.project.async.model.User;
import com.aditya.project.async.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserEntity map(User user, String username) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(username);
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setCompany(user.getCompany());
        userEntity.setBlog(user.getBlog());
        userEntity.setLocation(user.getLocation());
        userEntity.setDescription(user.getBio());
        userEntity.setRepositories(user.getPublic_repos());
        userEntity.setFollowers(user.getFollowers());
        userEntity.setFollowing(user.getFollowing());
        userEntity.setCreatedAt(user.getCreated_at());
        return userEntity;
    }

    public UserDto map(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUsername(userEntity.getUsername());
        userDto.setName(userEntity.getName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setCompany(userEntity.getCompany());
        userDto.setBlog(userEntity.getBlog());
        userDto.setLocation(userEntity.getLocation());
        userDto.setDescription(userEntity.getDescription());
        userDto.setRepositories(userEntity.getRepositories());
        userDto.setFollowers(userEntity.getFollowers());
        userDto.setFollowing(userEntity.getFollowing());
        userDto.setCreatedAt(userEntity.getCreatedAt());
        return userDto;
    }

    public List<UserDto> map(List<UserEntity> userEntities) {
        return userEntities
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
