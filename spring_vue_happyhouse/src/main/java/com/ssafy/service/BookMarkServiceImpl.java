package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dto.BookMark;
import com.ssafy.dto.HouseInfoDto;
import com.ssafy.mapper.BookMarkMapper;

@Service
public class BookMarkServiceImpl implements BookMarkService{

	@Autowired 
	private BookMarkMapper bookMarkMapper;
	
	@Override
	public boolean Upload(BookMark bookMark) {
		return bookMarkMapper.Upload(bookMark)==1;
	}

//	@Override
//	public boolean Delete(BookMark bookMark) {
//		
//		return bookMarkMapper.Delete(bookMark)==1;
//	}

	@Override
	public List<HouseInfoDto> getbookmark(String userid) {
		
		return bookMarkMapper.getbookmark(userid);
	}

	@Override
	public boolean Delete(String aptCode, String userid) {
		
		return bookMarkMapper.Delete(aptCode,userid);
	}

	
}
