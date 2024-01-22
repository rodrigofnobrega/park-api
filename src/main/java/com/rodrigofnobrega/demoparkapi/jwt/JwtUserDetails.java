package com.rodrigofnobrega.demoparkapi.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.rodrigofnobrega.demoparkapi.entity.UserEntity;

public class JwtUserDetails extends User{
	
	private UserEntity userEntity;

	public JwtUserDetails(UserEntity user) {
		super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
		userEntity = user;
	}
	
	public Long getId() {
		return userEntity.getId();
	}
	
	public String getRole() {
		return userEntity.getRole().name();
	}
}
