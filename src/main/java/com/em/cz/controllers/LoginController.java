package com.em.cz.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.em.cz.databeans.UserRegistrationDataBean;
@CrossOrigin("*")
@RestController
public class LoginController {

	@PostMapping("login")
	public ResponseEntity<Object> login(@AuthenticationPrincipal UserRegistrationDataBean userBean,HttpSession session){
		Map<String,Object> resultMap=new HashMap<>();
		if(userBean.getUserId()>0) {
			
			resultMap.put("status","200");
			resultMap.put("userId",userBean.getUserId());
			resultMap.put("token", session.getId());
			resultMap.put("roleId", userBean.getUserTypeId());
		}else if(userBean.getOrgRegId()>0) {
			
			resultMap.put("status","200");
			resultMap.put("orgRegId",userBean.getOrgRegId());
			resultMap.put("token", session.getId());
			resultMap.put("roleId", userBean.getUserTypeId());
		}else {
			resultMap.put("status","1006");
		}
		
		return  ResponseEntity.ok(resultMap);
	}
}
