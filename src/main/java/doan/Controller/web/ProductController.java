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
import doan.DAO.SupplierDAO;

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

//
	protected void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		request.setAttribute("page", page);
		String pageToInclude = "/views/web/home.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(pageToInclude);
		rd.forward(request, response);

	}

	protected void FilterProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// tiến
		ProductDAO dao = new ProductDAO();
		String cateID = request.getParameter("cid");
		String pri = request.getParameter("pid");
		String supid = request.getParameter("supid");
		String colo = request.getParameter("coid");
		String prid = request.getParameter("prid");
		String pmin = request.getParameter("pmin");
		String pmax = request.getParameter("pmax");
		String sort = request.getParameter("sort");
		// nguyên
		String searchTerm = request.getParameter("textsearchName");

		int pages = 1;
		if (request.getParameter("pages") != null && request.getParameter("pages") != "") {
			pages = Integer.parseInt(request.getParameter("pages"));
		}

		if (cateID != null && !cateID.isEmpty()) {
			if (cateID == null || cateID.isEmpty()) {
				cateID = ""; 
			}
			request.setAttribute("cateID", cateID);
			// tiến

			int limit = 6;
			List<ProductModel> productListct = dao.getAllProductByCategory(Integer.parseInt(cateID),limit,pages);
			request.setAttribute("listP", productListct);
			request.setAttribute("tag", cateID);
			
			int itemCount = productListct.size();
			int tocalrecord =(int) dao.countCategory_id(Integer.parseInt(cateID));
			int totalpage = (int) Math.ceil((float)tocalrecord/(float)limit);

			request.setAttribute("itemCounts", itemCount);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("tocalrecord", tocalrecord);
			request.setAttribute("itemcount", pages);
		} else if (pri != null && !pri.isEmpty()) {
			// tiến
			List<ProductModel> productList = new ArrayList<ProductModel>();
			String arr[] = pri.split(" ");
			if (arr[1].contains("top")) {
				productList = dao.getAllProductByPriceMin(Integer.parseInt(arr[0]));
			} else {
				int first = Integer.parseInt(arr[0]);
				int last = Integer.parseInt(arr[1]);
				productList = dao.getAllProductByPrice(first, last);
			}
			request.setAttribute("listP", productList);
		} else if (prid != null && !prid.isEmpty()) {
			List<ProductModel> productListpr = new ArrayList<ProductModel>();
			if (pmin != null && !pmin.isEmpty()) {
				if (pmax != null && !pmax.isEmpty()) {
					productListpr = dao.getAllProductByPrice(Integer.parseInt(pmin), Integer.parseInt(pmax));
				} else
					productListpr = dao.getAllProductByPriceMin(Integer.parseInt(pmin));
			} else if (pmax != null && !pmax.isEmpty()) {
				productListpr = dao.getAllProductByPriceMax(Integer.parseInt(pmax));
			} else
				productListpr = dao.getAllProduct();

			request.setAttribute("listP", productListpr);
		} else if (sort != null && !sort.isEmpty()) {
			List<ProductModel> productListpri = new ArrayList<ProductModel>();
			if (sort.contains("LtoH")) {
				request.setAttribute("LtoH", true);
				productListpri = dao.getAllProductByPriceLowToHigh();
			} else {
				request.setAttribute("HtoL", true);
				productListpri = dao.getAllProductByPriceHighToLow();

			}
			request.setAttribute("listP", productListpri);
		}

		else if (supid != null && !supid.isEmpty()) {
			List<ProductModel> supList = dao.getAllProductBySupplier(Integer.parseInt(supid));
			request.setAttribute("listP", supList);
			request.setAttribute("tags", supid);
		} else if (colo != null && !colo.isEmpty()) {

			List<ProductModel> listPc = dao.getAllProductByColor(colo);
			request.setAttribute("listP", listPc);
		} else {
			// tiến
			if (searchTerm == null || searchTerm.isEmpty()) {
				searchTerm = ""; 
			}
			request.setAttribute("txtsearch", searchTerm);
			int limit = 6;
			List<ProductModel> searchResults = dao.findByName(searchTerm, limit, pages);
			
			int itemCount = searchResults.size();
			int tocalrecord =(int) dao.count(searchTerm);
			int totalpage = (int) Math.ceil((float)tocalrecord/(float)limit);

			request.setAttribute("itemCounts", itemCount);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("tocalrecord", tocalrecord);
			request.setAttribute("itemcount", pages);
			request.setAttribute("listP", searchResults);
		}
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductDAO dao = new ProductDAO();
		SupplierDAO daosp = new SupplierDAO();
		List<SupplierModel> supList = daosp.getAllSupplier();
		request.setAttribute("listS", supList);
		List<CategoryModel> categoryList = dao.getAllCategory();
		request.setAttribute("listC", categoryList);
		List<String> listColor = dao.getAllColor();
		request.setAttribute("listCo", listColor);
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
