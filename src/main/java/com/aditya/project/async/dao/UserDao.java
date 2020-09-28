package com.aditya.project.async.dao;

import com.aditya.project.async.model.UserEntity;
import com.aditya.project.async.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;

    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public void delete(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}
