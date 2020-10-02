package com.aditya.project.async.service;

import com.aditya.project.async.dao.UserDao;
import com.aditya.project.async.exception.ErrorCatalog;
import com.aditya.project.async.exception.ServiceException;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
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

    @Test(expected = ServiceException.class)
    public void should_find_github_user_by_username_throws_exception_when_user_not_found() {
        //Given
        when(githubLookupService.findUser(USERNAME)).thenReturn(CompletableFuture.completedFuture(null));
        try {
            //When
            userService.findGithubUser(USERNAME);
        } catch (ServiceException e) {
            //Then
            assertThat(e.getErrorCatalog()).isEqualTo(ErrorCatalog.GITHUB_USER_NOT_FOUND_ERROR);
            assertThat(e.getMessage()).isEqualTo(ErrorCatalog.GITHUB_USER_NOT_FOUND_ERROR.getMessage());
            throw e;
        }
    }

    @Test(expected = ServiceException.class)
    public void should_find_github_user_by_username_throws_exception_when_github_api_error() {
        //Given
        doThrow(new RuntimeException()).when(githubLookupService).findUser(USERNAME);
        try {
            //When
            userService.findGithubUser(USERNAME);
        } catch (ServiceException e) {
            //Then
            assertThat(e.getErrorCatalog()).isEqualTo(ErrorCatalog.GITHUB_API_ERROR);
            assertThat(e.getMessage()).isEqualTo(ErrorCatalog.GITHUB_API_ERROR.getMessage());
            throw e;
        }
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
    public void should_find_user_entity_by_username_when_user_not_found() {
        //Given
        when(userDao.findByUsername(USERNAME)).thenReturn(Optional.empty());
        //When
        Optional<UserEntity> actual = userService.findByUsername(USERNAME);
        //Then
        assertThat(actual).isEqualTo(Optional.empty());
    }

    @Test(expected = ServiceException.class)
    public void should_find_user_entity_by_username_throws_exception_when_dao_error() {
        //Given
        doThrow(new RuntimeException()).when(userDao).findByUsername(USERNAME);
        try {
            //When
            userService.findByUsername(USERNAME);
        } catch (ServiceException e) {
            //Then
            assertThat(e.getErrorCatalog()).isEqualTo(ErrorCatalog.APPLICATION_ERROR);
            assertThat(e.getMessage()).isEqualTo(ErrorCatalog.APPLICATION_ERROR.getMessage());
            throw e;
        }
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

    @Test(expected = ServiceException.class)
    public void should_find_all_user_entities_throws_exception_when_dao_error() {
        //Given
        doThrow(new RuntimeException()).when(userDao).findAll();
        try {
            //When
            userService.findAll();
        } catch (ServiceException e) {
            //Then
            assertThat(e.getErrorCatalog()).isEqualTo(ErrorCatalog.APPLICATION_ERROR);
            assertThat(e.getMessage()).isEqualTo(ErrorCatalog.APPLICATION_ERROR.getMessage());
            throw e;
        }
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

    @Test(expected = ServiceException.class)
    public void should_save_user_entity_throws_exception_when_user_already_exists() {
        //Given
        UserEntity userEntity = givenUserEntity();
        when(userDao.findByUsername(USERNAME)).thenReturn(Optional.of(userEntity));
        try {
            //When
            userService.save(userEntity);
        } catch (ServiceException e) {
            //Then
            verify(userDao, never()).save(userEntity);
            assertThat(e.getErrorCatalog()).isEqualTo(ErrorCatalog.USER_ALREADY_EXISTS_ERROR);
            assertThat(e.getMessage()).isEqualTo(ErrorCatalog.USER_ALREADY_EXISTS_ERROR.getMessage());
            throw e;
        }
    }

    @Test(expected = ServiceException.class)
    public void should_save_user_entity_throws_exception_when_dao_error() {
        //Given
        UserEntity userEntity = givenUserEntity();
        doThrow(new RuntimeException()).when(userDao).findByUsername(USERNAME);
        try {
            //When
            userService.save(userEntity);
        } catch (ServiceException e) {
            //Then
            verify(userDao, never()).save(userEntity);
            assertThat(e.getErrorCatalog()).isEqualTo(ErrorCatalog.APPLICATION_ERROR);
            assertThat(e.getMessage()).isEqualTo(ErrorCatalog.APPLICATION_ERROR.getMessage());
            throw e;
        }
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

    @Test(expected = ServiceException.class)
    public void should_delete_user_entity_throws_exception_when_user_not_found() {
        //Given
        UserEntity userEntity = givenUserEntity();
        when(userDao.findByUsername(USERNAME)).thenReturn(Optional.empty());
        try {
            //When
            userService.delete(USERNAME);
        } catch (ServiceException e) {
            //Then
            verify(userDao, never()).delete(userEntity);
            assertThat(e.getErrorCatalog()).isEqualTo(ErrorCatalog.USER_NOT_FOUND_ERROR);
            assertThat(e.getMessage()).isEqualTo(ErrorCatalog.USER_NOT_FOUND_ERROR.getMessage());
            throw e;
        }
    }

    @Test(expected = ServiceException.class)
    public void should_delete_user_entity_throws_exception_when_dao_error() {
        //Given
        UserEntity userEntity = givenUserEntity();
        doThrow(new RuntimeException()).when(userDao).findByUsername(USERNAME);
        try {
            //When
            userService.delete(USERNAME);
        } catch (ServiceException e) {
            //Then
            verify(userDao, never()).delete(userEntity);
            assertThat(e.getErrorCatalog()).isEqualTo(ErrorCatalog.APPLICATION_ERROR);
            assertThat(e.getMessage()).isEqualTo(ErrorCatalog.APPLICATION_ERROR.getMessage());
            throw e;
        }
    }
}
