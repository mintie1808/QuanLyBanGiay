package doan.Controller.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import doan.DAO.ProductDAO;
import doan.model.CartItem;
import doan.model.ProductModel;

/**
	 * Servlet implementation class CartController
 */
@WebServlet(urlPatterns = { "/shopcart", "/updateQuantity", "/removeCartItem" })
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		request.setAttribute("page", page);
		String pageToInclude = "/views/web/home.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(pageToInclude);
		rd.forward(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("productId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		double price = Double.parseDouble(request.getParameter("price"));
		ProductDAO dao = new ProductDAO();
		List<ProductModel> initProduct = dao.getProductById(productId);
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<>();
			session.setAttribute("cart", cart);
		}
		boolean productExists = false;
		for (CartItem cartItem : cart) {
			if (cartItem.getProduct().getProductID() == productId) {
				cartItem.setQuantity(cartItem.getQuantity() + quantity);
				cartItem.setPrice(cartItem.getPrice());
				productExists = true;
				break;
			}
		}
		if (!productExists) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(initProduct.get(0));
			cartItem.setQuantity(quantity);
			cartItem.setPrice(price);
			cart.add(cartItem);
		}

		forwardToPage(request, response, "shopping_cart");
	}

	private void updateQuantity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("productId"));
		int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

		for (CartItem cartItem : cart) {
			if (cartItem.getProduct().getProductID() == productId) {
				cartItem.setQuantity(newQuantity);
				cartItem.setPrice(cartItem.getProduct().getPrice());
				break;
			}
		}

		// Gửi phản hồi về trình duyệt
		PrintWriter out = response.getWriter();
		out.print("Quantity updated successfully");
		out.flush();
	}
	private void removeCartItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("productId"));

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

		if (cart != null) {
			// Lọc ra CartItem cần xóa và xóa nó khỏi giỏ hàng
			cart.removeIf(cartItem -> cartItem.getProduct().getProductID() == productId);
		}

		// Gửi phản hồi về trình duyệt (nếu cần)
		PrintWriter out = response.getWriter();
		out.print("Cart item removed successfully");
		out.flush();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		switch (path) {
		case "/updateQuantity":
			updateQuantity(request, response);
			break;
		case "/removeCartItem":
			removeCartItem(request, response);
			break;
		default:
			processRequest(request, response);
			break;
		}
	}

}
