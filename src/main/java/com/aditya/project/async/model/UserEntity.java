package com.aditya.project.async.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class UserEntity {

    @Id
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

    private LocalDateTime created_at;
}
