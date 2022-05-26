package com.ssafy.dto;

public class BookMark {
	private String date;
	private int aptCode;
	private String userid;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAptCode() {
		return aptCode;
	}
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "BookMark [date=" + date + ", aptCode=" + aptCode + ", userid=" + userid + "]";
	}
	
	
}
