package com.aditya.project.async.dao;

import com.aditya.project.async.model.UserEntity;
import com.aditya.project.async.repository.UserRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {

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
    private UserDao userDao;

    @Mock
    private UserRepository userRepository;

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
    public void should_save_user_entity() {
        //Given
        UserEntity userEntity = givenUserEntity();
        //When
        userDao.save(userEntity);
        //Then
        verify(userRepository).save(userEntity);
    }

    @Test
    public void should_delete_user_entity() {
        //Given
        UserEntity userEntity = givenUserEntity();
        //When
        userDao.delete(userEntity);
        //Then
        verify(userRepository).delete(userEntity);
    }

    @Test
    public void should_find_user_entity_by_username() {
        //Given
        UserEntity userEntity = givenUserEntity();
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(userEntity));
        //When
        Optional<UserEntity> actual = userDao.findByUsername(USERNAME);
        //Then
        assertThat(actual).isEqualTo(Optional.of(userEntity));
    }

    @Test
    public void should_find_all_user_entity() {
        //Given
        List<UserEntity> userEntities = givenUserEntities();
        when(userRepository.findAll()).thenReturn(userEntities);
        //When
        List<UserEntity> actual = userDao.findAll();
        //Then
        assertThat(actual).isEqualTo(userEntities);
    }
}
