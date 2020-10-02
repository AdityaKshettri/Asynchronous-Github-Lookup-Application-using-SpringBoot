package com.aditya.project.async.service;

import com.aditya.project.async.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GithubLookupServiceTest {

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

    private static final String GITHUB_API_URL = "https://api.github.com/users/%s";

    @InjectMocks
    private GithubLookupService githubLookupService;

    @Mock
    private RestTemplate restTemplate;

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

    @Test
    public void should_find_github_user_nominal_case() {
        //Given
        User user = givenUser();
        String url = String.format(GITHUB_API_URL, USERNAME);
        when(restTemplate.getForObject(url, User.class)).thenReturn(user);
        //When
        CompletableFuture<User> actual = githubLookupService.findUser(USERNAME);
        //Then
        assertThat(actual).isCompletedWithValue(user);
    }

    @Test(expected = Exception.class)
    public void should_find_github_user_throws_exception_when_github_api_error() {
        //Given
        String url = String.format(GITHUB_API_URL, USERNAME);
        doThrow(new RuntimeException()).when(restTemplate).getForObject(url, User.class);
        githubLookupService.findUser(USERNAME);
    }
}
