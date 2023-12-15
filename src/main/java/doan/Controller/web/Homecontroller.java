package doan.Controller.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import doan.DAO.ProductDAO;
import doan.model.ProductModel;


/**
 * Servlet implementation class Homecontroller
 */
@WebServlet(urlPatterns = { "/trang-chu"})
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
		ProductDAO dao = new ProductDAO();
		List<ProductModel> listhot = dao.getLatestHotProducts(8);
		request.setAttribute("listhot", listhot);
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

	}

}
