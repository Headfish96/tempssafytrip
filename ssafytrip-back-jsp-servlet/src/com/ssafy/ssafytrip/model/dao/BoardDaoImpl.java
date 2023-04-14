package com.ssafy.ssafytrip.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.ssafy.ssafytrip.model.dto.Board;
import com.ssafy.ssafytrip.model.dto.Page;
import com.ssafy.ssafytrip.util.DBUtil;

public class BoardDaoImpl implements BoardDao {
	private DBUtil db;
	private static BoardDao instance = new BoardDaoImpl();

	private BoardDaoImpl() {
		db = DBUtil.getInstance();
	}

	public static BoardDao getInstance() {
		return instance;
	}

	@Override
	public List<Board> selectBoard(Page page) throws SQLException {
		try (Connection con = db.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement(" select article_no, user_id, subject, content, hit, register_time \r\n"
								+ " from board \r\n" + " order by article_no desc limit ?, ? ;");) {
			
			pstmt.setInt(1, page.getBegin());
			pstmt.setInt(2, page.getListSize());
			
			List<Board> list = new ArrayList<>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Board b = new Board();
				b.setArticle_no(rs.getInt("article_no"));
				b.setSubject(rs.getString("subject"));
				b.setUser_id(rs.getString("user_id"));
				b.setRegister_time(rs.getString("register_time"));
				b.setHit(rs.getInt("hit"));
				list.add(b);
			}
			return list;
		}
	}

	@Override
	public int selectBoardCount() throws SQLException, NamingException {
		List<Board> list = new ArrayList<>();
		try (Connection conn = db.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(" select count(*) from board ");) {
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		}
	}

	@Override
	public Board selectBoardByArticleNo(String article_no) throws SQLException {
		try (Connection con = db.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement(" select article_no, user_id, subject, content, hit, register_time\r\n"
								+ " from board\r\n" + " where article_no = ? ;")) {
			pstmt.setString(1, article_no);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Board b = new Board();
				b.setArticle_no(rs.getInt("article_no"));
				b.setSubject(rs.getString("subject"));
				b.setUser_id(rs.getString("user_id"));
				b.setRegister_time(rs.getString("register_time"));
				b.setHit(rs.getInt("hit"));
				b.setContent(rs.getString("content"));
				return b;
			}
			return null;
		}
	}

	@Override
	public void insertBoard(Board board) throws SQLException {
		try (Connection con = db.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement("INSERT INTO `board` (user_id, subject, content)VALUES (?,?,?);")) {
			int index = 0;
			pstmt.setString(++index, board.getUser_id());
			pstmt.setString(++index, board.getSubject());
			pstmt.setString(++index, board.getContent());
			pstmt.executeUpdate();
		}
	}

	@Override
	public void deleteBoard(String article_no) throws SQLException {
		try (Connection con = db.getConnection();
				PreparedStatement pstmt = con.prepareStatement(" delete from board where article_no = ?; ");) {
			pstmt.setString(1, article_no);
			pstmt.executeUpdate();
		}
	}

}
