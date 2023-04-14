package com.ssafy.ssafytrip.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ssafytrip.model.dto.Attraction;
import com.ssafy.ssafytrip.model.dto.Gugun;
import com.ssafy.ssafytrip.model.dto.Sido;
import com.ssafy.ssafytrip.model.service.AttractionService;
import com.ssafy.ssafytrip.model.service.AttractionServiceImpl;

@WebServlet("/api")
public class ApiController extends HttpServlet {

	private AttractionService attractionService;
	ObjectMapper mapper;

	@Override
	public void init() throws ServletException {
		attractionService = AttractionServiceImpl.getInstance();
		mapper = new ObjectMapper();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 서버 임시 API key - 추후 파일로 관리 필요
		String validationKey = "dlrjtdmsdlatlfhqkfrmqgkszldlqslek";

		String key = request.getParameter("key");

		// API key 검증 로직 - start
		if (!validationKey.equals(key)) {
			badRequest(request, response);
			return;
		}
		// API key 검증 로직 - end

		try {
			String keyword = request.getParameter("keyword");

			if ("detail".equals(keyword)) {
				detailApi(request, response);
			} else if ("list".equals(keyword)) {
				listApi(request, response);
			} else if ("sido".equals(keyword)) {
				sidoApi(request, response);
			} else if ("gugun".equals(keyword)) {
				gugunApi(request, response);
			} else {
				badRequest(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	private void gugunApi(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String sidoCode = request.getParameter("sidoCode");

		if (sidoCode == null || "".equals(sidoCode)) {
			badRequest(request, response);
			return;
		}
		
		List<Gugun> gugunList = attractionService.getGugunList(sidoCode);
		String json = mapper.writeValueAsString(gugunList);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}

	private void sidoApi(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		List<Sido> sidoList = attractionService.getSidoList();

		String json = mapper.writeValueAsString(sidoList);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}

	private void listApi(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String type = request.getParameter("type");

		if (type == null || "".equals(type)) {
			badRequest(request, response);
			return;
		}

		List<Attraction> list = new ArrayList<>();
		if ("popular".equals(type)) {
			list = attractionService.getPopularList(10);
		} else if ("festival".equals(type)) {
			list = attractionService.getFestivalList(10);
		} else if ("culture".equals(type)) {
			list = attractionService.getCultureList(10);
		} else if ("leisureSports".equals(type)) {
			list = attractionService.getLeisureSportsList(10);
		}

		String json = mapper.writeValueAsString(list);

		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}

	private void detailApi(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String input = request.getParameter("contentId");
		if (input == null || "".equals(input)) {
			badRequest(request, response);
			return;
		}

		int contentId = Integer.parseInt(input);
		Attraction attraction = attractionService.getDetail(contentId);

		String json = mapper.writeValueAsString(attraction);

		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}

	private void badRequest(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}
}
