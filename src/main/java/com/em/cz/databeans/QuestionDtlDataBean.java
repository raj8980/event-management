package com.em.cz.databeans;

import java.util.Arrays;

public class QuestionDtlDataBean {

	private String moduleId;
	private QuesCombo[] quesCombo;
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public QuesCombo[] getQuesCombo() {
		return quesCombo;
	}
	public void setQuesCombo(QuesCombo[] quesCombo) {
		this.quesCombo = quesCombo;
	}
	@Override
	public String toString() {
		return "QuestionDtlDataBean [moduleId=" + moduleId + ", quesCombo=" + Arrays.toString(quesCombo) + "]";
	}
	
	
	
}
