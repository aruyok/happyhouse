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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dto.Board;
import com.ssafy.dto.Comment;
import com.ssafy.service.CommentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired 
	private CommentService commentService;
	
	@ApiOperation(value = "모든 댓글리스트를 가져온다.", response = Board.class)
	@GetMapping("/list/{num}")
	public ResponseEntity<?>commentList(@PathVariable int num){
		return new ResponseEntity<List<Comment>>(commentService.commentList(num),HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글을 업로드한다.", response = String.class)
	@PostMapping("/upload")
	public ResponseEntity<?>commentUpload(@RequestBody Comment comment){
		if (commentService.commentUpload(comment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
//	@ApiOperation(value = "글번호에 해당하는 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//	@DeleteMapping("/delete/{num}")
//	public ResponseEntity<String> deleteBoard(@PathVariable int num) {
//		if (commentService.deleteComment(num)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}
	
//	@ApiOperation(value = "글번호에 해당하는 조회수를 수정. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//	@PutMapping("/hit/{num}")
//	public ResponseEntity<String> boardPutHit(@RequestBody Board board) {
//		if (boardService.boardPutHit(board)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}
	
}
