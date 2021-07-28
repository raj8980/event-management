package com.em.cz.daos;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionManagementDAO {

	@Autowired GenericDAO genericDAO;
	
	public List<Map<String,Object>> getQuestioneerList(int pageNum) {
		StringBuilder query=new StringBuilder();
		query.append(" SELECT modulequestionid AS moduleQuestionId,moduletitle AS moduleTitle,moduledescription AS moduleDesc, questioncount AS questionCnt ,totalmarks AS totalMarks ");
		query.append(" FROM module_question WHERE xstatus=1 LIMIT ?,?");
		
		return genericDAO.executeQueryForList(query.toString(),new Object[] {pageNum>1?((pageNum-1)*10):0,10});
	}

	public Map<String, Object> getQuestioneerCnt() {
		String query=" SELECT COUNT(moduleTitle) AS cnt FROM  module_question WHERE xstatus=1 ";
		return genericDAO.executeQueryForMap(query, new Object[] {});
	}

	public List<Map<String, Object>> getMappedQuestioneerList(int pageNumber, int eventId) {
		StringBuilder query=new StringBuilder();
		query.append(" SELECT eventmodulemapid AS eventModuleId, modulequestionid AS moduleQuestionId,moduletitle AS moduleTitle,moduledescription AS moduleDesc, questioncount AS questionCnt ,totalmarks AS totalMarks ");
		query.append(" FROM module_question mq ");
		query.append(" LEFT JOIN event_module_map emm ON emm.moduleid=mq.modulequestionid AND emm.eventid=? AND emm.xstatus=1");
		query.append(" WHERE mq.xstatus=1 ");
		
		return genericDAO.executeQueryForList(query.toString(),new Object[] {eventId});
	}

	public List<Map<String, Object>> getAlreadyMappedQuesList(int page, int eventId) {
		StringBuilder query=new StringBuilder();
		query.append(" SELECT eventmodulemapid AS eventModuleId, modulequestionid AS moduleQuestionId,moduletitle AS moduleTitle,moduledescription AS moduleDesc, questioncount AS questionCnt ,totalmarks AS totalMarks ");
		query.append(" FROM module_question mq ");
		query.append(" INNER JOIN event_module_map emm ON emm.moduleid=mq.modulequestionid  ");
		query.append(" WHERE mq.xstatus=1 AND emm.xstatus=1 AND emm.eventid=? LIMIT ?,?");
		return genericDAO.executeQueryForList(query.toString(), new Object[] {eventId,page>1?((page-1)*10):0,10});
	}

	public Map<String, Object> getAlreadyQuesCnt(int eventId) {
		StringBuilder query=new StringBuilder();
		query.append(" SELECT COUNT(eventmodulemapid) AS cnt ");
		query.append(" FROM module_question mq ");
		query.append(" INNER JOIN event_module_map emm ON emm.moduleid=mq.modulequestionid  ");
		query.append(" WHERE mq.xstatus=1 AND emm.xstatus=1 AND emm.eventid=? ");
		return genericDAO.executeQueryForMap(query.toString(), new Object[] {eventId});
	}

	public int removeMappedModule(String eventModuleMapId) {
		String query=" DELETE FROM event_module_map  WHERE eventmodulemapid=? ";
		return genericDAO.executeQueryForInsertion(query, eventModuleMapId);
	}

}
