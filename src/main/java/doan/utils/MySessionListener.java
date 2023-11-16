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
        // Lấy người dùng từ session và cập nhật trạng thái
        Usermodel user = (Usermodel) se.getSession().getAttribute("USER");
        if (user != null && (user.getStatus().equals("1") || user.getStatus().equals("2"))) {
            UserDAO udao = new UserDAO();
            udao.updateStatus(user.getId(), "2");
        }
    }
}
