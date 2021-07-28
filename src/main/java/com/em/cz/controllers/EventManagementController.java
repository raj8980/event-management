package com.em.cz.controllers;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.em.cz.databeans.CreateEventDataBean;
import com.em.cz.databeans.LoginDataBean;
import com.em.cz.databeans.QuestionDtlDataBean;
import com.em.cz.databeans.QuestionGeneratorDataBean;
import com.em.cz.databeans.UserRegistrationDataBean;
import com.em.cz.services.EventManagementService;
import com.em.cz.services.LoginService;

@CrossOrigin("*")
@RestController
public class EventManagementController {

	@Autowired private EventManagementService eventManagementService; 
	@Autowired private LoginService loginService;
	
	@PostMapping("event-type")
	private ResponseEntity<Object> getEventType(){
		return ResponseEntity.ok(eventManagementService.getEventType());
	}
	
	@PostMapping("participant-type")
	private ResponseEntity<Object> getParticipantType(){
		return ResponseEntity.ok(eventManagementService.getParticipantType());
	}
	
	@PostMapping("pre-condition-type")
	private ResponseEntity<Object> getPreConditionType(){
		return ResponseEntity.ok(eventManagementService.getPreConditionType());
	}
	
	@PostMapping("country-details")
	private ResponseEntity<Object> getCountryDetails(){
		return ResponseEntity.ok(eventManagementService.getCountryDetails());	
	}
	
	@PostMapping("state-dtls-by-country")
	private ResponseEntity<Object> getStateDtlsByCountry(@RequestBody String countryId){
		return ResponseEntity.ok(eventManagementService.getStateDtlsByCountry(countryId));
	}
	
	@PostMapping("access-type")
	private ResponseEntity<Object> getAccessType(){
		return ResponseEntity.ok(eventManagementService.getAccessType());
	}
	
	@PostMapping("intereset-type")
	private ResponseEntity<Object> getInteresetType(){
		return ResponseEntity.ok(eventManagementService.getInteresetType());
	}
	
	@PostMapping("answer-type")
	private ResponseEntity<Object> answerType(){
		return ResponseEntity.ok(eventManagementService.getAnswerType());
	}
	
	@PostMapping("event/create-event")
	private ResponseEntity<Object> createEvent(@AuthenticationPrincipal LoginDataBean loginDataBean,@RequestBody CreateEventDataBean createEventDataBean) throws Exception{
		//UserRegistrationDataBean userDB=loginService.getUserOrgDtlsByEmail(loginDataBean.getEmail());
		return ResponseEntity.ok(eventManagementService.createEvent(createEventDataBean,46));
	}
	
	@PostMapping("event/questions-generator")
	private ResponseEntity<Object> generateQuestions(@RequestBody QuestionGeneratorDataBean questionGeneratorDataBean) throws Exception{
		return ResponseEntity.ok(eventManagementService.generateQuestions(questionGeneratorDataBean));
	}
	
	@PostMapping("event/store-question")
	private ResponseEntity<Object> storeQuestionDtls(@RequestBody QuestionDtlDataBean queDtlDataBean) throws Exception{
		return ResponseEntity.ok(eventManagementService.storeQuestionDtls(queDtlDataBean));
	}
	
	@PostMapping("event/get-questioneer-dtls")
	private ResponseEntity<Object> getQuestioneerDtls(@RequestBody String moduleId){
		return ResponseEntity.ok(eventManagementService.getQuestioneerDtls(moduleId));
	}
	
	@PostMapping("event/map-module")
	private ResponseEntity<Object> getMappingModule(@RequestBody List<Map<String,Object>> requestParams){
		return ResponseEntity.ok(eventManagementService.getMappingModule(requestParams));
	}
}
