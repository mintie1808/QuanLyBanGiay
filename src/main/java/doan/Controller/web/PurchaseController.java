package doan.Controller.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import doan.DAO.OrderDAO;
import doan.DAO.OrderDetailDAO;
import doan.DAO.ProductDAO;
import doan.model.CartItem;
import doan.model.OrderDetailModel;
import doan.model.OrderModel;
import doan.model.Usermodel;

/**
 * Servlet implementation class PurcharController
 */
@WebServlet(urlPatterns = { "/order" })
public class PurchaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseController() {
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

	protected void Process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = request.getSession();
		Usermodel u = (Usermodel) s.getAttribute("USER");
		@SuppressWarnings("unchecked")
		List<CartItem> cart = (List<CartItem>) s.getAttribute("cart");
		int price = 0;
		for (CartItem cartItem : cart) {
			price += cartItem.getQuantity() * cartItem.getPrice();
		}
		if (cart.size() > 0) {
			OrderModel order = new OrderModel();
			order.setCustomerID(u.getId());
			order.setTotalAmount(price);
			order.setOrderDate(LocalDateTime.now());
			order.setStatus("Dual-wait processing");
			OrderDAO dao = new OrderDAO();
			int orderId = dao.addOrder(order);
			for (int i = 0; i < cart.size(); i++) {
				CartItem cartItem = cart.get(i);
				OrderDetailModel oDetail = new OrderDetailModel();
				oDetail.setOrderID(orderId);
				oDetail.setProductID(cartItem.getProduct().getProductID());
				oDetail.setQuantity(cartItem.getQuantity());
				oDetail.setPaymentMethod("ad");
				OrderDetailDAO odao = new OrderDetailDAO();
				odao.addOrderDetail(oDetail);
				ProductDAO daoP = new ProductDAO();
				int newAmount = cartItem.getProduct().getAmount() - cartItem.getQuantity();
				int newSold = cartItem.getProduct().getSold() + cartItem.getQuantity();
				boolean newStatus;
				if (newAmount < 1) {
					newStatus = false;
				} else
					newStatus = true;

				daoP.updateProductAmountAndSold(cartItem.getProduct().getProductID(), newAmount, newSold, newStatus);
			}

		}

		s.setAttribute("cart", null);
		/*
		 * ProductDAO dao = new ProductDAO(); SupplierDAO daoS = new SupplierDAO();
		 * CategoryDAO daoC = new CategoryDAO(); List<ProductModel> proList =
		 * dao.getAllProduct(); request.setAttribute("listP", proList);
		 * List<SupplierModel> supList = daoS.getAllSupplier();
		 * request.setAttribute("listS", supList); List<CategoryModel> categoryList =
		 * daoC.getAllCategory(); request.setAttribute("listC", categoryList);
		 * List<String> listColor = dao.getAllColor(); request.setAttribute("listCo",
		 * listColor);
		 */
		forwardToPage(request, response, "");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Process(request, response);
	}

}
