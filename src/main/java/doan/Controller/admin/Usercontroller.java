package doan.Controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import doan.DAO.RoleDAO;
import doan.DAO.UserDAO;
import doan.model.RoleModel;
import doan.model.Usermodel;
import doan.utils.PasswordHashing;

/**
 * Servlet implementation class Usercontroller
 */
@WebServlet(urlPatterns = "/admin-user")
public class Usercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Usercontroller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RoleDAO roledao = new RoleDAO();
		List<RoleModel> listrole = roledao.findAll();
		request.setAttribute("listrole", listrole);
		String SearchQuery = request.getParameter("txtsearch");
		if (SearchQuery == null || SearchQuery.isEmpty()) {
			SearchQuery = ""; // hoặc bất kỳ giá trị mặc định khác nếu cần
		}
		request.setAttribute("txtsearch", SearchQuery);
//		int pages = 1;
//		if (request.getParameter("pages") != null && request.getParameter("pages") != "") {
//			pages = Integer.parseInt(request.getParameter("pages"));
//		}
//		int limit = 6;

		// Lấy danh sách người dùng từ CSDL hoặc một nguồn dữ liệu khác
		UserDAO udao = new UserDAO();
		List<Usermodel> lsuser = udao.query(SearchQuery);
		request.setAttribute("listUser", lsuser);

//		int itemCount = lsuser.size();
//		int tocalrecord =(int) udao.count(SearchQuery);
//		int totalpage = (int) Math.ceil((float)tocalrecord/(float)limit);
//		request.setAttribute("itemCounts", itemCount);
//		request.setAttribute("totalpage", totalpage);
//		request.setAttribute("itemcount", pages);

		long cout = lsuser.stream().filter(item -> item.getRole() != 1 && item.getRole() != 2).count();
		request.setAttribute("itemcounttotal", cout);
		request.setAttribute("page", "user_management");
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/home.jsp");
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
		System.out.println("abc:" + action);
		if (selectedIds != null) {
			// Xóa từng id được chọn
			for (String id : selectedIds) {
				int userId = Integer.parseInt(id);
				UserDAO userDAO = new UserDAO();
				userDAO.deleteById(userId);
			}
		}
		if ("delete".equals(action)) {
			String userIdToDelete = request.getParameter("userId");
			if (userIdToDelete != null && !userIdToDelete.isEmpty()) {
				int userId = Integer.parseInt(userIdToDelete);
				UserDAO userDAO = new UserDAO();
				userDAO.deleteById(userId);
			}
		} else if ("add".equals(action)) {
			System.out.println("aaaaaaa");
			String username = request.getParameter("username");
			String fullname = request.getParameter("fullname");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			int role = Integer.parseInt(request.getParameter("select_role"));
			int phone = Integer.parseInt(request.getParameter("phone"));
			Part filePart = request.getPart("fileInput");
			if (filePart != null && filePart.getSize() > 0) {
				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

				String imgPath = fileName;
				String uploadPathtem = getServletContext().getRealPath("/img/user");
				File uploadDirtem = new File(uploadPathtem);
				if (!uploadDirtem.exists()) {
					uploadDirtem.mkdirs(); // Sử dụng mkdirs() để tạo nhiều thư mục cấp cao hơn nếu cần
				}

				filePart.write(uploadPathtem + File.separator + fileName);
			System.out.println("Username: " + username);
			System.out.println("Fullname: " + fullname);
			System.out.println("Email: " + email);
			System.out.println("Address: " + address);
			System.out.println("Role: " + role);
			// Tạo đối tượng Usermodel
			Usermodel newuser = new Usermodel();
			newuser.setUsername(username);
			newuser.setPassword(PasswordHashing.hashPassword("malefashion"));
			newuser.setFullname(fullname);
			newuser.setEmail(email);
			newuser.setAddress(address);
			newuser.setRole(role);
			newuser.setPhone(phone);
			newuser.setStatus("2");
			newuser.setImg(imgPath);
			// Thêm người dùng vào cơ sở dữ liệu
			UserDAO userdao = new UserDAO();
			userdao.create(newuser);
			}
		}
		response.sendRedirect("./admin-user");

	}
}
