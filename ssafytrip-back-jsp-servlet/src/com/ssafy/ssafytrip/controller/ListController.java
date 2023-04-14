package com.ssafy.ssafytrip.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ssafytrip.model.dto.Attraction;
import com.ssafy.ssafytrip.model.service.AttractionService;
import com.ssafy.ssafytrip.model.service.AttractionServiceImpl;

@WebServlet("/list")
public class ListController extends HttpServlet {

	private AttractionService attractionService;
	private ObjectMapper mapper;

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
		String type = request.getParameter("type");

		try {
			if (type == null || "".equals(type)) {
				throw new Exception("type miss match");
			}
			forward(request, response, "/page/list.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			forward(request, response, "/error/error.jsp");
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
