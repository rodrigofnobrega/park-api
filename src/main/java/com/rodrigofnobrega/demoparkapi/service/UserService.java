package com.rodrigofnobrega.demoparkapi.service;

import com.rodrigofnobrega.demoparkapi.entity.UserEntity;
import com.rodrigofnobrega.demoparkapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> getById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public Optional<UserEntity> updatePassword(Long id, String password) {
        Optional<UserEntity> user = getById(id);

        user.ifPresent(userEntity -> userEntity.setPassword(password));

        return user;
    }

    @Transactional
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
