package com.ssafy.member.model.service;

import java.sql.SQLException;

import com.ssafy.member.model.dto.User;

public interface MemberService {

	/**
	 * 로그인 시도
	 */
	User login(User user) throws SQLException;

	/**
	 * 회원정보 수정
	 */
	User update(User user) throws SQLException;

	/**
	 * 회원 가입
	 */
	void signUp(User user) throws SQLException;
}
