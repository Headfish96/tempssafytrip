package com.ssafy.ssafytrip.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.ssafytrip.model.dto.Attraction;
import com.ssafy.ssafytrip.model.dto.Gugun;
import com.ssafy.ssafytrip.model.dto.Sido;
import com.ssafy.ssafytrip.util.DBUtil;

public class AttractionDaoImpl implements AttractionDao {

	private static AttractionDao instance;

	private DBUtil db;

	private AttractionDaoImpl() {
		db = DBUtil.getInstance();
	}

	public static AttractionDao getInstance() {
		if (instance == null)
			instance = new AttractionDaoImpl();
		return instance;
	}

	@Override
	public List<Attraction> findAll() throws SQLException {

		return null;
	}

	@Override
	public List<Attraction> selectBannerList(int limit) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuffer sql = new StringBuffer();

			sql.append(" select ai.content_id, ai.title, ai.first_image, ad.overview " + " from attraction_info ai "
					+ " inner join attraction_description ad " + " on ai.content_id = ad.content_id "
					+ " where first_image != '' and first_image is not null and content_type_id = 12 "
					+ " order by rand() " + " limit ? ");

			con = db.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, limit);

			rs = pstmt.executeQuery();

			List<Attraction> list = new ArrayList<>();
			while (rs.next()) {
				// �궗吏�, title, overview, contentId �븘�슂
				Attraction cur = new Attraction();

				cur.setContentId(Integer.parseInt(rs.getString("content_id")));
				cur.setTitle(rs.getString("title"));
				cur.setOverview(rs.getString("overview"));
				cur.setFirstImage(rs.getString("first_image"));

				list.add(cur);
			}
			return list;
		} finally {
			db.close(con, pstmt, rs);
		}
	}

	@Override
	public List<Attraction> selectPopularList(int limit) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuffer sql = new StringBuffer();

			sql.append(
					" select ai.content_id, ai.title, ai.first_image, ad.overview\r\n" + " from attraction_info ai\r\n"
							+ " inner join attraction_description ad\r\n" + " on ai.content_id = ad.content_id\r\n"
							+ " where first_image != '' and first_image is not null and content_type_id = 12\r\n"
							+ " order by readcount desc\r\n" + " limit ?;");

			con = db.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, limit);

			rs = pstmt.executeQuery();

			List<Attraction> list = new ArrayList<>();
			while (rs.next()) {
				// contentId, title, first_image, overview �븘�슂
				Attraction cur = new Attraction();

				cur.setContentId(Integer.parseInt(rs.getString("content_id")));
				cur.setTitle(rs.getString("title"));
				cur.setOverview(rs.getString("overview"));
				cur.setFirstImage(rs.getString("first_image"));

				list.add(cur);
			}
			return list;
		} finally {
			db.close(con, pstmt, rs);
		}
	}

//	  12: "愿�愿묒�",
//	  14: "臾명솕�떆�꽕",
//	  15: "�뻾�궗/怨듭뿰/異뺤젣",
//	  25: "�뿬�뻾肄붿뒪",
//	  28: "�젅�룷痢�",
//	  32: "�닕諛�",
//	  38: "�눥�븨",
//	  39: "�쓬�떇�젏"
	@Override
	public List<Attraction> selectFestivalList(int limit) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuffer sql = new StringBuffer();

			sql.append(
					" select ai.content_id, ai.title, ai.first_image, ad.overview\r\n" + " from attraction_info ai\r\n"
							+ " inner join attraction_description ad\r\n" + " on ai.content_id = ad.content_id\r\n"
							+ " where first_image != '' and first_image is not null and content_type_id = 15\r\n"
							+ " order by readcount desc\r\n" + " limit ?;");

			con = db.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, limit);

			rs = pstmt.executeQuery();

			List<Attraction> list = new ArrayList<>();
			while (rs.next()) {
				// contentId, title, first_image, overview �븘�슂
				Attraction cur = new Attraction();

				cur.setContentId(Integer.parseInt(rs.getString("content_id")));
				cur.setTitle(rs.getString("title"));
				cur.setOverview(rs.getString("overview"));
				cur.setFirstImage(rs.getString("first_image"));

				list.add(cur);
			}
			return list;
		} finally {
			db.close(con, pstmt, rs);
		}
	}

	@Override
	public List<Attraction> selectCultureList(int limit) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuffer sql = new StringBuffer();

			sql.append(
					" select ai.content_id, ai.title, ai.first_image, ad.overview\r\n" + " from attraction_info ai\r\n"
							+ " inner join attraction_description ad\r\n" + " on ai.content_id = ad.content_id\r\n"
							+ " where first_image != '' and first_image is not null and content_type_id = 14\r\n"
							+ " order by readcount desc\r\n" + " limit ?;");

			con = db.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, limit);

			rs = pstmt.executeQuery();

			List<Attraction> list = new ArrayList<>();
			while (rs.next()) {
				// contentId, title, first_image, overview �븘�슂
				Attraction cur = new Attraction();

				cur.setContentId(Integer.parseInt(rs.getString("content_id")));
				cur.setTitle(rs.getString("title"));
				cur.setOverview(rs.getString("overview"));
				cur.setFirstImage(rs.getString("first_image"));

				list.add(cur);
			}
			return list;
		} finally {
			db.close(con, pstmt, rs);
		}
	}

	@Override
	public List<Attraction> selectLeisureSportsList(int limit) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuffer sql = new StringBuffer();

			sql.append(
					" select ai.content_id, ai.title, ai.first_image, ad.overview\r\n" + " from attraction_info ai\r\n"
							+ " inner join attraction_description ad\r\n" + " on ai.content_id = ad.content_id\r\n"
							+ " where first_image != '' and first_image is not null and content_type_id = 28\r\n"
							+ " order by readcount desc\r\n" + " limit ?;");

			con = db.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, limit);

			rs = pstmt.executeQuery();

			List<Attraction> list = new ArrayList<>();
			while (rs.next()) {
				// contentId, title, first_image, overview �븘�슂
				Attraction cur = new Attraction();

				cur.setContentId(Integer.parseInt(rs.getString("content_id")));
				cur.setTitle(rs.getString("title"));
				cur.setOverview(rs.getString("overview"));
				cur.setFirstImage(rs.getString("first_image"));

				list.add(cur);
			}
			return list;
		} finally {
			db.close(con, pstmt, rs);
		}
	}

	@Override
	public Attraction findByContentId(int contentId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuffer sql = new StringBuffer();

			sql.append(" select ai.*, ad.overview\r\n" + " from attraction_info ai\r\n"
					+ " inner join attraction_description ad\r\n" + " on ai.content_id = ad.content_id\r\n"
					+ " where ai.content_id = ? and first_image != \"\" and first_image is not null");

			con = db.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, contentId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// �븘�슂 �뜲�씠�꽣
				// content_id, title, overview, first_image, latitude, longitude, addr1, addr2
				Attraction cur = new Attraction();

				cur.setContentId(contentId);
				cur.setTitle(rs.getString("title"));
				cur.setOverview(rs.getString("overview"));
				cur.setFirstImage(rs.getString("first_image"));
				cur.setLat(rs.getString("latitude"));
				cur.setLng(rs.getString("longitude"));
				cur.setAddr1(rs.getString("addr1"));
				cur.setAddr2(rs.getString("addr2"));

				return cur;
			}
		} finally {
			db.close(con, pstmt, rs);
		}
		return null;
	}

	@Override
	public List<Sido> selectSidoList() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" select sido_code, sido_name from sido order by sido_code; ");

			con = db.getConnection();
			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			List<Sido> list = new ArrayList<>();
			while (rs.next()) {
				Sido cur = new Sido();

				cur.setCode(rs.getString("sido_code"));
				cur.setName(rs.getString("sido_name"));

				list.add(cur);
			}
			return list;
		} finally {
			db.close(con, pstmt, rs);
		}
	}

	@Override
	public List<Gugun> selectGugunList(String sidoCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from gugun where sido_code = ? order by gugun_code; ");

			con = db.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, sidoCode);
			
			rs = pstmt.executeQuery();

			List<Gugun> list = new ArrayList<>();
			while (rs.next()) {
				Gugun cur = new Gugun();

				cur.setCode(rs.getString("gugun_code"));
				cur.setName(rs.getString("gugun_name"));

				list.add(cur);
			}
			return list;
		} finally {
			db.close(con, pstmt, rs);
		}
	}

}
