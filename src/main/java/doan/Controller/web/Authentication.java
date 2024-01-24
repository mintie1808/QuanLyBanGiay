package doan.Controller.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import doan.DAO.UserDAO;
import doan.model.Usermodel;
import doan.utils.PasswordHashing;

/**
 * Servlet implementation class Authentication
 */
@WebServlet(urlPatterns = { "/login", "/register", "/forgot_password", "/ValidateOtp", "/newPassword" })
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authentication() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void handleRegistration(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String us = request.getParameter("us_register");
		String emrg = request.getParameter("email_register");
		String ps = request.getParameter("ps_register");
		String cf_ps = request.getParameter("cf_ps_register");
		String mes_sc = "message_success";
		String mes = "message";
		String page = "/views/web/home.jsp";

		UserDAO udao = new UserDAO();

		// Kiểm tra xem tên người dùng đã tồn tại hay chưa

		String gmailRegex = "^[a-zA-Z0-9_]+@gmail\\.com$";
		String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])[a-zA-Z\\d@#$%^&+=]{6,}$";
		if (udao.isUsernameExists(us)) {
			request.setAttribute("p_mes", "Please choose a different username.");
			forwardToPage(request, response, "Username already exists", "register", page, mes);
			return;
		} else if (udao.isEmailExists(emrg)) {
			request.setAttribute("p_mes", "Please choose a different email.");
			forwardToPage(request, response, "Email already exists", "register", page, mes);
			return;
		} else if (us.length() < 6) {
			request.setAttribute("p_mes", "Username must be at least 6 characters long.");
			forwardToPage(request, response, "Username too short", "register", page, mes);
			return;
		} else if (!isValid(emrg, gmailRegex)) {
			// Kiểm tra mật khẩu
			request.setAttribute("us_register", us);
			request.setAttribute("p_mes", "Please enter a valid email in the format example@gmail.com");
			forwardToPage(request, response, "Invalid email format", "register", page, mes);
			return;
		}

		else if (ps.length() < 6) {
			request.setAttribute("us_register", us);
			request.setAttribute("email_register", emrg);
			request.setAttribute("p_mes", "Password must be at least 6 characters long.");
			forwardToPage(request, response, "Password too short", "register", page, mes);
			return;
		} else if (!isValid(ps, passwordRegex)) {
			request.setAttribute("us_register", us);
			request.setAttribute("email_register", emrg);
			request.setAttribute("p_mes",
					"Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character.");
			forwardToPage(request, response, "Weak password", "register", page, mes);
			return;
		} else if (!ps.equals(cf_ps)) {
			request.setAttribute("us_register", us);
			request.setAttribute("email_register", emrg);
			request.setAttribute("p_mes", "Please enter the password again.");
			forwardToPage(request, response, "Password mismatch", "register", page, mes);
			return;
		}

		Usermodel newuser = new Usermodel();
		newuser.setUsername(us);
		newuser.setFullname(us);
		newuser.setPassword(PasswordHashing.hashPassword(ps));
		newuser.setEmail(emrg);
		newuser.setRole(3);
		newuser.setStatus("2");

		udao.create(newuser);

		// Chuyển hướng đến trang đăng nhập sau khi đăng ký
		request.getSession().setAttribute(mes_sc, "Registration Successful!");
		response.sendRedirect(request.getContextPath() + "/login");
	}

	protected void handleValidateOtp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String otpParameter = request.getParameter("otp");
		otpParameter = otpParameter != null ? otpParameter.trim() : null;

		HttpSession session = request.getSession();
		Integer otp = (Integer) session.getAttribute("otp");

		if (otp != null) {
			try {
				int value = Integer.parseInt(otpParameter);

				if (value == otp.intValue()) {
					request.setAttribute("page", "new_password");
				} else {
					request.setAttribute("p_mes", "Please enter correct information!");
					request.setAttribute("message", "Incorrect OTP ! ");
					request.setAttribute("page", "enter_otp");
				}

			} catch (NumberFormatException e) {
//				e.printStackTrace();
				// Xử lý exception nếu có
				request.setAttribute("p_mes", "Please enter correct information!");
				request.setAttribute("message", "Invalid OTP format!");
				request.setAttribute("page", "enter_otp");
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/forgot_password");
			return;
		}
	}

	private void handleNewPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("page", "new_password");
		String ps = request.getParameter("password");
		String cfps = request.getParameter("confPassword");
		HttpSession session = request.getSession();
		String mes = "message";
		String page = "/views/web/home.jsp";
		String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])[a-zA-Z\\d@#$%^&+=]{6,}$";
		String email = (String) session.getAttribute("email");

		if (ps.length() < 6) {
			request.setAttribute("p_mes", "Password must be at least 6 characters long.");
			forwardToPage(request, response, "Password too short", "new_password", page, mes);
			return;
		} else if (!isValid(ps, passwordRegex)) {
			request.setAttribute("p_mes",
					"Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character.");
			forwardToPage(request, response, "Weak password", "new_password", page, mes);
			return;
		} else if (!ps.equals(cfps)) {

			request.setAttribute("p_mes", "Please enter the password again.");
			forwardToPage(request, response, "Password mismatch", "new_password", page, mes);
			return;
		} else if (ps != null && cfps != null && ps.equals(cfps)) {
			UserDAO dao = new UserDAO();

			dao.updatePassword(email, PasswordHashing.hashPassword(ps));
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} else {
			request.setAttribute("p_mes", "Please enter correct information!");
			forwardToPage(request, response, "Incorrect password!", "new_password", "/views/web/home.jsp", "message");
		}

	}

	private void handleForgotPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String em = request.getParameter("em_forgot_password");
		UserDAO udao = new UserDAO();
		String mes = "message";
		if (!udao.isEmailExists(em)) {
			request.setAttribute("p_mes", "Please enter correct information!");
			forwardToPage(request, response, "Email Address is Empty or Not Found !", "forgot_password",
					"/views/web/home.jsp", mes);
			return;
		} else if (em != null && !em.isEmpty()) {
			// RequestDispatcher dispatcher = null;
			int otpvalue = 0;
			HttpSession mySession = request.getSession(false); // Pass false to not create a new session if it doesn't
			// sending otp
			if (mySession != null) {
				
				Random rand = new Random();
				otpvalue = rand.nextInt(1255650);
				String to = em;
				
				String subject = "Password Recovery - OTP " + otpvalue;

				String emailContent = "<div style='text-align: center; background: #555555; color: white;'>"
						+ "<br><p style='font-size: 24px; margin: 0;'>Password Recovery</p>"
						+ "<p style='font-size: 16px; margin: 10px 0;'>Your OTP for password recovery is:</p>"
						+ "<p style='font-size: 36px; font-weight: bold; margin: 10px 0;'>" + otpvalue + "</p>"
						+ "<p style='font-size: 16px; margin: 10px 0;'>Use this OTP to reset your password.</p>"
						+ "<br></div>";

				// Get the session object
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");
				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("trongtruong0908@gmail.com", "ghoh fnet hndk heid");
					}
				}); 
				// Gửi thư
				try {
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(em, "Male-Fashion"));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					message.setSubject(subject);
					message.setContent(emailContent, "text/html; charset=utf-8");
					Transport.send(message);
					System.out.println("Message sent successfully");
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
				mySession.setAttribute("otp", otpvalue);
				mySession.setAttribute("email", em);
				request.setAttribute("page", "enter_otp");
				RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/forgot_password");
				return;
			}
		}
	}

	private void handleLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		UserDAO udao = new UserDAO();
		Usermodel umodel = null;
		if (un.contains("@")) {
			// Nếu thông tin đăng nhập chứa ký tự '@', coi đó là email
			umodel = udao.findByEmail(un);
		} else {
			// Nếu không có ký tự '@', coi đó là tên người dùng
			umodel = udao.findByUserName(un);
		}
		String mes_sc = "message_success";
		String mes = "message";

		if (umodel != null) {
			if (umodel.getStatus().equals("1") || umodel.getStatus().equals("2")) {
				String hashedEnteredPassword = PasswordHashing.hashPassword(pw);
				if (hashedEnteredPassword.equals(umodel.getPassword())) {
					HttpSession session = request.getSession();
					session.setAttribute("USER", umodel);
					udao.updateStatus(umodel.getId(), "1");

					int role = umodel.getRole();
					if (role == 1 || role == 2) {
//						forwardToPage(request, response, "Đăng nhập thành công", "", "/views/admin/home.jsp", mes_sc);
						session.setAttribute(mes_sc, "Successfully Logged In");
						response.sendRedirect(request.getContextPath() + "/admin-home");
					} else {
//						forwardToPage(request, response, "Đăng nhập thành công", "", "/views/web/home.jsp", mes_sc);
						session.setAttribute(mes_sc, "Successfully Logged In");
						response.sendRedirect(request.getContextPath() + "/trang-chu");
					}
				} else {
					request.setAttribute("pwus", un);
					request.setAttribute("p_mes", "Please enter correct information!");
					forwardToPage(request, response, "Incorrect password!", "login", "/views/web/home.jsp", mes);
				}
			} else {
				forwardToPage(request, response, "Account has been locked.", "login", "/views/web/home.jsp", mes);
			}
		} else {
			request.setAttribute("p_mes", "Please enter correct information!");
			forwardToPage(request, response, "Incorrect username!", "login", "/views/web/home.jsp", mes);
		}
	}

	protected void forwardToPage(HttpServletRequest request, HttpServletResponse response, String message, String page,
			String pageToInclude, String messageAttributeName) throws ServletException, IOException {
		request.setAttribute(messageAttributeName, message);
		request.setAttribute("page", page);
		RequestDispatcher rd = request.getRequestDispatcher(pageToInclude);
		rd.forward(request, response);
	}

	private boolean isValid(String value, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	private void handleLogout(HttpServletRequest request, HttpServletResponse response, String ssion, String page)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Usermodel user = (Usermodel) session.getAttribute(ssion);
			if (user != null) {
				UserDAO udao = new UserDAO();
				udao.updateStatus(user.getId(), "2");
			}
			session.removeAttribute(ssion); // Hủy bỏ session
		}
		response.sendRedirect(page); // Đường dẫn đến trang đăng nhập
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("USER") != null) {
			response.sendRedirect(request.getContextPath() + "/trang-chu");
			return;
		} else if ("/login".equals(request.getServletPath())) {
			request.setAttribute("page", "login");
		} else if ("/register".equals(request.getServletPath())) {
			request.setAttribute("page", "register");
		} else if ("/forgot_password".equals(request.getServletPath())) {
			request.setAttribute("page", "forgot_password");
		} else {
			response.sendRedirect(request.getContextPath() + "/trang-chu");
			return;
		}
		String pageToInclude = "/views/web/home.jsp";
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
		String logout = request.getParameter("logout");
		if ("true".equals(logout)) {
			handleLogout(request, response, "USER", "./trang-chu");
			return;
		}
		if ("/login".equals(request.getServletPath())) {
			handleLogin(request, response);
		} else if ("/register".equals(request.getServletPath())) {
			handleRegistration(request, response);
		} else if ("/forgot_password".equals(request.getServletPath())) {
			handleForgotPassword(request, response);
		} else if ("/ValidateOtp".equals(request.getServletPath())) {
			handleValidateOtp(request, response);
		} else if ("/newPassword".equals(request.getServletPath())) {
			handleNewPassword(request, response);
		}
	}
}
