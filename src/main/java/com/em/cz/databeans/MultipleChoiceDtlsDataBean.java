package com.em.cz.databeans;

public class MultipleChoiceDtlsDataBean {

	private String choiceDtl;
	private String answer;
	public String getChoiceDtl() {
		return choiceDtl;
	}
	public void setChoiceDtl(String choiceDtl) {
		this.choiceDtl = choiceDtl;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "MultipleChoiceDtlsDataBean [choiceDtl=" + choiceDtl + ", answer=" + answer + "]";
	}
	
}
