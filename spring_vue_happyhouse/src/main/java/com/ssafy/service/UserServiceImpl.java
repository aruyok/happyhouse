package com.ssafy.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.dto.UserDto;
import com.ssafy.encryption.SHA256Util;
import com.ssafy.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	
	@Override
	public boolean regist(UserDto userDto) {
//		String salt = SHA256Util.generateSalt();
//		System.out.println("salt"+salt);
//		userDto.setSalt(salt);
//		String password = userDto.getUserpwd();
//		password = SHA256Util.getEncrypt(password, salt);
//		userDto.setUserpwd(password);
//		userDto.setSalt(salt);
		return userMapper.regist(userDto)==1;
		
	}

	@Override
	public UserDto getUserInfo(String id) {
		return userMapper.getUserInfo(id);
	}

	@Override
	public boolean modify(UserDto userDto) {
		return userMapper.modify(userDto)==1;
	}

	

	@Override
	public boolean delete(String userid) {
		return userMapper.delete(userid)==1;
		
	}

	

	

	
	@Override
	public String getSalt(UserDto userDto) {
		return userMapper.getSalt(userDto);
	}

	@Override
	public UserDto login(UserDto userDto) {
		return userMapper.login(userDto);
	}

//	@Override
//	public UserDto findPassword(UserDto userDto) {
//		return userMapper.findPassword(userDto);
//	}

	@Override
	public String findPassword(String userid, String username, String email) {
		
		return userMapper.findPassword(userid,username,email);
	}

	@Override
	public UserDto getLogin(String userid) {
		
		return userMapper.getLogin(userid);
	}

	
	
}
