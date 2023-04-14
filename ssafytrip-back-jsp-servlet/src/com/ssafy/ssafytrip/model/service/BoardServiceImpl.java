package com.ssafy.ssafytrip.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.ssafytrip.model.dao.BoardDao;
import com.ssafy.ssafytrip.model.dao.BoardDaoImpl;
import com.ssafy.ssafytrip.model.dto.Board;
import com.ssafy.ssafytrip.model.dto.Page;
import com.ssafy.ssafytrip.model.dto.PageResult;

public class BoardServiceImpl implements BoardService {
	private BoardDao boardDao;
	private static BoardService instance = new BoardServiceImpl();

	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}

	public static BoardService getInstance() {
		return instance;
	}

//	@Override
//	public List<Board> list(Page page) throws Exception {
//		return boardDao.selectBoard(page);
//	}
	
	@Override
	public Map<String, Object> list(Page page) throws Exception {
		// 페이징 처리를 위해 표시할 리스트와 카운트가 필요함
		Map<String, Object> result = new HashMap<>();
		List<Board> list = boardDao.selectBoard(page);
		result.put("list", list);
		result.put("pageResult", new PageResult(page.getPageNo(), boardDao.selectBoardCount(), page.getListSize(), 10));

		return result;
	}

	@Override
	public Board detail(String article_no) throws Exception {
		return boardDao.selectBoardByArticleNo(article_no);
	}

	@Override
	public void regist(Board board) throws Exception {
		boardDao.insertBoard(board);

	}

	@Override
	public void delete(String article_no) throws Exception {
		boardDao.deleteBoard(article_no);
	}
}
