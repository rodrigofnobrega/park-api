package com.rodrigofnobrega.demoparkapi.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rodrigofnobrega.demoparkapi.entity.UserEntity;
import com.rodrigofnobrega.demoparkapi.enums.UserRoleEnum;
import com.rodrigofnobrega.demoparkapi.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	private final UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userService.getByUsername(username);
		
		return new JwtUserDetails(userEntity);
	}
	
	public JwtToken getTokenAuthenticated(String username) {
		UserRoleEnum role = userService.findRoleByUsername(username);
					
		return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));
	}
}
