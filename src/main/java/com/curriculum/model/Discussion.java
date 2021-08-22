package com.curriculum.model;

public class Discussion {
	private Long questionNo;
	private String question;
	private String answer;
	private String unitNo;
	private String date;

	public Discussion() {
		super();
	}

	public Discussion(Long questionNo, String question, String answer, String unitNo, String date) {
		super();
		this.questionNo = questionNo;
		this.question = question;
		this.answer = answer;
		this.unitNo = unitNo;
		this.date = date;
	}

	public Long getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(Long questionNo) {
		this.questionNo = questionNo;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Discussion [questionNo=" + questionNo + ", question=" + question + ", answer=" + answer + ", unitNo="
				+ unitNo + ", date=" + date + "]";
	}

}
