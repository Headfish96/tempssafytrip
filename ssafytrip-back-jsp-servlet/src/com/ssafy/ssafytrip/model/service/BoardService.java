package com.ssafy.ssafytrip.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafytrip.model.dto.Board;
import com.ssafy.ssafytrip.model.dto.Page;

public interface BoardService {
//	List<Board> list(Page page) throws Exception;
	Map<String, Object> list(Page page) throws Exception;
	Board detail(String article_no) throws Exception;
	void regist(Board board) throws Exception;
	void delete(String article_no) throws Exception;
}
