package doan.Controller.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import doan.DAO.UserDAO;
import doan.model.Usermodel;
import doan.utils.PasswordHashing;

/**
 * Servlet implementation class Homecontroller
 */
@WebServlet(urlPatterns = { "/trang-chu" })
public class Homecontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Homecontroller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageParam = request.getParameter("page");
		if (pageParam != null) {
			request.setAttribute("page", pageParam);
		}
		String pageToInclude = "/views/web/home.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(pageToInclude);
		rd.forward(request, response);
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
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		UserDAO udao = new UserDAO();
		Usermodel umodel = udao.findByUserName(un);

		String mes_sc = "message_success";
		String mes = "message";
		if (umodel != null) {
			if (umodel.getStatus().equals("1") || umodel.getStatus().equals("2")) {
				String hashedEnteredPassword = PasswordHashing.hashPassword(pw);
				if (hashedEnteredPassword.equals(umodel.getPassword())) {
					HttpSession session = request.getSession();
					session.setAttribute("USER", umodel);
					udao.updateStatus(umodel.getId(), "1");

					int role = umodel.getRole();
					if (role == 1 || role == 2) {
//						forwardToPage(request, response, "Đăng nhập thành công", "", "/views/admin/home.jsp", mes_sc);
						session.setAttribute(mes_sc, "Successfully Logged In");
						response.sendRedirect(request.getContextPath() + "/admin-home");
					} else {
//						forwardToPage(request, response, "Đăng nhập thành công", "", "/views/web/home.jsp", mes_sc);
						session.setAttribute(mes_sc, "Successfully Logged In");
						response.sendRedirect(request.getContextPath() + "/trang-chu");
					}
				} else {
					request.setAttribute("pwus", un);
					request.setAttribute("p_mes", "Please enter correct information!");
					forwardToPage(request, response, "Incorrect password!", "login", "/views/web/home.jsp", mes);
				}
			} else {
				forwardToPage(request, response, "Account has been locked.", "login", "/views/web/home.jsp", mes);
			}
		} else {
			request.setAttribute("p_mes", "Please enter correct information!");
			forwardToPage(request, response, "Incorrect username!", "login", "/views/web/home.jsp", mes);
		}
	}

}
