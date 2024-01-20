package com.rodrigofnobrega.demoparkapi.service;

import com.rodrigofnobrega.demoparkapi.entity.UserEntity;
import com.rodrigofnobrega.demoparkapi.exception.EntityNotFoundException;
import com.rodrigofnobrega.demoparkapi.exception.PasswordInvalidException;
import com.rodrigofnobrega.demoparkapi.exception.UsernameUniqueViolationException;
import com.rodrigofnobrega.demoparkapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado", user.getUsername()));
        }
    }

    @Transactional(readOnly = true)
    public UserEntity getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário id=%s não encontrado", id))
        );
    }
    @Transactional
    public UserEntity updatePassword(Long id, String currentPassword, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new PasswordInvalidException("Sua senha não confere com confirmação de senha");
        }

        UserEntity user = getById(id);

        if (!user.getPassword().equals(currentPassword)) {
            throw new PasswordInvalidException("Sua senha não confere.");
        }

        user.setPassword(newPassword);
        
        return user;
    }

    @Transactional(readOnly = true)
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
