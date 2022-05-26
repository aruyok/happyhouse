package com.ssafy.dto;

public class CountDto {
	private String aptCode;
	private String aptName;
	private int hit;
	private int comment;
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getCount() {
		return comment;
	}
	public void setCount(int comment) {
		this.comment = comment;
	}
	
	
}
