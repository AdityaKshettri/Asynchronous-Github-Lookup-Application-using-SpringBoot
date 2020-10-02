package com.aditya.project.async.mapper;

import com.aditya.project.async.dto.UserDto;
import com.aditya.project.async.model.User;
import com.aditya.project.async.model.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {

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

    @Test
    public void should_map_to_user_entity() {
        //Given
        User user = givenUser();
        //When
        UserEntity userEntity = userMapper.map(user, USERNAME);
        //Then
        assertThat(userEntity.getId()).isEqualTo(ID);
        assertThat(userEntity.getUsername()).isEqualTo(USERNAME);
        assertThat(userEntity.getName()).isEqualTo(NAME);
        assertThat(userEntity.getEmail()).isEqualTo(EMAIL);
        assertThat(userEntity.getCompany()).isEqualTo(COMPANY);
        assertThat(userEntity.getBlog()).isEqualTo(BLOG);
        assertThat(userEntity.getLocation()).isEqualTo(LOCATION);
        assertThat(userEntity.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(userEntity.getRepositories()).isEqualTo(REPOSITORIES);
        assertThat(userEntity.getFollowers()).isEqualTo(FOLLOWERS);
        assertThat(userEntity.getFollowing()).isEqualTo(FOLLOWING);
        assertThat(userEntity.getCreatedAt()).isEqualTo(CREATED_AT);
    }

    @Test
    public void should_map_to_user_dto() {
        //Given
        UserEntity userEntity = givenUserEntity();
        //When
        UserDto userDto = userMapper.map(userEntity);
        //Then
        assertThat(userDto.getId()).isEqualTo(ID);
        assertThat(userDto.getUsername()).isEqualTo(USERNAME);
        assertThat(userDto.getName()).isEqualTo(NAME);
        assertThat(userDto.getEmail()).isEqualTo(EMAIL);
        assertThat(userDto.getCompany()).isEqualTo(COMPANY);
        assertThat(userDto.getBlog()).isEqualTo(BLOG);
        assertThat(userDto.getLocation()).isEqualTo(LOCATION);
        assertThat(userDto.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(userDto.getRepositories()).isEqualTo(REPOSITORIES);
        assertThat(userDto.getFollowers()).isEqualTo(FOLLOWERS);
        assertThat(userDto.getFollowing()).isEqualTo(FOLLOWING);
        assertThat(userDto.getCreatedAt()).isEqualTo(CREATED_AT);
    }

    @Test
    public void should_map_to_user_dtos() {
        //Given
        List<UserEntity> userEntities = givenUserEntities();
        //When
        List<UserDto> userDtos = userMapper.map(userEntities);
        //Then
        assertThat(userDtos.size()).isEqualTo(1);
        assertThat(userDtos.get(0).getId()).isEqualTo(ID);
        assertThat(userDtos.get(0).getUsername()).isEqualTo(USERNAME);
        assertThat(userDtos.get(0).getName()).isEqualTo(NAME);
        assertThat(userDtos.get(0).getEmail()).isEqualTo(EMAIL);
        assertThat(userDtos.get(0).getCompany()).isEqualTo(COMPANY);
        assertThat(userDtos.get(0).getBlog()).isEqualTo(BLOG);
        assertThat(userDtos.get(0).getLocation()).isEqualTo(LOCATION);
        assertThat(userDtos.get(0).getDescription()).isEqualTo(DESCRIPTION);
        assertThat(userDtos.get(0).getRepositories()).isEqualTo(REPOSITORIES);
        assertThat(userDtos.get(0).getFollowers()).isEqualTo(FOLLOWERS);
        assertThat(userDtos.get(0).getFollowing()).isEqualTo(FOLLOWING);
        assertThat(userDtos.get(0).getCreatedAt()).isEqualTo(CREATED_AT);
    }
}
