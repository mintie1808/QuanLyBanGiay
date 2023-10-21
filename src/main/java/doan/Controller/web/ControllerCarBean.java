//test file

package doan.Controller.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import doan.DTO.CartBean;
import doan.DTO.ProductDTO;
import doan.model.test_Product;

/**
 * Servlet implementation class ControllerCarBean
 */
public class ControllerCarBean extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerCarBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRepuest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String action = request.getParameter("action");
			if (action != null) {
				if (action.equals("Add to Cart")) {
					HttpSession session = request.getSession(true);
					CartBean shop = (CartBean) session.getAttribute("SHOP");
					if (shop == null) {
						shop = new CartBean();
					}
					int iID = Integer.parseInt(request.getParameter("txtid"));
					String name = request.getParameter("txtname");
					double doublePrice = Double.parseDouble(request.getParameter("txtPrice"));
					test_Product s = new test_Product(iID, name, doublePrice);
					ProductDTO sp = new ProductDTO(s);
					shop.addSanPham(sp);
					session.setAttribute("SHOP", shop);
					RequestDispatcher rd = request.getRequestDispatcher("/views/web/test_index.jsp");
					rd.forward(request, response);
				} else if (action.equals("View Cart")) {
					RequestDispatcher rd = request.getRequestDispatcher("/views/web/test_showcart.jsp");
					rd.forward(request, response);
				} else if (action.equals("AddMore")) {
					RequestDispatcher rd = request.getRequestDispatcher("/views/web/test_index.jsp");
					rd.forward(request, response);
				} else if (action.equals("Remove")) {
					String[] list = request.getParameterValues("rmv");
					if (list != null) {
						HttpSession session = request.getSession();
						if (session != null) {
							CartBean shop = (CartBean) session.getAttribute("SHOP");
							if (shop != null) {
								for (int i = 0; i < list.length; i++) {
									 shop.removeSanPham(list[i]);
								}
								session.setAttribute("SHOP", shop);
							}
						}
					}
					String url = "ControllerCarBean?action=View Cart";
					RequestDispatcher rd = request.getRequestDispatcher(url);
					rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRepuest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRepuest(request, response);
	}

}
