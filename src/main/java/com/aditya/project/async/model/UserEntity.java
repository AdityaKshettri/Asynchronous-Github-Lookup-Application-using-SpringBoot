package com.aditya.project.async.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "users")
public class UserEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "company")
    private String company;

    @Column(name = "blog")
    private String blog;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "repositories")
    private int repositories;

    @Column(name = "followers")
    private int followers;

    @Column(name = "following")
    private int following;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
