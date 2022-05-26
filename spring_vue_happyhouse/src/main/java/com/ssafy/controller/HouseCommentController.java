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
import com.ssafy.dto.HouseComment;
import com.ssafy.service.HouseCommentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/h_comment")
@CrossOrigin("*")
public class HouseCommentController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private HouseCommentService houseCommentService;
	
	@ApiOperation(value = "댓글을 등록.", response = String.class)
	@PostMapping("/regist")
	public ResponseEntity<?>commentUpload(@RequestBody HouseComment houseComment){
		if (houseCommentService.commentRegist(houseComment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "모든 댓글리스트를 가져온다.", response = HouseComment.class)
	@GetMapping("/list/{aptCode}")
	public ResponseEntity<?>commentList(@PathVariable int aptCode){
		return new ResponseEntity<List<HouseComment>>(houseCommentService.commentList(aptCode),HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글 삭제", response = String.class)
	@DeleteMapping("/delete/{num}")
	public ResponseEntity<String> deleteBoard(@PathVariable int num) {
		if (houseCommentService.delete(num)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "댓글 수정", response = String.class)
	@PutMapping("/modify/{num}")
	public ResponseEntity<String> updateBoard(@RequestBody HouseComment houseComment) {
		if (houseCommentService.update(houseComment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
}
