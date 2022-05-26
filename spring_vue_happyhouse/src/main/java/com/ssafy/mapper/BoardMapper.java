package com.ssafy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.dto.Board;

@Mapper
public interface BoardMapper {

	List<Board> boardList();

	int writeBoard(Board board);

	int updateBoard(Board board);

	int deleteBoard(int articleno);

	Board boardDetail(int num);

	List<Board> boardGetHit();

	int boardPutHit(Board board);

}
