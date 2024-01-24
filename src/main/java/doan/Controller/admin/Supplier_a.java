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
import doan.utils.AccessUtils;

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

	private void editSupplier(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int supplierID = Integer.parseInt(request.getParameter("supplierID"));
			SupplierDAO dao = new SupplierDAO();
			SupplierModel supplier = dao.findById(supplierID);
			request.setAttribute("disabledsup", "disabled");
			// xuat ra voi value supplier
			request.setAttribute("supplier", supplier);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error:" + e.getMessage());
		}

	}

	private SupplierModel getSupplierFromRequest(HttpServletRequest request) throws IOException, ServletException {

		// Lấy thông tin từ form và tạo đối tượng SupplierModel
		String supplierName = request.getParameter("supplierName");
		String address = request.getParameter("address");
		String productsSupplied = request.getParameter("productsSupplied");
		String paymentTerms = request.getParameter("paymentTerms");

		SupplierModel updatedSupplier = new SupplierModel();

		updatedSupplier.setSupplierName(supplierName);
		updatedSupplier.setAddress(address);
		updatedSupplier.setProductsSupplied(productsSupplied);
		updatedSupplier.setPaymentTerms(paymentTerms);

		// Thực hiện cập nhật nhà cung cấp vào cơ sở dữ liệu
		return updatedSupplier;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!AccessUtils.checkUserRole(request, response)){
			return;
		}
		SupplierDAO supdao = new SupplierDAO();
		List<SupplierModel> Supplier = supdao.getAllSupplier();
		ProductDAO dao = new ProductDAO();
		List<ProductModel> productList = dao.getAllProduct();

		request.setAttribute("listP", productList);
		request.setAttribute("listS", Supplier);
		request.setAttribute("page", "supplier_manager");
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
		String action = request.getParameter("action");

		SupplierDAO supplierdao = new SupplierDAO();
		if (selectedIds != null) {
			// Xóa từng id được chọn
			for (String id : selectedIds) {
				int supId = Integer.parseInt(id);
				supdao.deleteProductsBySupplierID(supId);
				supdao.deleteById(supId);
			}
		} else if ("delete".equals(action)) {
			int supplierID = Integer.parseInt(request.getParameter("supplierID"));

			supdao.deleteProductsBySupplierID(supplierID);
			supdao.deleteSupplierById(supplierID);
		} else if ("edit".equals(action)) {

			editSupplier(request, response);
			doGet(request, response);
			return;

		} else if ("update".equals(action)) {
			SupplierModel supplierModel = getSupplierFromRequest(request);
			String updatesupplier = request.getParameter("updatesupplier");
			supplierdao.updateSupplier(Integer.parseInt(updatesupplier), supplierModel);
		} else if ("add".equals(action)) {
			SupplierModel supplierModel = getSupplierFromRequest(request);
			supplierdao.addSupplier(supplierModel);
		}
		response.sendRedirect("./admin-Supplier");
	}

}
