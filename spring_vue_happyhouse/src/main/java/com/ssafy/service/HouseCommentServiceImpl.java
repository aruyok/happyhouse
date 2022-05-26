package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dto.Comment;
import com.ssafy.dto.HouseComment;
import com.ssafy.mapper.HouseCommentMapper;

@Service
public class HouseCommentServiceImpl implements HouseCommentService{

	@Autowired
	private HouseCommentMapper houseCommentMapper;
	@Override
	public boolean commentRegist(HouseComment houseComment) {
		
		return houseCommentMapper.commentRegist(houseComment)==1;
	}
	@Override
	public List<HouseComment> commentList(int aptCode) {
		
		return houseCommentMapper.commentList(aptCode);
	}
	@Override
	public boolean delete(int num) {
		// TODO Auto-generated method stub
		return houseCommentMapper.delete(num)==1;
	}
	@Override
	public boolean update(HouseComment houseComment) {
		// TODO Auto-generated method stub
		return houseCommentMapper.update(houseComment)==1;
	}

}
