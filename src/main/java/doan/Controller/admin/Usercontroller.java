package doan.Controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import doan.DAO.RoleDAO;
import doan.DAO.UserDAO;
import doan.model.RoleModel;
import doan.model.Usermodel;
import doan.utils.AccessUtils;
import doan.utils.PasswordHashing;

/**
 * Servlet implementation class Usercontroller
 */
@WebServlet(urlPatterns = "/admin-user")
@MultipartConfig
public class Usercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Usercontroller() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void forwardToPage(HttpServletRequest request, HttpServletResponse response, String message, String page,
			String pageToInclude, String messageAttributeName) throws ServletException, IOException {
		request.setAttribute(messageAttributeName, message);
		request.setAttribute("page", page);
		RequestDispatcher rd = request.getRequestDispatcher(pageToInclude);
		rd.forward(request, response);
	}

	private boolean isValidEmail(String email) {
		// Regex để kiểm tra địa chỉ email
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		return email.matches(emailRegex);
	}

	private Usermodel getUserFromRequest(HttpServletRequest request) throws IOException, ServletException {
		String username = request.getParameter("username");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		int role = Integer.parseInt(request.getParameter("select_role"));
		int phone = Integer.parseInt(request.getParameter("phone"));
		String password = PasswordHashing.hashPassword("malefashion");

		Part filePart = request.getPart("fileInput");
		String imgPath = "";
		if (filePart != null && filePart.getSize() > 0) {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			imgPath = fileName;
			String uploadPath = getServletContext().getRealPath("/img/user");
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			filePart.write(uploadPath + File.separator + fileName);
		}
		Usermodel newuser = new Usermodel();
		newuser.setUsername(username);
		newuser.setPassword(password);
		newuser.setFullname(fullname);
		newuser.setEmail(email);
		newuser.setAddress(address);
		newuser.setRole(role);
		newuser.setPhone(phone);
		newuser.setImg(imgPath);
		newuser.setStatus("2");

		return newuser;
	}

	private void editProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userid = Integer.parseInt(request.getParameter("userIdToDelete"));
		UserDAO usdao = new UserDAO();
		Usermodel useredit = usdao.findById(userid);
		request.setAttribute("listuseredit", useredit);
		request.setAttribute("status", "status");

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

		long cout = lsuser.stream().filter(item -> item.getId() != 1).count();
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
		UserDAO userdao = new UserDAO();
		if (selectedIds != null) {
			// Xóa từng id được chọn
			for (String id : selectedIds) {
				int userId = Integer.parseInt(id);

				userdao.deleteById(userId);
			}
		}
		if ("delete".equals(action)) {
			String userIdToDelete = request.getParameter("userId");
			if (userIdToDelete != null && !userIdToDelete.isEmpty()) {
				int userId = Integer.parseInt(userIdToDelete);
				UserDAO userDAO = new UserDAO();
				userDAO.deleteById(userId);
				response.sendRedirect(request.getContextPath() + "/admin-user");
				return;
			}
		} else if ("add".equals(action)) {
			String email = request.getParameter("email");
			String mes = "message";
			if (!isValidEmail(email)) {
				request.setAttribute(mes, "Invalid email format");
				doGet(request, response);
				return;
			} else {
				Usermodel newuser = getUserFromRequest(request);
				userdao.create(newuser);
				HttpSession session = request.getSession();
				session.setAttribute("message_add", "Successfully Add");
				session.setAttribute("message_adds", "The default password is set to: malefashion.");
			}
		} else if ("edit".equals(action)) {

			editProduct(request, response);
			doGet(request, response);
			return;

		} else if ("update".equals(action)) {
			Usermodel newuser = getUserFromRequest(request);

			String userid = request.getParameter("updateuser");
			userdao.updateUserData(Integer.parseInt(userid), newuser);
		}
		response.sendRedirect(request.getContextPath() + "/admin-user");
//		doGet(request, response);
	}
}
