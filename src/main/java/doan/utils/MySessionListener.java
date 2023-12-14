package doan.utils;

import doan.DAO.UserDAO;
import doan.model.Usermodel;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class MySessionListener implements HttpSessionListener {
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	    // Lấy người dùng từ session
	    Usermodel user = (Usermodel) se.getSession().getAttribute("USER");

	    // Kiểm tra xem session có bị invalidate không
	    if (user != null) {
	        // Kiểm tra trạng thái người dùng
	        String userStatus = user.getStatus();
	        if ("1".equals(userStatus) || "2".equals(userStatus)) {
	            // Cập nhật trạng thái người dùng
	            UserDAO udao = new UserDAO();
	            udao.updateStatus(user.getId(), "2");
	        }
	        
	        // Xóa thông tin người dùng khỏi session
	        se.getSession().removeAttribute("USER");
	    }
	}

}
