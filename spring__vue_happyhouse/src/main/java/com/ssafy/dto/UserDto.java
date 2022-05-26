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
	private String joinDate;
	
	@ApiModelProperty(value = "암호화코드")
	private String salt;
	
	
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
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@Override
	public String toString() {
		return "UserDto [userid=" + userid + ", userpwd=" + userpwd + ", username=" + username + ", email=" + email
				+ ", joinDate=" + joinDate + ", salt=" + salt + "]";
	}
	
	

	
}
