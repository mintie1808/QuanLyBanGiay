package doan.Controller.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import doan.DAO.UserDAO;
import doan.model.Usermodel;

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
	protected void login(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
		String method = req.getMethod();
		if (method.equalsIgnoreCase("POST")) {
			// TODO: ĐĂNG NHẬP
			String username = req.getParameter("username");
			String pw = req.getParameter("password");
			try {
				UserDAO dao = new UserDAO();
				Usermodel user = dao.findByUserName(username);

				if (!user.getPassword().equals(pw)) {
					req.setAttribute("message", "Sai mật khẩu!");

				} else {
					req.setAttribute("message", "Đăng nhập thành công!");
					req.getSession().setAttribute("user", user);
				}
			} catch (Exception e) {
				req.setAttribute("message", "Sai tên đăng nhập!");
			}
		}
		String pageToInclude = "/views/web/home.jsp";
		req.getRequestDispatcher(pageToInclude).forward(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageParam = request.getParameter("page");
		if (pageParam != null) {
			request.setAttribute("page", pageParam);
			if(pageParam.equals("login")) {
				String method = request.getMethod();
				if (method.equalsIgnoreCase("POST")) {
					// TODO: ĐĂNG NHẬP
					String username = request.getParameter("username");
					String pw = request.getParameter("password");
						UserDAO dao = new UserDAO();
						Usermodel user = dao.findByUserName(username);

						if (!user.getPassword().equals(pw)) {
							request.setAttribute("message", "Sai mật khẩu!");

						}
				}
			}
		}
		String pageToInclude = "/views/web/home.jsp";
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
		response.sendRedirect(request.getContextPath() + "/trang-chu");

	}

}
