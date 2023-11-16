package doan.Controller.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import doan.DAO.UserDAO;
import doan.model.Usermodel;
import doan.utils.PasswordHashing;

/**
 * Servlet implementation class UserController
 */
@WebServlet({ "/user" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void handleLogout(HttpServletRequest request, HttpServletResponse response, String ssion, String page)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Usermodel user = (Usermodel) session.getAttribute(ssion);
			if (user != null) {
				UserDAO udao = new UserDAO();
//				udao.updateStatus(user.getId(), "2");
			}
			session.invalidate(); // Hủy bỏ session
		}
		response.sendRedirect(page); // Đường dẫn đến trang đăng nhập
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private boolean isValid(String value, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	protected void forwardToPage(HttpServletRequest request, HttpServletResponse response, String message, String page,
			String pageToInclude, String messageAttributeName) throws ServletException, IOException {
		request.setAttribute(messageAttributeName, message);
		request.setAttribute("page", page);
		RequestDispatcher rd = request.getRequestDispatcher(pageToInclude);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String logout = request.getParameter("logout");
		if ("true".equals(logout)) {
			handleLogout(request, response, "USER", "./trang-chu");
			return;
		}

		String us = request.getParameter("us_register");
		String em = request.getParameter("email_register");
		String ps = request.getParameter("ps_register");

		String mes_sc = "message_success";
		String mes = "message";
		String page = "/views/web/home.jsp";

		UserDAO udao = new UserDAO();

		// Kiểm tra xem tên người dùng đã tồn tại hay chưa
		if (udao.isUsernameExists(us)) {
			request.setAttribute("p_mes", "Hãy nhập tên khác");
			forwardToPage(request, response, "tên đã có", "login", page, mes);
			return;
		}

		String gmailRegex = "^[a-zA-Z0-9_]+@gmail\\.com$";
		String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])[a-zA-Z\\d@#$%^&+=]{6,}$";
		if (!isValid(em, gmailRegex)) {
			// Kiểm tra mật khẩu
			request.setAttribute("p_mes", "ex:****@gmail.com");
			forwardToPage(request, response, "sai định dạng gmail!", "login", page, mes);
			return;
		}
		if (!isValid(ps, passwordRegex)) {
			request.setAttribute("p_mes", "ex:T**@**1**");
			forwardToPage(request, response, "sai định dạng pass!", "login", page, mes);
			return;
		}
		Usermodel newuser = new Usermodel();
		newuser.setUsername(us);
	    newuser.setPassword(PasswordHashing.hashPassword(ps)); 
		newuser.setEmail(em);
		newuser.setRole(3);
		newuser.setStatus("2");

		udao.create(newuser);

		// Chuyển hướng đến trang đăng nhập sau khi đăng ký
		request.getSession().setAttribute(mes_sc, "đăng ký thành công");
		response.sendRedirect(request.getContextPath() + "/trang-chu?page=login");
	}

}
