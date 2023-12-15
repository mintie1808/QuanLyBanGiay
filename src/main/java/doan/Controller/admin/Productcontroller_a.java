package doan.Controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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
@MultipartConfig
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

	private ProductModel getProductFromRequest(HttpServletRequest request) throws IOException, ServletException {

		// Lấy thông tin từ form và tạo đối tượng ProductModel
		String productName = request.getParameter("productName");
		int productPrice = Integer.parseInt(request.getParameter("productPrice"));
		String color = request.getParameter("color");
		String description = request.getParameter("description");
		String detail = request.getParameter("detail");
		int amount = Integer.parseInt(request.getParameter("amount"));
		// Lấy thông tin từ các ô select
		int category_id = Integer.parseInt(request.getParameter("category"));
		int supplierID = Integer.parseInt(request.getParameter("supplier"));

		ProductModel Product = new ProductModel();
		Part filePart = request.getPart("fileInput");
		String imgPath = "";
		if (filePart != null && filePart.getSize() > 0) {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			imgPath = fileName;
			String uploadPath = getServletContext().getRealPath("/img/product");
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			filePart.write(uploadPath + File.separator + fileName);
		}
		Product.setImg(imgPath);

		Product.setProductName(productName);
		Product.setPrice(productPrice);
		Product.setSeotitle(removeAccentsAndConvertToLowerCase(productName));
		Product.setColor(color);
		Product.setDescription(description);
		Product.setDetail(detail);
		Product.setAmount(amount);
		Product.setCategory_id(category_id);
		Product.setSupplierID(supplierID);

		Product.setStatus(true);
		Product.setHot(true);
		Product.setSALE(true);
		Product.setSold(0);
		return Product;
	}

	private void editProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("productId"));
		ProductDAO dao = new ProductDAO();
		ProductModel product = dao.findById(productId);
		request.setAttribute("disabled", "disabled");
		// xuat ra voi value product
		request.setAttribute("product", product);
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
		String action = request.getParameter("action");
		ProductDAO supdao = new ProductDAO();
		if (selectedIds != null) {
			// Xóa từng id được chọn
			for (String id : selectedIds) {
				int supId = Integer.parseInt(id);
				supdao.deleteById(supId);
			}
		} else if ("delete".equals(action)) {
			int productId = Integer.parseInt(request.getParameter("productId"));
			ProductDAO productService = new ProductDAO();
			productService.deleteProductById(productId);
			response.sendRedirect(request.getContextPath() + "/admin-product");
			return;
		} else if ("edit".equals(action)) {

			editProduct(request, response);
			doGet(request, response);
			return;
		} else if ("update".equals(action)) {
			ProductModel productModel = getProductFromRequest(request);
			String productid = request.getParameter("updateproduct");
			supdao.updateProduct(Integer.parseInt(productid),productModel);
		}
		if ("add".equals(action)) {
			ProductModel proModel = getProductFromRequest(request);
			supdao.addProduct(proModel);
		}

		response.sendRedirect("./admin-product");
	}

}
