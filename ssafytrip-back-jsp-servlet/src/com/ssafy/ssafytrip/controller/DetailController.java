package com.ssafy.ssafytrip.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ssafytrip.model.dto.Attraction;
import com.ssafy.ssafytrip.model.service.AttractionService;
import com.ssafy.ssafytrip.model.service.AttractionServiceImpl;

@WebServlet("/detail")
public class DetailController extends HttpServlet {

	private AttractionService attractionService;
	ObjectMapper mapper;

	@Override
	public void init() throws ServletException {
		attractionService = AttractionServiceImpl.getInstance();
		mapper = new ObjectMapper();
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
		String contentId = request.getParameter("contentId");
		if (contentId == null || "".equals(contentId)) {
			forward(request, response, "/error/error.jsp");
		}

		try {
			forward(request, response, "/page/detail.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

}
