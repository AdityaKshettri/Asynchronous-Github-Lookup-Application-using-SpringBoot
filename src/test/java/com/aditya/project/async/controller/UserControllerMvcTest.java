package com.aditya.project.async.controller;

import com.aditya.project.async.controller.helper.JsonHelper;
import com.aditya.project.async.dto.UserDto;
import com.aditya.project.async.dto.UsersDto;
import com.aditya.project.async.rest.UserRestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerMvcTest {

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

    private static final String GET_USERS_URL = "/users";

    private static final String GET_DELETE_USER_URL = "/users/{username}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRestService userRestService;

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

    private static UsersDto givenUsersDto() {
        UsersDto usersDto = new UsersDto();
        usersDto.setUsers(givenUserDtos());
        return usersDto;
    }

    private static String toJson(Object object) {
        return JsonHelper.toJson(object).orElse("");
    }

    @Test
    public void should_get_all_users_returns_200_nominal_case() throws Exception {
        //Given
        UsersDto usersDto = givenUsersDto();
        when(userRestService.getAllUsers()).thenReturn(usersDto);
        //When
        String expected = toJson(usersDto);
        //Then
        mockMvc.perform(get(GET_USERS_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_get_user_by_username_returns_200_nominal_case() throws Exception {
        //Given
        UserDto userDto = givenUserDto();
        when(userRestService.getUserDetails(USERNAME)).thenReturn(userDto);
        //When
        String expected = toJson(userDto);
        //Then
        mockMvc.perform(get(GET_DELETE_USER_URL, USERNAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void should_delete_user_returns_200_nominal_case() throws Exception {
        //Given
        //When
        //Then
        mockMvc.perform(delete(GET_DELETE_USER_URL, USERNAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
