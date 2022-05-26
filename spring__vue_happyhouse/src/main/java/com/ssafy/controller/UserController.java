
package com.ssafy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dto.UserDto;
import com.ssafy.encryption.SHA256Util;
import com.ssafy.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	@Autowired
	private UserService userService;
	
	
	
	@ApiOperation(value="로그인 정보를 반환한다.",response = String.class)
	@PostMapping(value= "/login")
//	@RequestMapping(value="/login", method=RequestMethod.POST, consumes = "application/json")
//	@RequestMapping(value = "/login",produces="application/json;charset=UTF-8")
	public ResponseEntity<String> login(@RequestBody UserDto userDto) {
		String salt = userService.getSalt(userDto);
		System.out.println(salt);
		String ipassword = SHA256Util.getEncrypt(userDto.getUserpwd(), salt);
		userDto.setUserpwd(ipassword);
//		System.out.println(map);
		System.out.println("아이디: "+userDto.getUserid());
		System.out.println("비밀번호: "+userDto.getUserpwd());
		
		if(userService.login(userDto)!=null) {
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		}
		
	}
	
//	@ApiOperation(value="로그아웃을 표현.",response = List.class)
//	@GetMapping("/logout")
//	public ResponseEntity<?> logout(HttpSession session) {
//		session.invalidate();
////		return "redirect:/";
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
//	
////	@GetMapping("/regist")
////	public String regist() {
////		System.out.println("레지스트 들어왔다!");
////		return "user/regist";
////	}
//	
//	@ApiOperation(value="사용자 정보 등록(회원가입).",response = List.class)
//	@PostMapping("/regist")
//	public ResponseEntity<?> regist(UserDto userDto, Model model) {
//		System.out.println(userDto.getUsername());
//		System.out.println(userDto.getUserid());
//		System.out.println(userDto.getUserpwd());
//		System.out.println(userDto.getEmail());
//		System.out.println(userDto);
//		userService.regist(userDto);
//		
//		model.addAttribute("r_msg","회원이 된 것을 환영합니다!!");
//		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
//	}
//	
////	@GetMapping("/modify")
////	public String modify(UserDto userDto) throws Exception {
////		
////		
////		return "user/modify";
////		
////	}
//	
//	@ApiOperation(value="회원 정보 수정",response = List.class)
//	@PostMapping("/update")
//	public ResponseEntity<?> update(UserDto userDto, Model model, HttpSession session) {
////		System.out.println(userDto.getUserid());
//		System.out.println(userDto.getUserpwd());
//		userService.modify(userDto);
////		System.out.println(result);
//		session.invalidate();
//		
//		model.addAttribute("u_msg","회원정보 수정 완료!");
//		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
//	}
//	
//	@ApiOperation(value="회원 탈퇴.",response = List.class)
//	@GetMapping("/delete")
//	public ResponseEntity<?> list(HttpSession session,Model model) throws Exception {
//		UserDto userDto=(UserDto) session.getAttribute("userinfo");
//		String id = userDto.getUserid();
//		session.invalidate();
//		System.out.println(id);
//		userService.delete(id);
//		model.addAttribute("d_msg","아이디가 성공적으로 삭제되었습니다.");
//		
//		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
//	}
}
