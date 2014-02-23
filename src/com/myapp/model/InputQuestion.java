package com.myapp.model;

import com.myapp.base.BaseModel;

public class InputQuestion extends BaseModel {
	
	private String id;
	private String eioid;
	private String questionnum;
	private String question;
	private String answer;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEioid() {
		return eioid;
	}
	public void setEioid(String eioid) {
		this.eioid = eioid;
	}
	public String getQuestionnum() {
		return questionnum;
	}
	public void setQuestionnum(String questionnum) {
		this.questionnum = questionnum;
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

}
