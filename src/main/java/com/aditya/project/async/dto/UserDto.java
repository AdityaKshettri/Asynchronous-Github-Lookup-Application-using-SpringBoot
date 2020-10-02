package com.aditya.project.async.dto;

import com.aditya.project.async.helper.LocalDateTimeDeserializer;
import com.aditya.project.async.helper.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserDto {

    private String id;

    private String username;

    private String name;

    private String email;

    private String company;

    private String blog;

    private String location;

    private String description;

    private int repositories;

    private int followers;

    private int following;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;
}
