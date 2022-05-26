package com.ssafy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dto.CountDto;
import com.ssafy.dto.HouseInfoDto;
import com.ssafy.dto.SidoGugunCodeDto;
import com.ssafy.service.HouseMapService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	@Autowired
	private HouseMapService HouseMapService;
	
	@ApiOperation(value="시도정보.",response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido : {}", HouseMapService.getSido());
//		System.out.println(HouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(HouseMapService.getSido(), HttpStatus.OK);
	}
	@ApiOperation(value="구군정보.",response = List.class)
	@GetMapping("/gugun/{sido}")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@PathVariable String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(HouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	@ApiOperation(value="동정보.",response = List.class)
	@GetMapping("/dong/{gugunCode}")
	public ResponseEntity<List<HouseInfoDto>> dong(@PathVariable String gugunCode) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(HouseMapService.getDongInGugun(gugunCode), HttpStatus.OK);
	}
	
	@ApiOperation(value="아파트 정보.",response = List.class)
	@GetMapping("/apt/{dongCode}")
	public ResponseEntity<List<HouseInfoDto>> apt(@PathVariable String dongCode) throws Exception {
//		System.out.println(HouseMapService.getAptInDong(dong));
//		System.out.println(dong);
		return new ResponseEntity<List<HouseInfoDto>>(HouseMapService.getAptInDong(dongCode), HttpStatus.OK);
	}
	
	@ApiOperation(value="시도 정보 검색.",response = List.class)
	@GetMapping("/s_sido/{sido}")
	public ResponseEntity<List<HouseInfoDto>>s_sido(@PathVariable String sido)throws Exception {
//		System.out.println(sido);
//		System.out.println(HouseMapService.getSido2(sido));
		return new ResponseEntity<List<HouseInfoDto>>(HouseMapService.getSido2(sido), HttpStatus.OK);
	}
	@ApiOperation(value="구군 정보 검색.",response = List.class)
	@GetMapping("/s_gugun/{gugun}")
	public ResponseEntity<List<HouseInfoDto>>s_gugun(@PathVariable String gugun)throws Exception {
//		System.out.println(gugun);
//		System.out.println(HouseMapService.getSido2(gugun));
		return new ResponseEntity<List<HouseInfoDto>>(HouseMapService.getGugun2(gugun), HttpStatus.OK);
	}
	@ApiOperation(value="동정보 검색.",response = List.class)
	@GetMapping("/s_dong/{dong}")
	public ResponseEntity<List<HouseInfoDto>>s_dong(@PathVariable String dong)throws Exception {
//		System.out.println(dong);
//		System.out.println(HouseMapService.getDong2(dong));
		return new ResponseEntity<List<HouseInfoDto>>(HouseMapService.getDong2(dong), HttpStatus.OK);
	}
	
	@ApiOperation(value="아파트코드 넘기기..",response = HouseInfoDto.class)
	@GetMapping("{aptCode}")
	public ResponseEntity<?>AptDetail(@PathVariable int aptCode)throws Exception {
		System.out.println("aptCode: " +aptCode);
//		System.out.println(HouseMapService.getDong2(dong));
		return new ResponseEntity<HouseInfoDto>(HouseMapService.AptDetail(aptCode), HttpStatus.OK);
	}
	
	@ApiOperation(value = "글번호에 해당하는 조회수를 수정. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/hit/{aptCode}")
	public ResponseEntity<String> aptPutHit(@RequestBody HouseInfoDto houseInfoDto) {
		if (HouseMapService.aptPutHit(houseInfoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value="아파트와 댓글 정보넘기기",response = HouseInfoDto.class)
	@GetMapping("/count")
	public ResponseEntity<?>Count()throws Exception {
//		System.out.println("aptCode: " +aptCode);
//		System.out.println(HouseMapService.getDong2(dong));
		return new ResponseEntity<CountDto>(HouseMapService.Count(), HttpStatus.OK);
	}
	
	@ApiOperation(value="아파트 정보넘기기",response = HouseInfoDto.class)
	@GetMapping("")
	public ResponseEntity<?>getAll()throws Exception {
//		System.out.println("aptCode: " +aptCode);
//		System.out.println(HouseMapService.getDong2(dong));
		return new ResponseEntity<List<HouseInfoDto>>(HouseMapService.getAll(), HttpStatus.OK);
	}
}
