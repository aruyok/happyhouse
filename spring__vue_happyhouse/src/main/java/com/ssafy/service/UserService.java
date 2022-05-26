package com.ssafy.service;

import java.util.Map;

import com.ssafy.dto.UserDto;

public interface UserService {
	// 회원가입
		void regist(UserDto userDto);	
	//	로그인
		UserDto login(UserDto userDto);
	//	회원 정보
		UserDto getUserInfo(String id);
	//	회원 정보 수정
		int modify(UserDto userDto);
	//	회원 탈퇴
		void delete(String id);
		
		String getSalt(UserDto userDto);
//		UserDto login(Map<String, String> map);
//		UserDto login(UserDto userDto);

}
