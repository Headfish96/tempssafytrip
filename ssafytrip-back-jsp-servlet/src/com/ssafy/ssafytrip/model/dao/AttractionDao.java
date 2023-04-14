package com.ssafy.ssafytrip.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

import com.ssafy.ssafytrip.model.dto.Attraction;
import com.ssafy.ssafytrip.model.dto.Gugun;
import com.ssafy.ssafytrip.model.dto.Sido;

public interface AttractionDao {
	List<Attraction> findAll() throws SQLException;

	/**
	 * home �솕硫댁쓽 banner�뿉 �몴�떆�맆 �뜲�씠�꽣
	 */
	List<Attraction> selectBannerList(int limit) throws SQLException;

	/**
	 * home �솕硫댁쓽 �씤湲� �뿬�뻾吏��뿉 �몴�떆�맆 �뜲�씠�꽣
	 */
	List<Attraction> selectPopularList(int limit) throws SQLException;

	/**
	 * home �솕硫댁쓽 異뺤젣 �빆紐⑹뿉 �몴�떆�맆 �뜲�씠�꽣
	 */
	List<Attraction> selectFestivalList(int limit) throws SQLException;

	/**
	 * home �솕硫댁쓽 臾명솕�떆�꽕�뿉 �몴�떆�맆 �뜲�씠�꽣
	 */
	List<Attraction> selectCultureList(int limit) throws SQLException;

	/**
	 * home �솕硫댁쓽 �젅�룷痢좎뿉 �몴�떆�맆 �뜲�씠�꽣
	 */
	List<Attraction> selectLeisureSportsList(int limit) throws SQLException;

	/**
	 * detail �솕硫댁쓽 �뜲�씠�꽣瑜� 媛��졇�삤�뒗 硫붿꽌�뱶
	 */
	Attraction findByContentId(int contentId) throws SQLException;
	
	List<Sido> selectSidoList() throws SQLException;
	
	List<Gugun> selectGugunList(String sidoCode) throws SQLException;
}
