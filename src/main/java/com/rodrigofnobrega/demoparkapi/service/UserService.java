package com.rodrigofnobrega.demoparkapi.service;

import com.rodrigofnobrega.demoparkapi.entity.UserEntity;
import com.rodrigofnobrega.demoparkapi.enums.HttpMessagesEnum;
import com.rodrigofnobrega.demoparkapi.enums.utils.EnumUtils;
import com.rodrigofnobrega.demoparkapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    public Optional<UserEntity> updatePassword(Long id, String currentPassword, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException(EnumUtils.enumToString(HttpMessagesEnum.NOVA_SENHA_NAO_CONFERE_COM_CONFIRMACAO_DE_SENHA));
        }

        Optional<UserEntity> user = getById(id);

        user.ifPresent(userEntity -> {
            if (!user.get().getPassword().equals(currentPassword)) {
                throw new RuntimeException(EnumUtils.enumToString(HttpMessagesEnum.SUA_SENHA_NAO_CONFERE));
            }

            user.get().setPassword(newPassword);
        });

        return user;
    }

    @Transactional
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
