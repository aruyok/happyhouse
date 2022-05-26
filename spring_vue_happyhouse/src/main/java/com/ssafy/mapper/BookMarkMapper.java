package com.ssafy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.dto.BookMark;
import com.ssafy.dto.HouseInfoDto;

@Mapper
public interface BookMarkMapper {

	int Upload(BookMark bookMark);

//	int Delete(BookMark bookMark);

	List<HouseInfoDto> getbookmark(String aptCode);

	boolean Delete(String aptCode, String userid);


}
