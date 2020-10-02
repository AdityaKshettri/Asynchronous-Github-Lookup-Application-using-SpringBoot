package com.aditya.project.async.repository;

import com.aditya.project.async.model.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private UserEntity givenUserEntity() {
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

    @Test
    public void should_save_user_entity() {
        //Given
        UserEntity userEntity = givenUserEntity();
        //When
        UserEntity response = userRepository.save(userEntity);
        //Then
        UserEntity actual = testEntityManager.find(UserEntity.class, response.getId());
        assertThat(actual).isEqualTo(response);
        assertThat(actual).isEqualTo(userEntity);
    }

    @Test
    public void should_delete_user_entity() {
        //Given
        UserEntity userEntity = givenUserEntity();
        testEntityManager.persist(userEntity);
        //When
        userRepository.delete(userEntity);
        //Then
        UserEntity actual = testEntityManager.find(UserEntity.class, userEntity.getId());
        assertThat(actual).isNull();
    }

    @Test
    public void should_find_user_entity_by_username() {
        //Given
        UserEntity userEntity = givenUserEntity();
        testEntityManager.persist(userEntity);
        //When
        Optional<UserEntity> actual = userRepository.findByUsername(userEntity.getUsername());
        //Then
        assertThat(actual).isEqualTo(Optional.of(userEntity));
    }

    @Test
    public void should_find_all_user_entities() {
        //Given
        UserEntity userEntity = givenUserEntity();
        testEntityManager.persist(userEntity);
        //When
        List<UserEntity> actual = userRepository.findAll();
        //Then
        assertThat(actual).containsExactly(userEntity);
    }
}
