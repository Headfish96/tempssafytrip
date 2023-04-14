package com.ssafy.member.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.member.model.dto.User;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.member.model.service.MemberServiceImpl;

@WebServlet("/member")
public class MemberController extends HttpServlet {

	private MemberService memberService;

	@Override
	public void init() throws ServletException {
		memberService = MemberServiceImpl.getInstance();
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
		if (action == null || "".equals(action)) {
			action = "error";
		}

		try {
			String path = "";
			if ("login".equals(action)) {
				path = doLogin(request, response);
				if (path == null)
					return;
				redirect(request, response, path);
			} else if ("loginForm".equals(action)) {
				path = "/member/login.jsp";
				forward(request, response, path);
			} else if ("logout".equals(action)) {
				path = doLogout(request, response);
				redirect(request, response, path);
			} else if ("signup".equals(action)) {
				path = doSignUp(request, response);
				if (path == null)
					return;
				redirect(request, response, path);
			} else if ("signupForm".equals(action)) {
				path = "/member/signup.jsp";
				forward(request, response, path);
			} else if ("modifyForm".equals(action)) {
				path = "/member/userinfo.jsp";
				forward(request, response, path);
			} else if ("modify".equals(action)) {
				path = doModify(request, response);
				if (path == null)
					return;
				redirect(request, response, path);
			}

			else {
				forward(request, response, "/error/error.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String doModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("msg", "로그인이 필요합니다");
			forward(request, response, "/member/login.jsp");
			return null;
		}
		
		User modifyUser = new User();
		modifyUser.setEmail(user.getEmail());
		modifyUser.setNickname(request.getParameter("nickname"));
		modifyUser.setPassword(request.getParameter("password"));
		
		memberService.update(modifyUser);

		return "/home";
	}

	private String doSignUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		user.setEmail(request.getParameter("email"));
		user.setNickname(request.getParameter("nickname"));
		user.setPassword(request.getParameter("password"));

		try {
			memberService.signUp(user);
		} catch (SQLException e) {
			request.setAttribute("msg", "회원가입 실패");
			forward(request, response, "/member/signup.jsp");
			return null;
		}

		return "/member?action=loginForm";
	}

	private String doLogout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "/home";
	}

	private String doLogin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);

		User valUser = memberService.login(user);

		if (valUser == null) {
			request.setAttribute("msg", "로그인 실패");
			forward(request, response, "/member/login.jsp");
			return null;
		}

		request.getSession().setAttribute("user", valUser);

		String remember = request.getParameter("remember");
		if (remember != null) {
			Cookie cookie = new Cookie("email", valUser.getEmail());
			cookie.setMaxAge(60 * 60 * 24 * 365 * 20);
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("email", valUser.getEmail());
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		return "/home";
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

}
