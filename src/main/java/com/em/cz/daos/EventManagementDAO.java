package com.em.cz.daos;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventManagementDAO {

	@Autowired GenericDAO genericDAO;
	
	public List<Map<String, Object>> getEventType() {
		String query=" SELECT eventtypeid AS eventTypeId,eventtype AS eventType FROM master_eventtype WHERE xstatus=1";
		return genericDAO.executeQueryForList(query,new Object[] {});
	}

	public List<Map<String, Object>> getParticipantType() {
		String query=" SELECT participanttypeid AS participantTypeID,particanttype AS participantType FROM master_participanttype WHERE xstatus=1 ";
		return genericDAO.executeQueryForList(query,new Object[] {});
	}

	public List<Map<String,Object>> getPreConditionType() {
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
	public List<Map<String,Object>> getAccessType(){
		String query="SELECT accesstypeid AS accessTypeID , accesstype AS accessType FROM master_accesstype WHERE xstatus=1";
		return genericDAO.executeQueryForList(query,new Object[]{});
	}

	public long createEvent(Object[] params) throws Exception {
		StringBuilder query=new StringBuilder();
		query.append(" INSERT INTO event_details (interestid,eventtypeid,title,description,startdate,enddate,createdon,createdby,xstatus, ");
		query.append(" accesstypeid,participanttypeid,requireparticipantfees,participantfees,requireprecondi,preconditypeid,precondidtls) ");
		query.append(" VALUES(?,?,?,?,?,?,UTC_TIMESTAMP(),?,1,?,?,?,?,?,?,?) ");
		return genericDAO.executeQueryWithInsertId(query.toString(),"eventdtlid", params);
	}

	public List<Map<String, Object>> getInteresetType() {
		String query=" SELECT interestid AS interesetID, interest AS intereset FROM master_interest WHERE xstatus=1 ";
		return genericDAO.executeQueryForList(query, new Object[] {});
	}

	public long generateQuestions(Object[] params) throws Exception {
		
		String query=" INSERT INTO module_question (moduletitle,moduledescription,questioncount,totalmarks,xstatus,createdby,createdon) VALUES(?,?,?,?,1,?,UTC_TIMESTAMP())";
		return genericDAO.executeQueryWithInsertId(query,"modulequestionid", params);
	}

	public List<Map<String, Object>> getAnswerType() {
		String query=" SELECT answertypeid AS ansTypeID , answertype AS ansType FROM master_answer_type WHERE xstatus=1 ORDER BY answertype ASC ";
		return genericDAO.executeQueryForList(query, new Object[] {});
	}

	public long insertQuestion(Object[] params) throws Exception {
		StringBuilder query=new StringBuilder();
		query.append(" INSERT INTO module_question_dtl (modulequestionid,question,answertypeid,marks,totalOptions,createdby,createdon) ");
		query.append(" VALUES (?,?,?,?,?,?,UTC_TIMESTAMP()) ");
		return genericDAO.executeQueryWithInsertId(query.toString(), "modulequestiondtlid", params);
	}

	public long insertMultiChoiceDlts(Object[] multiChoiceParams) throws Exception {
		String query=" INSERT INTO module_ques_multi_choice_dtl (modulequestiondtlid,iscorrectanswer,multichoicedtl,createdby,createdon) VALUES (?,?,?,?,UTC_TIMESTAMP()) ";
		return genericDAO.executeQueryWithInsertId(query.toString(), "multichoicequesdtlid", multiChoiceParams);
	}

	public Map<String,Object> getQuestionFormDtls(String moduleId) {
		String query=" SELECT moduletitle,moduledescription,questioncount,totalmarks FROM module_question WHERE modulequestionid=? AND xstatus=1 ";
		return genericDAO.executeQueryForMap(query,moduleId);
	}

	public List<Map<String, Object>> getQuestionDtls(String moduleId) {
		StringBuilder query=new StringBuilder();
		query.append(" SELECT mqd.question,mqd.answertypeid,mqd.marks,mqd.totalOptions,mqd.modulequestiondtlid,mat.answertype "); 
		query.append(" FROM module_question_dtl mqd ");
		query.append(" INNER JOIN master_answer_type mat ON mat.answertypeid=mqd.answertypeid ");
		query.append(" WHERE modulequestionid=? ");
		return genericDAO.executeQueryForList(query.toString(),moduleId);
	}

	public List<Map<String, Object>> getMultiChoiceQuestionDtl(long moduleQuestionDtlId) {
		String query="SELECT multichoicedtl,iscorrectanswer FROM module_ques_multi_choice_dtl WHERE modulequestiondtlid=? ";
		return genericDAO.executeQueryForList(query,moduleQuestionDtlId);
	}

	public int saveMappingModule(int selectedModuleId, int eventId, int userId) {
		StringBuilder query=new StringBuilder();
		query.append(" INSERT INTO event_module_map (eventid,moduleid,createdby,createdon,xstatus) ");
		query.append(" VALUES(?,?,?,UTC_TIMESTAMP(),1) ");
		return genericDAO.executeQueryForInsertion(query.toString(), new Object[] {eventId,selectedModuleId,userId});
	}

}
