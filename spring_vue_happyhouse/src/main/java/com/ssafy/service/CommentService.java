package com.ssafy.service;

import java.util.List;

import com.ssafy.dto.Comment;

public interface CommentService {

	List<Comment> commentList(int num);

	boolean commentUpload(Comment comment);

	

}
