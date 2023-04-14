package com.ssafy.ssafytrip.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.ssafytrip.model.dto.Attraction;
import com.ssafy.ssafytrip.model.service.AttractionService;
import com.ssafy.ssafytrip.model.service.AttractionServiceImpl;

@WebServlet("/home")
public class HomeController extends HttpServlet {

	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		attractionService = AttractionServiceImpl.getInstance();
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

		if (action == null || "".equals(action))
			action = "main";

		String path = "";
		switch (action) {
		case "main":
			path = doHome(request, response);
			forward(request, response, path);
			break;
		case "error":
			forward(request, response, "/error/error.jsp");
			break;
		}
	}

	private String doHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Attraction> banner = attractionService.getBannerList();
			List<Attraction> popular = attractionService.getPopularList(3);
			List<Attraction> festival = attractionService.getFestivalList(3);
			List<Attraction> culture = attractionService.getCultureList(3);
			List<Attraction> leisureSports = attractionService.getLeisureSportsList(3);

			request.setAttribute("banner", banner);
			request.setAttribute("popular", popular);
			request.setAttribute("festival", festival);
			request.setAttribute("curture", culture);
			request.setAttribute("leisureSports", leisureSports);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/index.jsp";
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

}
