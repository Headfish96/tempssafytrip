package com.ssafy.ssafytrip.model.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.ssafy.ssafytrip.model.dto.Board;
import com.ssafy.ssafytrip.model.dto.Page;

public interface BoardDao {
	List<Board> selectBoard(Page page) throws SQLException;
	Board selectBoardByArticleNo(String article_no) throws SQLException;
	void insertBoard(Board board) throws SQLException;
	void deleteBoard(String article_no) throws SQLException;
	int selectBoardCount() throws SQLException, NamingException;
}
