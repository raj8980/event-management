package com.em.cz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.em.cz.services.EventManagementService;

@CrossOrigin("*")
@RestController
public class EventManagementController {

	@Autowired private EventManagementService eventManagementService; 
	
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
}
