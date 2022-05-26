package com.ssafy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.dto.Comment;
import com.ssafy.dto.HouseComment;

@Mapper
public interface HouseCommentMapper {

	int commentRegist(HouseComment houseComment);

	List<HouseComment> commentList(int aptCode);

	int delete(int num);

	int update(HouseComment houseComment);

}
