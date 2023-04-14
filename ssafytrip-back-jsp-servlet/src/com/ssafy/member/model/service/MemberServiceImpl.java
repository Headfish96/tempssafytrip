package com.ssafy.member.model.service;

import java.sql.SQLException;

import com.ssafy.member.model.dao.MemberDao;
import com.ssafy.member.model.dao.MemberDaoImpl;
import com.ssafy.member.model.dto.User;

public class MemberServiceImpl implements MemberService {

	private static MemberService instance;
	private MemberDao memberDao;

	private MemberServiceImpl() {
		memberDao = MemberDaoImpl.getInstance();
	}

	public static MemberService getInstance() {
		if (instance == null)
			instance = new MemberServiceImpl();
		return instance;
	}

	@Override
	public User login(User user) throws SQLException {
		return memberDao.login(user);
	}

	@Override
	public User update(User user) throws SQLException {
		return memberDao.update(user);
	}

	@Override
	public void signUp(User user) throws SQLException {
		memberDao.insertUser(user);
	}

}
