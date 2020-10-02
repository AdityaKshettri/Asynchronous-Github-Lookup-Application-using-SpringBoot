package com.aditya.project.async.controller;

import com.aditya.project.async.GithubLookupApplication;
import com.aditya.project.async.controller.helper.HttpHelper;
import com.aditya.project.async.dto.UserDto;
import com.aditya.project.async.dto.UsersDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GithubLookupApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    private static final String GET_USERS_URL = "http://localhost:%s/users";

    private static final String GET_DELETE_USER_URL = "http://localhost:%s/users/%s";

    private static final String USERNAME = "AdityaKshettri";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int PORT;

    @Test
    public void should_find_user_by_username() {
        //Given
        String url = String.format(GET_DELETE_USER_URL, PORT, USERNAME);
        HttpEntity<String> request = HttpHelper.getHttpEntity();
        //When
        ResponseEntity<UserDto> response = testRestTemplate.exchange(url, HttpMethod.GET, request, UserDto.class);
        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void should_find_all_users_in_database() {
        //Given
        String url = String.format(GET_USERS_URL, PORT);
        HttpEntity<String> request = HttpHelper.getHttpEntity();
        //When
        ResponseEntity<UsersDto> response = testRestTemplate.exchange(url, HttpMethod.GET, request, UsersDto.class);
        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void should_delete_user() {
        //Given
        should_find_user_by_username();
        String url = String.format(GET_DELETE_USER_URL, PORT, USERNAME);
        HttpEntity<String> request = HttpHelper.getHttpEntity();
        //When
        ResponseEntity<Void> response = testRestTemplate.exchange(url, HttpMethod.DELETE, request, Void.class);
        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNull();
    }
}
