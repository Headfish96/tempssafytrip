package com.ssafy.ssafytrip.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.ssafytrip.model.dao.AttractionDao;
import com.ssafy.ssafytrip.model.dao.AttractionDaoImpl;
import com.ssafy.ssafytrip.model.dto.Attraction;
import com.ssafy.ssafytrip.model.dto.Gugun;
import com.ssafy.ssafytrip.model.dto.Sido;

public class AttractionServiceImpl implements AttractionService {

	private static AttractionService instance;
	private AttractionDao attractionDao;

	private AttractionServiceImpl() {
		attractionDao = AttractionDaoImpl.getInstance();
	}

	public static AttractionService getInstance() {
		if (instance == null)
			instance = new AttractionServiceImpl();
		return instance;
	}

	@Override
	public List<Attraction> getBannerList() throws SQLException {
		int limit = 3;
		List<Attraction> list = attractionDao.selectBannerList(limit);

		for (Attraction attraction : list) {
			String overview = attraction.getOverview();
			if (overview.length() < 100)
				continue;
			overview = overview.substring(0, 100);
			overview += "...";
			attraction.setOverview(overview);
		}

		return list;
	}

	@Override
	public List<Attraction> getPopularList(int limit) throws SQLException {
		List<Attraction> list = attractionDao.selectPopularList(limit);
		
		return list;
	}

	@Override
	public List<Attraction> getFestivalList(int limit) throws SQLException {
		List<Attraction> list = attractionDao.selectFestivalList(limit);
		
		return list;
	}

	@Override
	public List<Attraction> getCultureList(int limit) throws SQLException {
		List<Attraction> list = attractionDao.selectCultureList(limit);
		
		return list;
	}

	@Override
	public List<Attraction> getLeisureSportsList(int limit) throws SQLException {
		List<Attraction> list = attractionDao.selectLeisureSportsList(limit);
		
		return list;
	}

	@Override
	public Attraction getDetail(int contentId) throws SQLException {
		return attractionDao.findByContentId(contentId);
	}

	@Override
	public List<Sido> getSidoList() throws SQLException {
		return attractionDao.selectSidoList();
	}

	@Override
	public List<Gugun> getGugunList(String sidoCode) throws SQLException {
		return attractionDao.selectGugunList(sidoCode);
	}

}
