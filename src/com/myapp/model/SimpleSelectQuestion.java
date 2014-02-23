package com.myapp.model;

import com.myapp.base.BaseModel;

public class SimpleSelectQuestion extends BaseModel {
	
	private String id;
	private String eioid;
	private String questionnum;
	private String question;
	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;
	private String answerE;

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
	public String getAnswerA() {
		return answerA;
	}
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	public String getAnswerB() {
		return answerB;
	}
	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	public String getAnswerC() {
		return answerC;
	}
	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}
	public String getAnswerD() {
		return answerD;
	}
	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}
	public String getAnswerE() {
		return answerE;
	}
	public void setAnswerE(String answerE) {
		this.answerE = answerE;
	}

}
