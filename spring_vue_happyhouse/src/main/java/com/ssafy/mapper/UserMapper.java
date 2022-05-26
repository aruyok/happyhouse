package com.ssafy.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.dto.UserDto;

@Mapper
public interface UserMapper {

	UserDto login(UserDto userDto);

	

	UserDto getUserInfo(String id);

	int modify(UserDto userDto);

	int delete(String userid);

	String getSalt(UserDto userDto);

	int regist(UserDto userDto);

//	UserDto findPassword(UserDto userDto);



	String findPassword(String userid, String username, String email);



	UserDto getLogin(String userid);


	
	
}
