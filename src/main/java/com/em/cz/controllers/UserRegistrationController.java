package com.em.cz.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.em.cz.databeans.UserRegistrationDataBean;
import com.em.cz.services.UserRegistrationService;

@CrossOrigin("*")
@RestController
public class UserRegistrationController {

	@Autowired UserRegistrationService userRegistrationService;
	
	@PostMapping("register-user")
	private ResponseEntity<Object> registerUser(@RequestBody UserRegistrationDataBean userRegistrationDataBean) throws Exception{
		System.out.println("here");
		return ResponseEntity.ok(userRegistrationService.registerUser(userRegistrationDataBean));
	}
	

				
	@PostMapping("upload-image/{userId}")
	private ResponseEntity<Object> uploadImage(@PathVariable("userId") int userId, @RequestBody MultipartFile file) throws Exception{
		
		long fileUploadID=userRegistrationService.saveFileDtls(userId,file,1);
		Map<String,Object> resultMap=new HashMap<>();
		resultMap.put("status","1003");
		resultMap.put("fileUploadID",fileUploadID);
		
		return ResponseEntity.ok(resultMap);
	}
	
	@PostMapping("remove-user-profile-pic")
	private ResponseEntity<Object> removeUserProfile(@RequestBody Map<String,Object> fileUploadID){
		userRegistrationService.removeUserProfile(fileUploadID);
		return ResponseEntity.ok("1004");
	}
	
	@PostMapping("org-register")
	private ResponseEntity<Object> orgRegister(@RequestBody UserRegistrationDataBean userRegistrationDataBean) throws Exception{
		return ResponseEntity.ok(userRegistrationService.orgRegister(userRegistrationDataBean));
	}
	
	@PostMapping("org-upload-image/{userId}")
	private ResponseEntity<Object> orgUploadImage(@PathVariable("userId") int userId, @RequestBody MultipartFile file) throws Exception{
		
		long fileUploadID=userRegistrationService.saveFileDtls(userId,file,2);
		Map<String,Object> resultMap=new HashMap<>();
		resultMap.put("status","1003");
		resultMap.put("fileUploadID",fileUploadID);
		
		return ResponseEntity.ok(resultMap);
	}
}
