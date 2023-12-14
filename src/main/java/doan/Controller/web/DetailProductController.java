package doan.Controller.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import doan.DAO.CategoryDAO;
import doan.DAO.ProductDAO;
import doan.model.ProductModel;


/**
 * Servlet implementation class DetailProductController
 */
@WebServlet(urlPatterns = { "/shop_details" })
public class DetailProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailProductController() {
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
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String proid = request.getParameter("proid");
		ProductDAO dao = new ProductDAO();
		CategoryDAO daoC = new CategoryDAO();
		int cateID = daoC.getCateIdByProductId(Integer.parseInt(proid));
		String cateName = daoC.getNameCateById(cateID);
		List<ProductModel> productList = dao.getProductById(Integer.parseInt(proid));
		List<ProductModel> relatedList = dao.getRelatedProductByCategory(cateID);
		
		
		request.setAttribute("listP", productList);
		request.setAttribute("relatedList", relatedList);
		request.setAttribute("cateName", cateName);
		forwardToPage(request, response, "shop_details");
	}
	

	
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
