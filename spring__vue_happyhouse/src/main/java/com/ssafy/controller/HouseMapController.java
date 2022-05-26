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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dto.HouseInfoDto;
import com.ssafy.dto.SidoGugunCodeDto;
import com.ssafy.dto.UserDto;
import com.ssafy.service.HouseMapService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

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
	@GetMapping("/dong/{gugun}")
	public ResponseEntity<List<HouseInfoDto>> dong(@PathVariable String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(HouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@ApiOperation(value="아파트 정보.",response = List.class)
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
//		System.out.println(HouseMapService.getAptInDong(dong));
//		System.out.println(dong);
		return new ResponseEntity<List<HouseInfoDto>>(HouseMapService.getAptInDong(dong), HttpStatus.OK);
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
		System.out.println(dong);
//		System.out.println(HouseMapService.getDong2(dong));
		return new ResponseEntity<List<HouseInfoDto>>(HouseMapService.getDong2(dong), HttpStatus.OK);
	}
	
	
}
