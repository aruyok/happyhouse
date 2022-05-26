
package com.ssafy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.dto.HouseInfoDto;
import com.ssafy.dto.UserDto;
import com.ssafy.service.JwtService;
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
	
	@Autowired
	private JwtService jwtService;
	
	
	@ApiOperation(value="로그인 정보를 반환한다.",response = String.class)
	@PostMapping(value= "/login")
//	@RequestMapping(value="/login", method=RequestMethod.POST, consumes = "application/json")
//	@RequestMapping(value = "/login",produces="application/json;charset=UTF-8")
	public ResponseEntity<?> login(@RequestBody UserDto userDto) {
//		String salt = userService.getSalt(userDto);
		Map<String,Object>resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.login(userDto);
			System.out.println(userDto.getRole());
			if(loginUser!=null) {
				String token = jwtService.create("userid", loginUser.getUserid(), "access-token"); //key, data, subject
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
				System.out.println("토큰 생성!");
			}else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
		} 
		}catch(Exception e){
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
//		System.out.println(salt);
//		String ipassword = SHA256Util.getEncrypt(userDto.getUserpwd(), salt);
//		userDto.setUserpwd(ipassword);
////		System.out.println(map);
//		System.out.println("아이디: "+userDto.getUserid());
//		System.out.println("비밀번호: "+userDto.getUserpwd());
//		
//		if(userService.login(userDto)!=null) {
//			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
//		}else {
//			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
//		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value="회원 정보 수정",response = UserDto.class)
	@GetMapping("/login/{userid}")
	public ResponseEntity<?> getLogin(@PathVariable String userid) {
		UserDto userDto = new UserDto();
		userDto=userService.getLogin(userid);
		if(userDto!=null) {
			return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		
	}
	
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userid")String userid,
			HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if(jwtService.isUsable(request.getHeader("access-token"))) {
			System.out.println(request.getHeader("access-token"));
			logger.info("사용 가능한 토큰!!!");
			try {
				//로그인 사용자 정보.
				UserDto userDto = userService.getUserInfo(userid);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
				System.out.println("토큰 확인!");
			}catch(Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	


	@ApiOperation(value="사용자 정보 등록(회원가입).",response = String.class)
	@PostMapping("/regist")
	public ResponseEntity<?> regist(@RequestBody UserDto userDto) {
		System.out.println(userDto.getUsername());
		System.out.println(userDto.getUserid());
		System.out.println(userDto.getUserpwd());
		System.out.println(userDto.getEmail());
		System.out.println(userDto);
		
		if(userService.regist(userDto)) {
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		
	}
	

	@ApiOperation(value="회원 정보 수정",response = String.class)
	@PutMapping("/modify/{userid}")
	public ResponseEntity<?> update(@RequestBody UserDto userDto) {
		if(userService.modify(userDto)) {
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		
	}
	
	@ApiOperation(value="회원 탈퇴.",response = String.class)
	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<?> list(@PathVariable String userid) throws Exception {
		
	
		if(userService.delete(userid)) {
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
	
		
		
	}
	@ApiOperation(value = "비밀번호 정보 불러옴",
            notes = "토큰이 유효하면 유저 정보('userInfo')를 'success' 메세지('message')와 함께 맵으로 반환, 실패하면 'fail' 메세지를 반환",
            response = String.class)
    @GetMapping("/find/{userid}/{username}/{email}")
    public ResponseEntity<?> findPassword(@PathVariable String userid, @PathVariable String username, @PathVariable String email) {
		String password = userService.findPassword(userid,username,email);
		return new ResponseEntity<String>(password,HttpStatus.OK);
    }
}
