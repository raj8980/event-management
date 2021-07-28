package com.em.cz.databeans;

import java.util.Arrays;

public class QuesCombo {

	private String question;
	private String answerType;
	private String totalOptions;
	private String marks;
	private MultipleChoiceDtlsDataBean[] multipleChoiceQuestion;
	private String ansType;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswerType() {
		return answerType;
	}
	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}
	public String getTotalOptions() {
		return totalOptions;
	}
	public void setTotalOptions(String totalOptions) {
		this.totalOptions = totalOptions;
	}
	public MultipleChoiceDtlsDataBean[] getMultipleChoiceQuestion() {
		return multipleChoiceQuestion;
	}
	public void setMultipleChoiceQuestion(MultipleChoiceDtlsDataBean[] multipleChoiceQuestion) {
		this.multipleChoiceQuestion = multipleChoiceQuestion;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getAnsType() {
		return ansType;
	}
	public void setAnsType(String ansType) {
		this.ansType = ansType;
	}
	@Override
	public String toString() {
		return "QuesCombo [question=" + question + ", answerType=" + answerType + ", totalOptions=" + totalOptions
				+ ", marks=" + marks + ", multipleChoiceQuestion=" + Arrays.toString(multipleChoiceQuestion)
				+ ", ansType=" + ansType + "]";
	}
	
	
	
}
