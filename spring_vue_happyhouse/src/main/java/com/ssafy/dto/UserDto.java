package com.ssafy.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@ApiModel(value = "사용자 정보", description = "아이디, 이름, 비밀번호, 이메일, 가입일, 암호화코드")
public class UserDto {
	@ApiModelProperty(value = "아이디")
	private String userid;
	
	@ApiModelProperty(value = "이름")
	private String username;
	
	@ApiModelProperty(value = "비밀번호")
	private String userpwd;
	
	@ApiModelProperty(value = "이메일")
	private String email;
	
	@ApiModelProperty(value = "가입일")
	private String joindate;
	
	private String role;
	
	
	
	
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	@Override
	public String toString() {
		return "UserDto [userid=" + userid + ", userpwd=" + userpwd + ", username=" + username + ", email=" + email
				+ ", joinDate=" + joindate + "]";
	}
	
	

	
}
