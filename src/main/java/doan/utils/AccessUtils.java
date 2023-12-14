package doan.utils;

import java.io.IOException;

import doan.model.Usermodel;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AccessUtils extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static boolean checkUserRole(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        // Kiểm tra xem session có tồn tại và USER có giá trị không
        if (session == null || session.getAttribute("USER") == null) {
            // Nếu chưa đăng nhập, chuyển hướng đến trang login
            redirectToLoginPage(request, response);
            return false;
        }

        Usermodel userRole = (Usermodel) session.getAttribute("USER");

        // Kiểm tra nếu role không nằm trong danh sách quyền cho phép, chuyển hướng đi
        if (userRole == null || (userRole.getRole() != 1 && userRole.getRole() != 2)) {
            redirectToHomePage(request, response);
            return false;
        }

        return true;
    }

    private static void redirectToLoginPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (IOException e) {
            // Xử lý ngoại lệ nếu cần thiết
            e.printStackTrace();
        }
    }

    private static void redirectToHomePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + "/trang-chu");
        } catch (IOException e) {
            // Xử lý ngoại lệ nếu cần thiết
            e.printStackTrace();
        }
    }
}

