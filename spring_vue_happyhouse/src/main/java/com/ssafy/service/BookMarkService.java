package com.ssafy.service;

import java.util.List;

import com.ssafy.dto.BookMark;
import com.ssafy.dto.HouseInfoDto;

public interface BookMarkService {

	boolean Upload(BookMark bookMark);

//	boolean Delete(BookMark bookMark);

	List<HouseInfoDto> getbookmark(String userid);

	boolean Delete(String aptCode, String userid);

//	boolean Delete(BookMark bookMark);
		

}
