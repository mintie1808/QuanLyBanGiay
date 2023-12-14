package doan.Controller.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import doan.DAO.CategoryDAO;
import doan.DAO.ProductDAO;
import doan.DAO.SupplierDAO;
import doan.model.CartItem;
import doan.model.CategoryModel;
import doan.model.ProductModel;
import doan.model.SupplierModel;

/**
 * Servlet implementation class CheckCartController
 */
@WebServlet(urlPatterns = { "/check_out" })
public class CheckOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		request.setAttribute("page", page);
		String pageToInclude = "/views/web/home.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(pageToInclude);
		rd.forward(request, response);
	}
	protected void Process(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    if (isCartEmpty(request)) {
	    	ProductDAO dao = new ProductDAO();
	    	SupplierDAO daoS = new SupplierDAO();
			CategoryDAO daoC = new CategoryDAO();
	    	List<ProductModel> proList = dao.getAllProduct();
	    	request.setAttribute("listP", proList);
			List<SupplierModel> supList = daoS.getAllSupplier();
			request.setAttribute("listS", supList);
			List<CategoryModel> categoryList = daoC.getAllCategory();
			request.setAttribute("listC", categoryList);
			List<String> listColor = dao.getAllColor();
			request.setAttribute("listCo", listColor);
	        forwardToPage(request, response, "shop");
	    } else if (isUserLoggedIn(request)) {
    		forwardToPage(request, response, "check_out");
    	} else
    		forwardToPage(request, response, "login");
	    }

	private boolean isCartEmpty(HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    @SuppressWarnings("unchecked")
	    List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
	    return cart == null || cart.isEmpty();
	}
	 private boolean isUserLoggedIn(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        return session != null && session.getAttribute("USER") != null;
	    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Process(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Process(request, response);
	}

}
