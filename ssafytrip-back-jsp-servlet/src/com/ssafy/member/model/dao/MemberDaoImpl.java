package com.ssafy.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.member.model.dto.User;
import com.ssafy.ssafytrip.model.dto.Attraction;
import com.ssafy.ssafytrip.util.DBUtil;

public class MemberDaoImpl implements MemberDao {

	private static MemberDao instance;
	private DBUtil db;

	private MemberDaoImpl() {
		db = DBUtil.getInstance();
	}

	public static MemberDao getInstance() {
		if (instance == null)
			instance = new MemberDaoImpl();
		return instance;
	}

	@Override
	public User login(User user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuffer sql = new StringBuffer();

			sql.append(" select * from member where email = ? and password = ? ");

			con = db.getConnection();
			pstmt = con.prepareStatement(sql.toString());

			int idx = 0;
			pstmt.setString(++idx, user.getEmail());
			pstmt.setString(++idx, user.getPassword());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				User rsUser = new User();

				rsUser.setEmail(rs.getString("email"));
				rsUser.setPassword(rs.getString("password"));
				rsUser.setNickname(rs.getString("nickname"));

				return rsUser;
			}
		} finally {
			db.close(con, pstmt, rs);
		}
		return null;
	}

	@Override
	public User update(User user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			StringBuffer sql = new StringBuffer();

			sql.append(" update member set nickname = ?, password = ? where email = ?");

			con = db.getConnection();
			pstmt = con.prepareStatement(sql.toString());

			int idx = 0;
			pstmt.setString(++idx, user.getNickname());
			pstmt.setString(++idx, user.getPassword());
			pstmt.setString(++idx, user.getEmail());

			pstmt.executeUpdate();
			return user;
		} finally {
			db.close(con, pstmt);
		}
	}

	@Override
	public void insertUser(User user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			StringBuffer sql = new StringBuffer();

			sql.append(" insert into member (email, nickname, password) values (?, ?, ?); ");

			con = db.getConnection();
			pstmt = con.prepareStatement(sql.toString());

			int idx = 0;
			pstmt.setString(++idx, user.getEmail());
			pstmt.setString(++idx, user.getNickname());
			pstmt.setString(++idx, user.getPassword());

			pstmt.executeUpdate();
		} finally {
			db.close(con, pstmt);
		}
	}

}
