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
	private int myAnswer = -1;

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
	public int getQuestionnum() {
		return Integer.valueOf(questionnum);
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
	public String[] getAllAnswer() {
		return new String[]{answerA,answerB,answerC,answerD,answerE};
	}
	
	public int getMyAnswer() {
		return myAnswer;
	}
	public void setMyAnswer(int myAnswer) {
		this.myAnswer = myAnswer;
	}

}
