package com.em.cz.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired LoginService loginService;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		List<Map<String,Object>> resultList=loginService.getUserByEmail(userName);
		
		return new User(resultList.get(0).get("emailID").toString(), resultList.get(0).get("password").toString(), new ArrayList<>());
	}
	

}
