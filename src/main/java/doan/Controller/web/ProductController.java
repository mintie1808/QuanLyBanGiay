package doan.Controller.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import doan.DAO.ProductDAO;
import doan.model.CategoryModel;
import doan.model.ProductModel;
import doan.model.SupplierModel;

/**
 * Servlet implementation class ShowProductController
 */
@WebServlet(urlPatterns = { "/shop" })
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
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

	protected void FilterProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//tiến 
		ProductDAO dao = new ProductDAO();
		String cateID = request.getParameter("cid");
		String pri = request.getParameter("pid");
		//nguyên
		String searchTerm = request.getParameter("textsearchName");

		if (cateID != null && !cateID.isEmpty()) {
			//tiến
			List<ProductModel> productListct = dao.getAllProductByCategory(Integer.parseInt(cateID));
			request.setAttribute("listP", productListct);
			request.setAttribute("tag", cateID);
		} else if (pri != null && !pri.isEmpty()) {
			//tiến
			List<ProductModel> productList = new ArrayList<ProductModel>();
			String arr[] = pri.split(" ");
			if (arr[1].contains("top")) {
				productList = dao.getAllProductByPrice(Integer.parseInt(arr[0]));
			} else {
				int first = Integer.parseInt(arr[0]);
				int last = Integer.parseInt(arr[1]);
				productList = dao.getAllProductByPrice(first, last);
			}
			request.setAttribute("listP", productList);
		} else if(searchTerm != null && !searchTerm.isEmpty()){
			//nguyên
	        List<ProductModel> searchResults = dao.findByName(searchTerm);
	        request.setAttribute("listP", searchResults);
		}
		else {
			//tiến
			List<ProductModel> productList = dao.getAllProduct();
			request.setAttribute("listP", productList);
		}
	}

	protected void FilterPriceController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductDAO dao = new ProductDAO();
		List<SupplierModel> supList = dao.getAllSupplier();
		request.setAttribute("listS", supList);
		List<CategoryModel> categoryList = dao.getAllCategory();
		request.setAttribute("listC", categoryList);

		FilterProduct(request, response);

		forwardToPage(request, response, "shop");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
		processRequest(request, response);
	}

}
