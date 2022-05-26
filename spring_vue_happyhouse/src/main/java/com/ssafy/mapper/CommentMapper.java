package com.ssafy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.dto.Comment;

@Mapper
public interface CommentMapper {

	List<Comment> commentList(int num);

	int commentUpload(Comment comment);

	

}
