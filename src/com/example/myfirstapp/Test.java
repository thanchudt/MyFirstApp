package com.example.myfirstapp;

import java.util.Date;

public class Test {
	private long id;
	private long user_id;
	private long library_id;
	private Date last_test_date;
	private long mark;
	private long times;
	private long total_mark;
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public long getUserId(){
		return user_id;
	}
	
	public void setUserId(long user_id){
		this.user_id = user_id;
	}
	
	public long getLibraryId(){
		return library_id;
	}
	
	public void setLibraryId(long library_id){
		this.library_id = library_id;
	}
	
	public Date getLastTestDate(){
		return last_test_date;
	}
	
	public void setLastTestDate(Date last_test_date){
		this.last_test_date = last_test_date;
	}
	
	public long getMark(){
		return mark;
	}
	
	public void setMark(long mark){
		this.mark = mark;
	}
	
	public long getTimes(){
		return times;
	}
	
	public void setTimes(long times){
		this.times = times;
	}
	
	public long getTotalMark(){
		return total_mark;
	}
	
	public void setTotalMark(long total_mark){
		this.total_mark = total_mark;
	}
}

