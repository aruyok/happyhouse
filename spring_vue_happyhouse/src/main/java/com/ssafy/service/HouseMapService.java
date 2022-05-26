package com.ssafy.service;

import java.util.List;

import com.ssafy.dto.Board;
import com.ssafy.dto.CountDto;
import com.ssafy.dto.HouseInfoDto;
import com.ssafy.dto.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getSido2(String sido);
	List<HouseInfoDto> getGugun2(String gugun);
	List<HouseInfoDto> getDong2(String dong);
	HouseInfoDto AptDetail(int aptCode);
	boolean aptPutHit(HouseInfoDto houseInfoDto);
	CountDto Count();
	List<HouseInfoDto> getAll();
	
}
