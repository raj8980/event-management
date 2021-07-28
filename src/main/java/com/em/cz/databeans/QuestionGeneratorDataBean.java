package com.em.cz.databeans;

import java.util.Arrays;

public class QuestionGeneratorDataBean {
	private String moduleTitle;
	private String moduleDesciption;
	private String questionCount;
	private String totalMarks;
	private QuesCombo[] quesCombo;
	
	public String getModuleTitle() {
		return moduleTitle;
	}
	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}
	public String getModuleDesciption() {
		return moduleDesciption;
	}
	public void setModuleDesciption(String moduleDesciption) {
		this.moduleDesciption = moduleDesciption;
	}
	public String getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(String questionCount) {
		this.questionCount = questionCount;
	}
	public QuesCombo[] getQuesCombo() {
		return quesCombo;
	}
	public void setQuesCombo(QuesCombo[] quesCombo) {
		this.quesCombo = quesCombo;
	}
	public String getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
	}
	@Override
	public String toString() {
		return "QuestionGeneratorDataBean [moduleTitle=" + moduleTitle + ", moduleDesciption=" + moduleDesciption
				+ ", questionCount=" + questionCount + ", totalMarks=" + totalMarks + ", quesCombo="
				+ Arrays.toString(quesCombo) + "]";
	}
	
	
}
