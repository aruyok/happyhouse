package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dto.HouseInfoDto;
import com.ssafy.dto.SidoGugunCodeDto;
import com.ssafy.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return houseMapMapper.getAptInDong(dong);
	}

	@Override
	public List<HouseInfoDto> getSido2(String sido) {
		return houseMapMapper.getSido2(sido);
	}

	@Override
	public List<HouseInfoDto> getGugun2(String gugun) {
		return houseMapMapper.getGugun2(gugun);
	}

	@Override
	public List<HouseInfoDto> getDong2(String dong) {
		return houseMapMapper.getDong2(dong);
	}

}
