package doan.Controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import doan.DAO.ProductDAO;
import doan.DAO.SupplierDAO;
import doan.model.ProductModel;
import doan.model.SupplierModel;

/**
 * Servlet implementation class Supplier_a
 */
@WebServlet(urlPatterns = "/admin-Supplier")
public class Supplier_a extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Supplier_a() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void addSupplier(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Lấy thông tin từ form và tạo đối tượng SupplierModel
		String supplierName = request.getParameter("supplierName");
		String address = request.getParameter("address");
		String productsSupplied = request.getParameter("productsSupplied");
		String paymentTerms = request.getParameter("paymentTerms");

		SupplierModel newSupplier = new SupplierModel();
		newSupplier.setSupplierName(supplierName);
		newSupplier.setAddress(address);
		newSupplier.setProductsSupplied(productsSupplied);
		newSupplier.setPaymentTerms(paymentTerms);

		// Thực hiện thêm nhà cung cấp vào cơ sở dữ liệu
		SupplierDAO supplierdao = new SupplierDAO();
		supplierdao.addSupplier(newSupplier);

		// Cập nhật dữ liệu
		// (Tuỳ thuộc vào cách bạn tổ chức dữ liệu, có thể cần gọi DAO để lấy danh sách
		// nhà cung cấp)
		// SupplierDAO dao = new SupplierDAO();
		List<SupplierModel> updatedSupplierList = supplierdao.getAllSupplier();
		request.setAttribute("listS", updatedSupplierList);
		request.setAttribute("page", "supplier_manager");

	}

	private void editSupplier(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int supplierID = Integer.parseInt(request.getParameter("supplierID"));
			SupplierDAO dao = new SupplierDAO();
			SupplierModel supplier = dao.findById(supplierID);
			request.setAttribute("disabled", "disabled");
			// xuat ra voi value supplier
			request.setAttribute("supplier", supplier);
			System.out.println(supplier.getSupplierName());
			request.setAttribute("page", "supplier_manager");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error:" + e.getMessage());
		}

	}

	private void updateSupplier(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Lấy thông tin từ form và tạo đối tượng SupplierModel
		int supplierID = Integer.parseInt(request.getParameter("supplierID"));
		System.out.println(supplierID);
		String supplierName = request.getParameter("supplierName");
		String address = request.getParameter("address");
		String productsSupplied = request.getParameter("productsSupplied");
		String paymentTerms = request.getParameter("paymentTerms");

		SupplierModel updatedSupplier = new SupplierModel();
		updatedSupplier.setSupplierID(supplierID);
		updatedSupplier.setSupplierName(supplierName);
		updatedSupplier.setAddress(address);
		updatedSupplier.setProductsSupplied(productsSupplied);
		updatedSupplier.setPaymentTerms(paymentTerms);

		// Thực hiện cập nhật nhà cung cấp vào cơ sở dữ liệu
		SupplierDAO supplierdao = new SupplierDAO();
		supplierdao.updateSupplier(updatedSupplier);
		List<SupplierModel> updatedSupplierList = supplierdao.getAllSupplier();

		request.setAttribute("listS", updatedSupplierList);
		request.setAttribute("page", "supplier_manager");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		SupplierDAO supdao = new SupplierDAO();
		List<SupplierModel> Supplier = supdao.getAllSupplier();
		ProductDAO dao = new ProductDAO();
		List<ProductModel> productList = dao.getAllProduct();

		request.setAttribute("listP", productList);
		request.setAttribute("listS", Supplier);
		request.setAttribute("page", "supplier_manager");

		String action = request.getParameter("action");
		if ("add".equals(action)) {
			addSupplier(request, response);
			response.sendRedirect(request.getContextPath() + "/admin-Supplier");
			return;
		} else if ("edit".equals(action)) {

			editSupplier(request, response);
			// response.sendRedirect(request.getContextPath() + "/supplieredit");
			// return;
		} else if ("delete".equals(action)) {
			int supplierID = Integer.parseInt(request.getParameter("supplierID"));
			int sp = Integer.parseInt(request.getParameter("sp"));
			supdao.deleteSupplierById(supplierID, sp);
			response.sendRedirect(request.getContextPath() + "/admin-Supplier");
			return;

			// response.sendRedirect(request.getContextPath() + "./supplieredit");
//            return;   // Tránh tiếp tục thực hiện forward và các thao tác khác sau khi chuyển hướng
		} else if ("update".equals(action)) {

			updateSupplier(request, response);
			response.sendRedirect(request.getContextPath() + "/admin-Supplier");
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
		SupplierDAO supdao = new SupplierDAO();
		if (selectedIds != null) {
			// Xóa từng id được chọn
			for (String id : selectedIds) {
				int supId = Integer.parseInt(id);
				supdao.deleteById(supId);
			}
		}
		response.sendRedirect("./admin-Supplier");
	}

}
