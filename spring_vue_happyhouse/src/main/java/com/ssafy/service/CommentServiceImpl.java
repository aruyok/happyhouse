package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dto.Comment;
import com.ssafy.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public List<Comment> commentList(int num) {
		
		return commentMapper.commentList(num);
	}

	@Override
	public boolean commentUpload(Comment comment) {
		
		return commentMapper.commentUpload(comment)==1;
	}

	
}
