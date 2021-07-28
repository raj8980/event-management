package com.em.cz.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.em.cz.services.QuestionManagementService;

@CrossOrigin("*")
@RestController
public class QuestionManagementController {

	@Autowired QuestionManagementService questionManagementService; 
	
	@PostMapping("/questioneer-list")
	private ResponseEntity<Object> getQuestioneerList(@RequestBody String pageNumber){
		return ResponseEntity.ok(questionManagementService.getQuestioneerList(pageNumber));
	}
	
	@PostMapping("/mapped-questioneer-list")
	private ResponseEntity<Object> getMappedQuestioneerList(@RequestBody List<Map<String,Object>> requestParams){
		System.out.println(requestParams);
		int page=Integer.parseInt(requestParams.get(0).get("page").toString());
		int eventId=Integer.parseInt(requestParams.get(0).get("eventId").toString());
		return ResponseEntity.ok(questionManagementService.getMappedQuestioneerList(page,eventId));
	}
	
	@PostMapping("/already-mapped-questioneer-list")
	private ResponseEntity<Object> getAlreadyMappedQuesList(@RequestBody List<Map<String,Object>> requestParams){
		System.out.println(requestParams);
		int page=Integer.parseInt(requestParams.get(0).get("page").toString());
		int eventId=Integer.parseInt(requestParams.get(0).get("eventId").toString());
		return ResponseEntity.ok(questionManagementService.getAlreadyMappedQueList(page,eventId));
	}
	
	@PostMapping("event/remove-mapped-module")
	private ResponseEntity<Object> removeMappedModule(@RequestBody String eventModuleMapId){
		return ResponseEntity.ok(questionManagementService.removeMappedModule(eventModuleMapId));
	}
	
	
}
