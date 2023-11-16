package doan.Controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import doan.DAO.UserDAO;
import doan.model.Usermodel;

/**
 * Servlet implementation class Homecontroller
 */
@WebServlet(urlPatterns = "/admin-home")
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
		HttpSession session = request.getSession(false);

		// Kiểm tra xem session có tồn tại và USER có giá trị không
		if (session == null || session.getAttribute("USER") == null) {
			// Nếu chưa đăng nhập, chuyển hướng đến trang login
			response.sendRedirect(request.getContextPath() + "/trang-chu?page=login");
			return;
		}

		Usermodel userRole = (Usermodel) session.getAttribute("USER");

		// Kiểm tra nếu role không phải là 1 hoặc 2, chuyển hướng đi
		if (userRole == null || (userRole.getRole() != 1 && userRole.getRole() != 2)) {
			response.sendRedirect(request.getContextPath() + "/trang-chu");
			return;
		}

		String pageParam = request.getParameter("page");
		if (pageParam != null) {
			// Lưu trang vào thuộc tính request
			request.setAttribute("page", pageParam);
			// Chuyển hướng đến trang home.jsp
		}
		UserDAO udao = new UserDAO();
		List<Usermodel> lsuser = udao.findAll();
		request.setAttribute("listUser", lsuser);

		long cout = lsuser.stream().filter(item -> item.getRole() != 1 && item.getRole() != 2).count();
		request.setAttribute("itemcount", cout);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/home.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
