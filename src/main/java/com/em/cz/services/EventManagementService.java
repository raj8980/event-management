package com.em.cz.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.cz.daos.EventManagementDAO;

@Service
public class EventManagementService {

	@Autowired EventManagementDAO eventManagementDAO;
	
	public List<Map<String,Object>> getEventType() {
		return eventManagementDAO.getEventType();
		
	}

	public  List<Map<String,Object>> getParticipantType() {
		return eventManagementDAO.getParticipantType();
	}

	public Object getPreConditionType() {
		return eventManagementDAO.getPreConditionType();
	}

	public List<Map<String,Object>> getCountryDetails() {
		return eventManagementDAO.getCountryDetails();
	}

	public List<Map<String,Object>> getStateDtlsByCountry(String countryId) {
		return eventManagementDAO.getStateDtlsByCountry(countryId);
	}
}
