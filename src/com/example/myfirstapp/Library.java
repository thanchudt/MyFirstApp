package com.example.myfirstapp;

public class Library {
	private long id;
	private long subject_id;
	private String question;
	private String answer;
	private long category_id;
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public long getSubjectId(){
		return subject_id;
	}
	
	public void setSubjectId(long subject_id){
		this.subject_id = subject_id;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public void setQuestion(String question){
		this.question = question;
	}
	
	public String getAnswer(){
		return answer;
	}
	
	public void setAnswer(String answer){
		this.answer = answer;
	}
	
	public long getCategoryId(){
		return category_id;
	}
	
	public void setCategoryId(long category_id){
		this.category_id = category_id;
	}	
}

