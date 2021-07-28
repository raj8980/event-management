package com.em.cz.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.cz.daos.GenericDAO;
import com.em.cz.daos.QuestionManagementDAO;

@Service
public class QuestionManagementService {
	@Autowired QuestionManagementDAO questionManagementDAO;
	
	public List<Map<String,Object>> getQuestioneerList(String pageNumber) {
		int pageNum=(pageNumber!=null && !pageNumber.isEmpty()?Integer.parseInt(pageNumber):0);
		List<Map<String,Object>> questioneerList= questionManagementDAO.getQuestioneerList(pageNum);
		Map<String,Object> cntMap=questionManagementDAO.getQuestioneerCnt();
		int questioneerListCnt=0;
		if(cntMap!=null && !cntMap.isEmpty()) {
			questioneerListCnt=Integer.parseInt(cntMap.get("cnt").toString());	
		}
		if(questioneerList!=null && !questioneerList.isEmpty()) {
			for(int i=0;i<questioneerList.size();i++) {
				Map<String,Object> resultMap=questioneerList.get(i);
				if(resultMap!=null && !resultMap.isEmpty()) {
					resultMap.put("totalCount",questioneerListCnt);
				}
			}
		}
		return questioneerList;
	}

	public List<Map<String,Object>> getMappedQuestioneerList(int pageNumber,int eventId) {
		
		List<Map<String,Object>> questioneerList= questionManagementDAO.getMappedQuestioneerList(pageNumber,eventId);
		questioneerList.removeIf(x->(x.get("eventModuleId")!=null && !x.get("eventModuleId").toString().isEmpty()));
		
		int questioneerListCnt=questioneerList.size();
		int fromIndex=pageNumber>1?((pageNumber-1)*10):0;
		int toIndex=pageNumber>1?fromIndex+10:10;
		System.out.println("toIndex:"+toIndex);
		toIndex=toIndex>questioneerListCnt?questioneerListCnt:toIndex;
		System.out.println("toIndex:"+toIndex);
		List<Map<String,Object>> filteredQuestioneerList=questioneerList.subList(fromIndex, toIndex);
		if(filteredQuestioneerList!=null && !filteredQuestioneerList.isEmpty()) {
			for(int i=0;i<filteredQuestioneerList.size();i++) {
				Map<String,Object> resultMap=filteredQuestioneerList.get(i);
				if(resultMap!=null && !resultMap.isEmpty()) {
					resultMap.put("totalCount",questioneerListCnt);
				}
			}
		}
		return filteredQuestioneerList;
	}

	public List<Map<String,Object>> getAlreadyMappedQueList(int page, int eventId) {
		List<Map<String,Object>> questioneerList=questionManagementDAO.getAlreadyMappedQuesList(page,eventId);
		
		Map<String,Object> cntMap=questionManagementDAO.getAlreadyQuesCnt(eventId);
		int questioneerListCnt=0;
		if(cntMap!=null && !cntMap.isEmpty()) {
			questioneerListCnt=Integer.parseInt(cntMap.get("cnt").toString());	
		}
		
		if(questioneerList!=null && !questioneerList.isEmpty()) {
			for(int k=0;k<questioneerList.size();k++) {
				Map<String,Object> resultMap=questioneerList.get(k);
				if(resultMap!=null && !resultMap.isEmpty()) {
					resultMap.put("totalCount", questioneerListCnt);
				}
			}
		}
		return questioneerList;
	}

	public Map<String,Object> removeMappedModule(String eventModuleMapId) {
		 int result=questionManagementDAO.removeMappedModule(eventModuleMapId);
		 Map<String,Object> resultMap=new HashMap<String, Object>();
		 if(result>0) {
			 resultMap.put("status","200");
		 }else {
			 resultMap.put("status","1010");
		 }
		 return resultMap;
	}
}
