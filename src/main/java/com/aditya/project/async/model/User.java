package com.aditya.project.async.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String id;

    private String name;

    private String email;

    private String company;

    private String blog;

    private String location;

    private String bio;

    private int public_repos;

    private int followers;

    private int following;

    private LocalDateTime created_at;
}
