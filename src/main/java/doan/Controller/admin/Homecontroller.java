package doan.Controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

import doan.DAO.OrderDAO;
import doan.DAO.ProductDAO;
import doan.DAO.UserDAO;
import doan.model.Usermodel;
import doan.utils.AccessUtils;

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
	 private String formatAsUSD(int amount) {
	        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(java.util.Locale.US);
	        return currencyFormat.format(amount);
	    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AccessUtils.checkUserRole(request, response)){
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

		long cout = lsuser.stream().filter(item -> item.getRole() != 1 && item.getRole() != 2).count();
		request.setAttribute("itemcount", cout);
		
		OrderDAO oDao = new OrderDAO();
		int TotalAmount = oDao.getTotalOrderAmount();
		
		ProductDAO productDAO = new ProductDAO();
		int soldproduct = productDAO.getsoldPrduct();
		request.setAttribute("soldproduct", soldproduct);
		

        // Định dạng số thành tiền USD
        String formattedTotalAmount = formatAsUSD(TotalAmount);
		request.setAttribute("TotalAmount", formattedTotalAmount);

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
