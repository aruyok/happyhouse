package com.ssafy.service;

import java.util.List;

import com.ssafy.dto.Comment;
import com.ssafy.dto.HouseComment;

public interface HouseCommentService {

	boolean commentRegist(HouseComment houseComment);

	List<HouseComment> commentList(int aptCode);

	boolean delete(int num);

	boolean update(HouseComment houseComment);

}
