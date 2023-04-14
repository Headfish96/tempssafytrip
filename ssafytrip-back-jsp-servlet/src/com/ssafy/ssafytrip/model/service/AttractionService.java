package com.ssafy.ssafytrip.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.ssafytrip.model.dto.Attraction;
import com.ssafy.ssafytrip.model.dto.Gugun;
import com.ssafy.ssafytrip.model.dto.Sido;

public interface AttractionService {
	List<Attraction> getBannerList() throws SQLException;

	List<Attraction> getPopularList(int limit) throws SQLException;

	List<Attraction> getFestivalList(int limit) throws SQLException;

	List<Attraction> getCultureList(int limit) throws SQLException;

	List<Attraction> getLeisureSportsList(int limit) throws SQLException;

	Attraction getDetail(int contentId) throws SQLException;
	
	List<Sido> getSidoList() throws SQLException;
	
	List<Gugun> getGugunList(String sidoCode) throws SQLException;
}
