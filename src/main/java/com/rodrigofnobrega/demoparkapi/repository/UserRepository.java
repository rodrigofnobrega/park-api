package com.rodrigofnobrega.demoparkapi.repository;

import com.rodrigofnobrega.demoparkapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
