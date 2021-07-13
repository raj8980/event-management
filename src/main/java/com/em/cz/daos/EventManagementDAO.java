package com.em.cz.daos;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventManagementDAO {

	@Autowired GenericDAO genericDAO;
	
	public List<Map<String, Object>> getEventType() {
		String query=" SELECT eventtypeid AS eventTypeID,eventtype AS eventType FROM master_eventtype WHERE xstatus=1";
		return genericDAO.executeQueryForList(query,new Object[] {});
	}

	public List<Map<String, Object>> getParticipantType() {
		String query=" SELECT participanttypeid AS participantTypeID,particanttype AS participantType FROM master_participanttype WHERE xstatus=1 ";
		return genericDAO.executeQueryForList(query,new Object[] {});
	}

	public Object getPreConditionType() {
		String query=" SELECT preconditiontypeid AS preCondiId, preconditiontype AS preCondiType FROM master_precondition_type WHERE xstatus=1 ";
		return genericDAO.executeQueryForList(query, new Object[] {});
	}

	public List<Map<String, Object>> getCountryDetails() {
		String query=" SELECT countryid AS countryID,country AS country FROM master_country WHERE xstatus=1 ";
		return genericDAO.executeQueryForList(query, new Object[] {});
	}

	public List<Map<String, Object>> getStateDtlsByCountry(String countryId) {
		String query=" SELECT stateid,state FROM master_state WHERE countryid=? AND xstatus=? ";
		return genericDAO.executeQueryForList(query, new Object[] {countryId});
	}

}
