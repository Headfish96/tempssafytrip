package com.ssafy.ssafytrip.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.ssafytrip.model.dto.Board;
import com.ssafy.ssafytrip.model.dto.Page;
import com.ssafy.ssafytrip.model.dto.PageResult;
import com.ssafy.ssafytrip.model.service.BoardService;
import com.ssafy.ssafytrip.model.service.BoardServiceImpl;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private BoardService boardService;

	@Override
	public void init() throws ServletException {
		boardService = BoardServiceImpl.getInstance();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null)
			action = "list";

		switch (action) {
		case "registForm":
			RequestDispatcher rd1 = request.getRequestDispatcher("/board/registBoard.jsp");
			rd1.forward(request, response);
			break;
		case "regist":
			String path1 = request.getContextPath() + regist(request, response);
			response.sendRedirect(path1);
			break;
		case "list":
			request.getRequestDispatcher(list(request, response)).forward(request, response);
			break;
		case "detail":
			request.getRequestDispatcher(detail(request, response)).forward(request, response);
			break;
		case "delete":
			String path2 = request.getContextPath() + delete(request, response);
			response.sendRedirect(path2);
			break;
		case "editForm":
			RequestDispatcher rd2 = request.getRequestDispatcher(editForm(request, response));
			rd2.forward(request, response);
			break;
		case "edit":
//			String path3 = request.getContextPath() + edit(request, response);
//			response.sendRedirect(path3);
			break;
		default:
			break;
		}
	}

	private String editForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("board", boardService.detail(request.getParameter("article_no")));
		} catch (Exception e) {
			e.printStackTrace();
			return "/error/error.jsp";
		}
		return "/board/editBoard.jsp";
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			boardService.delete(request.getParameter("article_no"));
		} catch (Exception e) {
			e.printStackTrace();
			return "/error/error.jsp";
		}
		return "/board?action=list";
	}

	private String regist(HttpServletRequest request, HttpServletResponse response) {
		Board board = new Board();
		board.setContent(request.getParameter("content"));
		board.setSubject(request.getParameter("subject"));
		board.setUser_id(request.getParameter("user_id"));
		try {
			boardService.regist(board);
		} catch (Exception e) {
			e.printStackTrace();
			return "/error/error.jsp";
		}
		return "/board?action=list";
	}

	private String detail(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("board", boardService.detail(request.getParameter("article_no")));
		} catch (Exception e) {
			e.printStackTrace();
			return "/error/error.jsp";
		}
		return "board/detailBoard.jsp";
	}

	private String list(HttpServletRequest request, HttpServletResponse response) {
		try {
			Page page = new Page();
			try {
				page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));

				String listSize = request.getParameter("listSize");
				if (listSize != null) {
					page.setListSize(Integer.parseInt(listSize));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 페이징 결과와 함께 jsp 로 전달
			Map<String, Object> boards = boardService.list(page);
			request.setAttribute("boards", (List<Board>) boards.get("list"));
			request.setAttribute("pageResult", (PageResult) boards.get("pageResult"));

		} catch (Exception e) {
			e.printStackTrace();
			return "/error/error.jsp";
		}
		return "/board/listBoard.jsp";
	}

}
