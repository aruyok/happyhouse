package com.ssafy.service;

import java.util.Map;

import com.ssafy.dto.UserDto;

public interface UserService {
	// 회원가입
		boolean regist(UserDto userDto);	
	//	로그인
		UserDto login(UserDto userDto);
	//	회원 정보
		UserDto getUserInfo(String id);
	//	회원 정보 수정
		boolean modify(UserDto userDto);
	//	회원 탈퇴
		boolean delete(String userid);
		
		String getSalt(UserDto userDto);
//		UserDto login(Map<String, String> map);
//		UserDto login(UserDto userDto);
//		UserDto findPassword(UserDto userDto);
		String findPassword(String userid, String username, String email);
//		UserDto findPassword(UserDto userDto);
		UserDto getLogin(String userid);
	

}
