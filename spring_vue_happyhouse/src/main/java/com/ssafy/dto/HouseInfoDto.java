package com.ssafy.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "아파트 정보", description = "아파트코드, 아파트이름, 동코드, 동이름, 시도이름, 구군이름, 건축년도, 지번, 위도, 경도, 이미지, 현재 가격")
public class HouseInfoDto {
	@ApiModelProperty(value = "아파트코드")
	private int aptCode;
	@ApiModelProperty(value = "아파트이름")
	private String aptName;
	@ApiModelProperty(value = "동코드")
	private String dongCode;
	@ApiModelProperty(value = "동이름")
	private String dongName;
	@ApiModelProperty(value = "시도이름")
	private String sidoName;
	@ApiModelProperty(value = "구군이름")
	private String gugunName;
	@ApiModelProperty(value = "건축년도")
	private int buildYear;
	@ApiModelProperty(value = "지번")
	private String jibun;
	@ApiModelProperty(value = "위도")
	private String lat;
	@ApiModelProperty(value = "경도")
	private String lng;
	@ApiModelProperty(value = "이미지")
	private String img;
	@ApiModelProperty(value = "현재 가격")
	private String recentPrice;
	@ApiModelProperty(value = "조회수")
	private int hit;
	
	private int count;
	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getAptCode() {
		return aptCode;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getRecentPrice() {
		return recentPrice;
	}

	public void setRecentPrice(String recentPrice) {
		this.recentPrice = recentPrice;
	}

	@Override
	public String toString() {
		return "HouseInfoDto [aptCode=" + aptCode + ", aptName=" + aptName + ", dongCode=" + dongCode + ", dongName="
				+ dongName + ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", buildYear=" + buildYear
				+ ", jibun=" + jibun + ", lat=" + lat + ", lng=" + lng + ", img=" + img + ", recentPrice=" + recentPrice
				+ "]";
	}

}
