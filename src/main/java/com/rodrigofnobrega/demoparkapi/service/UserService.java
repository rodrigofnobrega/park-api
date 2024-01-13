package com.rodrigofnobrega.demoparkapi.service;

import com.rodrigofnobrega.demoparkapi.entity.UserEntity;
import com.rodrigofnobrega.demoparkapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
