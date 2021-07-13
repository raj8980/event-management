package com.em.cz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.cz.daos.LoginDAO;

@Service
public class LoginService {
	
	@Autowired LoginDAO loginDAO;
	

}
