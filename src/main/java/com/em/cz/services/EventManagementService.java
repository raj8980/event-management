package com.em.cz.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.cz.daos.EventManagementDAO;
import com.em.cz.databeans.CreateEventDataBean;
import com.em.cz.databeans.MultipleChoiceDtlsDataBean;
import com.em.cz.databeans.QuesCombo;
import com.em.cz.databeans.QuestionDtlDataBean;
import com.em.cz.databeans.QuestionGeneratorDataBean;

@Service
public class EventManagementService {

	@Autowired EventManagementDAO eventManagementDAO;
	
	public List<Map<String,Object>> getEventType() {
		return eventManagementDAO.getEventType();
		
	}

	public  List<Map<String,Object>> getParticipantType() {
		return eventManagementDAO.getParticipantType();
	}

	public List<Map<String,Object>> getPreConditionType() {
		return eventManagementDAO.getPreConditionType();
	}

	public List<Map<String,Object>> getCountryDetails() {
		return eventManagementDAO.getCountryDetails();
	}

	public List<Map<String,Object>> getStateDtlsByCountry(String countryId) {
		return eventManagementDAO.getStateDtlsByCountry(countryId);
	}
	
	public List<Map<String,Object>> getAccessType(){
		return eventManagementDAO.getAccessType();	
	}

	public Map<String,Object> createEvent(CreateEventDataBean createEventDataBean, int userId) throws Exception {
		System.out.println("cs:"+createEventDataBean.toString());
		String startDateTime=createEventDataBean.getStartDate().split("T")[0]+" "+createEventDataBean.getStartTime().split("T")[1];
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		    Date parsedDate = sdf.parse(startDateTime);
		   Timestamp startTimestamp = new java.sql.Timestamp(parsedDate.getTime());
		System.out.println("startDateTime:"+startDateTime);
		String endDateTime=createEventDataBean.getEndDate().split("T")[0]+" "+createEventDataBean.getEndTime().split("T")[1];
		Date endParsedDate = sdf.parse(endDateTime);
		   Timestamp endTimestamp = new java.sql.Timestamp(endParsedDate.getTime());
		System.out.println("endDateTime:"+endDateTime);
		
		
		Object[] params=new Object[] {createEventDataBean.getEventRelated(),createEventDataBean.getEventType(),
				
				createEventDataBean.getEventTitle(),createEventDataBean.getEventDesciption(),
				startTimestamp,endTimestamp,userId,createEventDataBean.getAccessType(),
				createEventDataBean.getParticipantType(),createEventDataBean.getRequireParticipantFees(),
				createEventDataBean.getParticipantFees(),createEventDataBean.getRequirePreCondi(),
				createEventDataBean.getPreConditionType(),createEventDataBean.getPreConditionDtls()};
		long eventId=eventManagementDAO.createEvent(params);
		Map<String,Object> resultMap=new HashMap<String, Object>();
		if(eventId>0) {
			resultMap.put("status", "200");
			resultMap.put("eventId", eventId);	
		}else {
			resultMap.put("status", "1008");
			resultMap.put("eventId", eventId);
		}
		return resultMap ;
	}

	public List<Map<String,Object>> getInteresetType() {
		return eventManagementDAO.getInteresetType();
	}

	public Map<String,Object> generateQuestions(QuestionGeneratorDataBean questionGeneratorDataBean) throws Exception {
		Map<String,Object> resultMap=new HashMap<>();
		Object[] params=new Object[] {
			questionGeneratorDataBean.getModuleTitle(),questionGeneratorDataBean.getModuleDesciption(),
			questionGeneratorDataBean.getQuestionCount(),questionGeneratorDataBean.getTotalMarks(),46
		};
		long moduleQuestionId=eventManagementDAO.generateQuestions(params);
		if(moduleQuestionId>0) {
			resultMap.put("status", "200");
			resultMap.put("moduleQuestionId", moduleQuestionId);
		}else {
			resultMap.put("status","1009");
		}
		return resultMap;
	}

	public List<Map<String,Object>> getAnswerType() {
		return eventManagementDAO.getAnswerType();
	}

	public long storeQuestionDtls(QuestionDtlDataBean queDtlDataBean) throws Exception {
		long result=0l;
		for(QuesCombo quesCombo:queDtlDataBean.getQuesCombo()) {
			Object[] params=new Object[] {queDtlDataBean.getModuleId(),quesCombo.getQuestion(),quesCombo.getAnswerType(),quesCombo.getMarks(),(quesCombo.getTotalOptions()!=null && !quesCombo.getTotalOptions().isEmpty()?quesCombo.getTotalOptions():0),46};
			long moduleQuestionDtlID=eventManagementDAO.insertQuestion(params);
			result=moduleQuestionDtlID;
			for(MultipleChoiceDtlsDataBean multiChoiceDtlsDB:quesCombo.getMultipleChoiceQuestion()) {
				System.out.println("quesCombo.getAnswerType():"+quesCombo.getAnswerType());
				if(Integer.parseInt(quesCombo.getAnswerType())==3 || Integer.parseInt(quesCombo.getAnswerType())==4) {
					System.out.println("multiChoiceDtlsDB.getAnswer():"+multiChoiceDtlsDB.getAnswer()+"multiChoiceDtlsDB.getChoiceDtl():"+multiChoiceDtlsDB.getChoiceDtl());
					Object[] multiChoiceParams=new Object[] {moduleQuestionDtlID,multiChoiceDtlsDB.getAnswer(),multiChoiceDtlsDB.getChoiceDtl(),46};
					long multiChoiceDtlId=eventManagementDAO.insertMultiChoiceDlts(multiChoiceParams);
					result=multiChoiceDtlId;
				}
			
			}
		}
		return result;
	}

	public QuestionGeneratorDataBean getQuestioneerDtls(String moduleId) {
		Map<String,Object> questionFormDtls=eventManagementDAO.getQuestionFormDtls(moduleId);
		QuestionGeneratorDataBean queGenDB=new QuestionGeneratorDataBean();
		if(questionFormDtls!=null && !questionFormDtls.isEmpty()) {
			
			List<Map<String,Object>> questionDtls=eventManagementDAO.getQuestionDtls(moduleId);
			if(questionDtls!=null && !questionDtls.isEmpty()) {
				QuesCombo[] quesComboList=new QuesCombo[questionDtls.size()];
				for(int i=0;i<questionDtls.size();i++) {
					QuesCombo quesCombo=new QuesCombo();
					Map<String,Object> questionDtlMap=questionDtls.get(i);
					if(questionDtlMap!=null && !questionDtlMap.isEmpty()) {
						long moduleQuestionDtlId=Long.parseLong(questionDtlMap.get("modulequestiondtlid").toString());
						if(moduleQuestionDtlId>0) {
							List<Map<String,Object>> multiChoiceQuestionDtl=eventManagementDAO.getMultiChoiceQuestionDtl(moduleQuestionDtlId);
							if(multiChoiceQuestionDtl!=null && !multiChoiceQuestionDtl.isEmpty()) {
								MultipleChoiceDtlsDataBean[] multiChoiceDtlDataBean=new MultipleChoiceDtlsDataBean[multiChoiceQuestionDtl.size()];
								for(int j=0;j<multiChoiceQuestionDtl.size();j++) {
									Map<String,Object> multiChoiceQueDtlMap=multiChoiceQuestionDtl.get(j);
									MultipleChoiceDtlsDataBean multiChoiceDB=new MultipleChoiceDtlsDataBean();
									multiChoiceDB.setAnswer(multiChoiceQueDtlMap.get("iscorrectanswer").toString());
									multiChoiceDB.setChoiceDtl(multiChoiceQueDtlMap.get("multichoicedtl").toString());
									multiChoiceDtlDataBean[j]=multiChoiceDB;
								}
								quesCombo.setMultipleChoiceQuestion(multiChoiceDtlDataBean);
							}
							
						}
						quesCombo.setQuestion(questionDtlMap.get("question").toString());
						quesCombo.setAnswerType(questionDtlMap.get("answertypeid").toString());
						quesCombo.setAnsType(questionDtlMap.get("answertype").toString());
						quesCombo.setMarks(questionDtlMap.get("marks").toString());
						quesCombo.setTotalOptions(questionDtlMap.get("totalOptions").toString());
						quesComboList[i]=quesCombo;
					}
					
				}
				queGenDB.setQuesCombo(quesComboList);
			}
			queGenDB.setModuleTitle(questionFormDtls.get("moduletitle").toString());
			queGenDB.setModuleDesciption(questionFormDtls.get("moduledescription").toString());
			queGenDB.setQuestionCount(questionFormDtls.get("questioncount").toString());
			queGenDB.setTotalMarks(questionFormDtls.get("totalmarks").toString());
		}
		return queGenDB;
	}

	public Map<String,Object> getMappingModule(List<Map<String, Object>> requestParams) {
		int result=0;
		for(int i=0;i<requestParams.size();i++) {
			Map<String,Object> requestMap=requestParams.get(i);
			if(requestMap!=null && !requestMap.isEmpty()) {
				int selectedModuleId=Integer.parseInt(requestMap.get("selectedModuleId").toString());
				int eventId=Integer.parseInt(requestMap.get("eventId").toString());
				System.out.println("selectedModuleId:"+selectedModuleId+"eventid:"+eventId);
				result=eventManagementDAO.saveMappingModule(selectedModuleId,eventId,46);
			}
		}
		Map<String,Object> resultMap=new HashMap<>();
		if(result>0) {
			resultMap.put("status", "200");
		
		}else {
			resultMap.put("status", "1011");
		}
		return resultMap;
	}
}
