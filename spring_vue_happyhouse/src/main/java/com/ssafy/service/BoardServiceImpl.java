package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dto.Board;
import com.ssafy.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<Board> boardList() {
		
		return boardMapper.boardList();
	}

	@Override
	public boolean writeBoard(Board board) {
		
		return boardMapper.writeBoard(board)==1;
	}

	@Override
	public boolean updateBoard(Board board) {
		
		return boardMapper.updateBoard(board)==1;
	}

	@Override
	public boolean deleteBoard(int articleno) {
		return boardMapper.deleteBoard(articleno)==1;
	}

	@Override
	public Board boardDetail(int num) {
		return boardMapper.boardDetail(num);
	}

	@Override
	public List<Board> boardGetHit() {
		return boardMapper.boardGetHit();
	}

	@Override
	public boolean boardPutHit(Board board) {
		
		return boardMapper.boardPutHit(board)==1;
	}

}
