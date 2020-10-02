package com.aditya.project.async.rest;

import com.aditya.project.async.dto.UserDto;
import com.aditya.project.async.dto.UsersDto;
import com.aditya.project.async.mapper.UserMapper;
import com.aditya.project.async.model.User;
import com.aditya.project.async.model.UserEntity;
import com.aditya.project.async.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRestServiceTest {

    private static final String ID = "ID";

    private static final String USERNAME = "USERNAME";

    private static final String NAME = "NAME";

    private static final String EMAIL = "EMAIL";

    private static final String COMPANY = "COMPANY";

    private static final String BLOG = "BLOG";

    private static final String LOCATION = "LOCATION";

    private static final String DESCRIPTION = "DESCRIPTION";

    private static final int REPOSITORIES = 100;

    private static final int FOLLOWERS = 10;

    private static final int FOLLOWING = 20;

    private static final LocalDateTime CREATED_AT = LocalDateTime.now();

    @InjectMocks
    private UserRestService userRestService;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    private static User givenUser() {
        User user = new User();
        user.setId(ID);
        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setCompany(COMPANY);
        user.setBlog(BLOG);
        user.setLocation(LOCATION);
        user.setBio(DESCRIPTION);
        user.setPublic_repos(REPOSITORIES);
        user.setFollowers(FOLLOWERS);
        user.setFollowing(FOLLOWING);
        user.setCreated_at(CREATED_AT);
        return user;
    }

    private static UserEntity givenUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(ID);
        userEntity.setUsername(USERNAME);
        userEntity.setName(NAME);
        userEntity.setEmail(EMAIL);
        userEntity.setCompany(COMPANY);
        userEntity.setBlog(BLOG);
        userEntity.setLocation(LOCATION);
        userEntity.setDescription(DESCRIPTION);
        userEntity.setRepositories(REPOSITORIES);
        userEntity.setFollowers(FOLLOWERS);
        userEntity.setFollowing(FOLLOWING);
        userEntity.setCreatedAt(CREATED_AT);
        return userEntity;
    }

    private static List<UserEntity> givenUserEntities() {
        UserEntity userEntity = givenUserEntity();
        return Collections.singletonList(userEntity);
    }

    private static UserDto givenUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(ID);
        userDto.setUsername(USERNAME);
        userDto.setName(NAME);
        userDto.setEmail(EMAIL);
        userDto.setCompany(COMPANY);
        userDto.setBlog(BLOG);
        userDto.setLocation(LOCATION);
        userDto.setDescription(DESCRIPTION);
        userDto.setRepositories(REPOSITORIES);
        userDto.setFollowers(FOLLOWERS);
        userDto.setFollowing(FOLLOWING);
        userDto.setCreatedAt(CREATED_AT);
        return userDto;
    }

    private static List<UserDto> givenUserDtos() {
        UserDto userDto = givenUserDto();
        return Collections.singletonList(userDto);
    }

    @Test
    public void should_find_user_entity_by_username() {
        //Given
        UserEntity userEntity = givenUserEntity();
        when(userService.findByUsername(USERNAME)).thenReturn(Optional.of(userEntity));
        UserDto userDto = givenUserDto();
        when(userMapper.map(userEntity)).thenReturn(userDto);
        //When
        UserDto actual = userRestService.getUserDetails(USERNAME);
        //Then
        assertThat(actual).isEqualTo(userDto);
    }

    @Test
    public void should_find_github_user_by_username_when_not_found_in_database() {
        //Given
        when(userService.findByUsername(USERNAME)).thenReturn(Optional.empty());
        User user = givenUser();
        when(userService.findGithubUser(USERNAME)).thenReturn(user);
        UserEntity userEntity = givenUserEntity();
        when(userMapper.map(user, USERNAME)).thenReturn(userEntity);
        doNothing().when(userService).save(userEntity);
        UserDto userDto = givenUserDto();
        when(userMapper.map(userEntity)).thenReturn(userDto);
        //When
        UserDto actual = userRestService.getUserDetails(USERNAME);
        //Then
        assertThat(actual).isEqualTo(userDto);
    }

    @Test
    public void should_find_all_users() {
        //Given
        List<UserEntity> userEntities = givenUserEntities();
        when(userService.findAll()).thenReturn(userEntities);
        List<UserDto> userDtos = givenUserDtos();
        when(userMapper.map(userEntities)).thenReturn(userDtos);
        //When
        UsersDto usersDto = userRestService.getAllUsers();
        //Then
        assertThat(usersDto.getUsers()).isEqualTo(userDtos);
    }

    @Test
    public void should_delete_user() {
        //Given
        //When
        userRestService.deleteUser(USERNAME);
        //Then
        verify(userService).delete(USERNAME);
    }
}
