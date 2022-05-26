package com.ssafy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dto.Board;
import com.ssafy.service.BoardService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	@Autowired
	private BoardService boardService;
	
	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Board.class)
	@GetMapping("/list")
	public ResponseEntity<?>boardList(){
		return new ResponseEntity<List<Board>>(boardService.boardList(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Board.class)
	@GetMapping("/boarddetail/{num}")
	public ResponseEntity<?>boardList(@PathVariable int num){
		return new ResponseEntity<Board>(boardService.boardDetail(num),HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/writeBoard")
	public ResponseEntity<?>writeBoard(@RequestBody Board board){
		if(boardService.writeBoard(board)) {
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		}
	}
	
	
	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/modify/{num}")
	public ResponseEntity<String> updateBoard(@RequestBody Board board) {
		if (boardService.updateBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/delete/{num}")
	public ResponseEntity<String> deleteBoard(@PathVariable int num) {
		if (boardService.deleteBoard(num)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "조회수를 가져온다..", response = Board.class)
	@GetMapping("/hit")
	public ResponseEntity<?>boardGetHit(){
		return new ResponseEntity<List<Board>>(boardService.boardGetHit(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "글번호에 해당하는 조회수를 수정. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/hit/{num}")
	public ResponseEntity<String> boardPutHit(@RequestBody Board board) {
		if (boardService.boardPutHit(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
