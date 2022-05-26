package com.ssafy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dto.BookMark;
import com.ssafy.dto.HouseInfoDto;
import com.ssafy.service.BookMarkService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/bookmark")
@CrossOrigin("*")
public class BookMarkController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private BookMarkService bookMarkService;
	
	//포스트, 딜리트, 겟
	@ApiOperation(value = "북마크의 정보를 가져온다..", response = HouseInfoDto.class)
	@GetMapping("/getbookmark/{userid}")
	public ResponseEntity<?>getbookmark(@PathVariable String userid){
		return new ResponseEntity<List<HouseInfoDto>>(bookMarkService.getbookmark(userid),HttpStatus.OK);
	}
	
	@ApiOperation(value = "북마크를 등록한다.", response = String.class)
	@PostMapping("/upload")
	public ResponseEntity<?>Upload(@RequestBody BookMark bookMark){
		if (bookMarkService.Upload(bookMark)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "북마크를 취소한다.", response = String.class)
	@DeleteMapping("/delete/{aptCode}/{userid}")
	public ResponseEntity<?>Delete(@PathVariable String aptCode, @PathVariable String userid){
//		System.out.println("북마크: "+bookMark.toString());
		BookMark bookmark = new BookMark();
		if (bookMarkService.Delete(aptCode,userid)) {
			
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
}
