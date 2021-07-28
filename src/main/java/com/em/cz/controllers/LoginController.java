package com.em.cz.controllers;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.em.cz.databeans.CreateEventDataBean;
import com.em.cz.databeans.LoginDataBean;
import com.em.cz.databeans.UserRegistrationDataBean;
import com.em.cz.services.EventManagementService;
import com.em.cz.services.LoginService;
@CrossOrigin("*")
@RestController
public class LoginController {
	@Autowired
	AuthenticationManager authManager;
	
	
	
	
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("login")
	public ResponseEntity<Object> login(@RequestBody LoginDataBean loginDataBean,HttpSession session){
		
		Map<String,Object> resultMap=new HashMap<>();
		try {
		Authentication auth =authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDataBean.getEmail(), loginDataBean.getPassword()));
		
		UserRegistrationDataBean userRegDB=loginService.getUserOrgDtlsByEmail(loginDataBean.getEmail());
		SecurityContextHolder.getContext().setAuthentication(auth);
		if(userRegDB.getUserId()>0) {
			resultMap.put("status","200");
			resultMap.put("userId",userRegDB.getUserId());
			resultMap.put("token", session.getId());
			resultMap.put("roleId", userRegDB.getUserTypeId());
		}else if(userRegDB.getOrgRegId()>0) {
			
			resultMap.put("status","200");
			resultMap.put("orgRegId",userRegDB.getOrgRegId());
			resultMap.put("token", session.getId());
			resultMap.put("roleId", userRegDB.getUserTypeId());	
		}
		}catch(Exception e) {
			e.printStackTrace();
			resultMap.put("status","1006");
		}		
		
		return  ResponseEntity.ok(resultMap);
	}
	
}
