package doan.Controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.Normalizer;
import java.util.List;

import doan.DAO.ProductDAO;
import doan.DAO.SupplierDAO;
import doan.model.CategoryModel;
import doan.model.ProductModel;
import doan.model.SupplierModel;

/**
 * Servlet implementation class Productcontroller_a
 */
@WebServlet(urlPatterns = "/admin-product")
public class Productcontroller_a extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Productcontroller_a() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static String removeAccentsAndConvertToLowerCase(String input) {
	    String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
	    String withoutAccents = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	    return withoutAccents.toLowerCase();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	private void addProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Lấy thông tin từ form và tạo đối tượng ProductModel
		String productName = request.getParameter("productName");
		int productPrice = Integer.parseInt(request.getParameter("productPrice"));
		String color = request.getParameter("color");
		String description = request.getParameter("description");
		int amount = Integer.parseInt(request.getParameter("amount"));
		String img = request.getParameter("img");
		// Lấy thông tin từ các ô select
		int category_id = Integer.parseInt(request.getParameter("category"));
		int supplierID = Integer.parseInt(request.getParameter("supplier"));

		ProductModel newProduct = new ProductModel();
		newProduct.setProductName(productName);
		newProduct.setPrice(productPrice);
		newProduct.setSeotitle(removeAccentsAndConvertToLowerCase(productName));
		newProduct.setColor(color);
		newProduct.setDescription(description);
		newProduct.setAmount(amount);
		newProduct.setCategory_id(category_id);
		newProduct.setSupplierID(supplierID);
		newProduct.setImg(img);

		// Thực hiện thêm sản phẩm vào cơ sở dữ liệu
		ProductDAO ser = new ProductDAO();
		ser.addProduct(newProduct);
		ProductDAO dao = new ProductDAO();
		// Cập nhật dữ liệu
		List<ProductModel> updatedProductList = dao.getAllProduct();
		request.setAttribute("listP", updatedProductList);
		request.setAttribute("page", "product_manager");

	}

	private void editProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int productId = Integer.parseInt(request.getParameter("productId"));
			ProductDAO dao = new ProductDAO();
			ProductModel product = dao.findById(productId);
			request.setAttribute("disabled", "disabled");
			// xuat ra voi value product
			request.setAttribute("product", product);
			System.out.println(product.getProductName());
			request.setAttribute("page", "product_manager");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error:" + e.getMessage());
		}

	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Lấy thông tin từ form và tạo đối tượng ProductModel
		int productId = Integer.parseInt(request.getParameter("productId"));
		String productName = request.getParameter("productName");
		int productPrice = Integer.parseInt(request.getParameter("productPrice"));
		String color = request.getParameter("color");
		String description = request.getParameter("description");
		int amount = Integer.parseInt(request.getParameter("amount"));
		String img = request.getParameter("img");

		// Lấy thông tin từ các ô select
		int category_id = Integer.parseInt(request.getParameter("category"));
		int supplierID = Integer.parseInt(request.getParameter("supplier"));

		ProductModel updatedProduct = new ProductModel();
		updatedProduct.setProductID(productId);
		updatedProduct.setProductName(productName);
		updatedProduct.setPrice(productPrice);
		updatedProduct.setSeotitle(removeAccentsAndConvertToLowerCase(productName));
		updatedProduct.setColor(color);
		updatedProduct.setDescription(description);
		updatedProduct.setAmount(amount);
		updatedProduct.setCategory_id(category_id);
		updatedProduct.setSupplierID(supplierID);
		updatedProduct.setImg(img);

		// Thực hiện cập nhật sản phẩm vào cơ sở dữ liệu
		ProductDAO productService = new ProductDAO();
		productService.updateProduct(updatedProduct);

		ProductDAO dao = new ProductDAO();
		// Cập nhật dữ liệu
		List<ProductModel> updatedProductList = dao.getAllProduct();
		request.setAttribute("listP", updatedProductList);
		request.setAttribute("page", "product_manager");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * if (!AccessUtils.checkUserRole(request, response)) { return; }
		 */
		SupplierDAO supdao = new SupplierDAO();
		ProductDAO dao = new ProductDAO();

		List<SupplierModel> Supplier = supdao.getAllSupplier();
		List<ProductModel> productList = dao.getAllProduct();
		List<CategoryModel> categoryList = dao.getAllCategory();
		request.setAttribute("listS", Supplier);
		request.setAttribute("listC", categoryList);
		request.setAttribute("listP", productList);
		request.setAttribute("page", "product_manager");

		String action = request.getParameter("action");

		if ("add".equals(action)) {
			addProduct(request, response);
			response.sendRedirect(request.getContextPath() + "/admin-product");
			return;
		} else if ("edit".equals(action)) {

			editProduct(request, response);
			// response.sendRedirect(request.getContextPath() + "/productedit");
			// return;
		} else if ("delete".equals(action)) {
			int productId = Integer.parseInt(request.getParameter("productId"));
			ProductDAO productService = new ProductDAO();
			productService.deleteProductById(productId);
			response.sendRedirect(request.getContextPath() + "/admin-product");
			return;
		} else if ("update".equals(action)) {

			updateProduct(request, response);
			response.sendRedirect(request.getContextPath() + "/admin-product");
			return;
		}

		String pageToInclude = "/views/admin/home.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(pageToInclude);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] selectedIds = request.getParameterValues("selectedIds");
		ProductDAO supdao = new ProductDAO();
		if (selectedIds != null) {
			// Xóa từng id được chọn
			for (String id : selectedIds) {
				int supId = Integer.parseInt(id);
				supdao.deleteById(supId);
			}
		}
		response.sendRedirect("./admin-product");
	}

}
