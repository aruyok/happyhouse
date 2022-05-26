package com.ssafy.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.dto.UserDto;

@Mapper
public interface UserMapper {

	UserDto login(UserDto userDto);

	void register(UserDto userDto);

	UserDto getUserInfo(String id);

	int modify(UserDto userDto);

	void delete(String id);

	String getSalt(UserDto userDto);


	
	
}
