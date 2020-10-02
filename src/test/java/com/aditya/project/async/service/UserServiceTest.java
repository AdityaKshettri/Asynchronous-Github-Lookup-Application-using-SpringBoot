package com.aditya.project.async.service;

import com.aditya.project.async.dao.UserDao;
import com.aditya.project.async.model.User;
import com.aditya.project.async.model.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

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
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Mock
    private GithubLookupService githubLookupService;

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
    public void should_find_github_user_by_username_nominal_case() {
        //Given
        User user = givenUser();
        when(githubLookupService.findUser(USERNAME)).thenReturn(CompletableFuture.completedFuture(user));
        //When
        User actual = userService.findGithubUser(USERNAME);
        //Then
        assertThat(actual).isEqualTo(user);
    }

    @Test
    public void should_find_user_entity_by_username_nominal_case() {
        //Given
        UserEntity userEntity = givenUserEntity();
        when(userDao.findByUsername(USERNAME)).thenReturn(Optional.of(userEntity));
        //When
        Optional<UserEntity> actual = userService.findByUsername(USERNAME);
        //Then
        assertThat(actual).isEqualTo(Optional.of(userEntity));
    }

    @Test
    public void should_find_all_user_entities_nominal_case() {
        //Given
        List<UserEntity> userEntities = givenUserEntities();
        when(userDao.findAll()).thenReturn(userEntities);
        //When
        List<UserEntity> actual = userService.findAll();
        //Then
        assertThat(actual).isEqualTo(userEntities);
    }

    @Test
    public void should_save_user_entity_nominal_case() {
        //Given
        UserEntity userEntity = givenUserEntity();
        when(userDao.findByUsername(USERNAME)).thenReturn(Optional.empty());
        //When
        userService.save(userEntity);
        //Then
        verify(userDao).save(userEntity);
    }

    @Test
    public void should_delete_user_entity_nominal_case() {
        //Given
        UserEntity userEntity = givenUserEntity();
        when(userDao.findByUsername(USERNAME)).thenReturn(Optional.of(userEntity));
        //When
        userService.delete(USERNAME);
        //Then
        verify(userDao).delete(userEntity);
    }
}
