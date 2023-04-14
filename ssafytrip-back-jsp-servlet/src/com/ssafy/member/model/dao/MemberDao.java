package com.ssafy.member.model.dao;

import java.sql.SQLException;

import com.ssafy.member.model.dto.User;

public interface MemberDao {
	User login(User user) throws SQLException;
	
	User update(User user) throws SQLException;
	
	void insertUser(User user) throws SQLException;
}
