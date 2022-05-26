package com.ssafy.service;

import java.util.List;

import com.ssafy.dto.Board;

public interface BoardService {

	List<Board> boardList();

	boolean writeBoard(Board board);

	boolean updateBoard(Board board);

	boolean deleteBoard(int articleno);

	Board boardDetail(int num);

	List<Board> boardGetHit();

	boolean boardPutHit(Board board);

}
