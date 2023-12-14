package doan.Controller.web;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import doan.DAO.UserDAO;
import doan.model.OrderModel;
import doan.model.Usermodel;
import doan.utils.PasswordHashing;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = { "/user" })
@MultipartConfig
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("USER") == null) {
	        response.sendRedirect(request.getContextPath() + "/trang-chu");
	        return;
	    }

	    UserDAO dao = new UserDAO();
	    if (session != null) {
	        Usermodel umodel = (Usermodel) session.getAttribute("USER");
	        if (umodel != null) {
	            int id = umodel.getId();

	            List<OrderModel> listorder = dao.findOrdersByCustomerId(id);


	            request.setAttribute("listOrder", listorder);
	        }
	    }

	    request.setAttribute("page", "edit_profile");
	    String pageToInclude = "/views/web/home.jsp";
	    RequestDispatcher rd = request.getRequestDispatcher(pageToInclude);
	    rd.forward(request, response);
	}


	private boolean isValid(String value, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String formName = request.getParameter("name");
		HttpSession session = request.getSession(false);
		Usermodel updatedUser = new Usermodel();
		UserDAO dao = new UserDAO();
		if (session != null) {
			Usermodel umodel = (Usermodel) session.getAttribute("USER");
			if (umodel != null) {
				int id = umodel.getId();

				if ("form1".equals(formName)) {
					String val_username = request.getParameter("val-username");
					String val_email = request.getParameter("val-email");
					String val_fullname = request.getParameter("val-fullname");
					String val_phoneus = request.getParameter("val-phoneus");
					String val_address = request.getParameter("val-address");

					Part filePart = request.getPart("fileInput");

					// Kiểm tra xem có file ảnh được chọn không
					if (filePart != null && filePart.getSize() > 0) {
						String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

						// Đường dẫn tuyệt đối đến thư mục nguồn của dự án

//    	                String uploadPath = "A:\\eclipse\\ai\\doan\\src\\main\\webapp"+ File.separator + "img" + File.separator + "user";
//    	                filePart.write(uploadPath + File.separator + fileName);
						//
//    	                // Tạo thư mục nếu nó không tồn tại
//    	                File uploadDir = new File(uploadPath);
//    	                if (!uploadDir.exists()) {
//    	                    uploadDir.mkdirs();  // Sử dụng mkdirs() để tạo nhiều thư mục cấp cao hơn nếu cần
//    	                }

						// Tạo đường dẫn đầy đủ cho ảnh
						String imgPath = fileName;
						String uploadPathtem = getServletContext().getRealPath("/img/user");
						File uploadDirtem = new File(uploadPathtem);
						if (!uploadDirtem.exists()) {
							uploadDirtem.mkdirs(); // Sử dụng mkdirs() để tạo nhiều thư mục cấp cao hơn nếu cần
						}

						filePart.write(uploadPathtem + File.separator + fileName);
						// Lưu file ảnh vào thư mục

						// Tạo đối tượng Usermodel và cập nhật thông tin ảnh

						updatedUser.setImg(imgPath);

						// Gọi hàm cập nhật trong DAO

						dao.updateUserData(id, updatedUser);

						umodel.setImg(imgPath);
						session.setAttribute("USER", umodel);

					} else {
						updatedUser.setUsername(val_username);
						updatedUser.setEmail(val_email);
						updatedUser.setFullname(val_fullname);
						updatedUser.setPhone(Integer.parseInt(val_phoneus));
						updatedUser.setAddress(val_address);
						dao.updateUserData(id, updatedUser);

						umodel.setUsername(val_username);
						umodel.setEmail(val_email);
						umodel.setFullname(val_fullname);
						umodel.setPhone(Integer.parseInt(val_phoneus));
						umodel.setAddress(val_address);
						session.setAttribute("USER", umodel);
					}
					request.setAttribute("page", "edit_profile");
					String pageToInclude = "/views/web/home.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(pageToInclude);
					rd.forward(request, response);
				} else if ("form2".equals(formName)) {
					String val_current_pass = request.getParameter("val-current-pass");
					String val_new_pass = request.getParameter("val-new-pass");
					String val_confirm_new_password = request.getParameter("val-confirm-new-password");
					String hashedInputPassword = PasswordHashing.hashPassword(val_current_pass);
					String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])[a-zA-Z\\d@#$%^&+=]{6,}$";

					if (hashedInputPassword.equals(umodel.getPassword())) {
						if (val_new_pass.equals(val_current_pass)) {
							request.setAttribute("val_current_pass", val_current_pass);
							request.setAttribute("p_mes", "New password must be different from the current password.");
							forwardToPage(request, response, "Password mismatch!", "edit_profile",
									"/views/web/home.jsp", "message");
						} else if (val_new_pass.length() < 6) {
							request.setAttribute("val_current_pass", val_current_pass);
							request.setAttribute("p_mes", "Password must be at least 6 characters long.");
							forwardToPage(request, response, "Password too short", "edit_profile",
									"/views/web/home.jsp", "message");
						} else if (!isValid(val_new_pass, passwordRegex)) {
							request.setAttribute("val_current_pass", val_current_pass);
							request.setAttribute("p_mes",
									"Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character.");
							forwardToPage(request, response, "Weak password", "edit_profile", "/views/web/home.jsp",
									"message");
							return;
						} else if (val_new_pass.equals(val_confirm_new_password)) {
							updatedUser.setPassword(PasswordHashing.hashPassword(val_new_pass));
							dao.updateUserData(id, updatedUser);
							umodel.setPassword(val_new_pass);
							session.setAttribute("USER", umodel);
							session.setAttribute("message_success", "Successfully Logged In");
							response.sendRedirect(request.getContextPath() + "/user");
							return;
						} else {
							request.setAttribute("val_current_pass", val_current_pass);
							System.out.println(val_current_pass);
							request.setAttribute("p_mes", "Please enter the password again.");
							forwardToPage(request, response, "Password mismatch!", "edit_profile",
									"/views/web/home.jsp", "message");
						}
					} else {
						request.setAttribute("p_mes", "Please enter correct information!");
						forwardToPage(request, response, "Incorrect password!", "edit_profile", "/views/web/home.jsp",
								"message");
					}
				}
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/trang-chu");
			return;
		}
	}
}
