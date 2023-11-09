package doan.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import doan.model.Usermodel;


public class UserDAO {
	public static void main(String[] args) {
//	    UserDAO dao = new UserDAO();
//	    user newUser = new user();
//	    newUser.setId("your_id_here");
//	    newUser.setFullname("your_fullname_here");
//	    newUser.setPassword("123");
//	    newUser.setEmail("your_email_here");
//	    dao.create(newUser);
		
//		 update(newUser);
//		 delete();
//		findAll();
//		findById(1);
//		findByUserName("trongtruong");
	}
	
	// Sửa kiểu trả về của phương thức findAll
	public List<Usermodel> findAll() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
	    // Tạo EntityManager để bắt đầu làm việc với CSDL
	    EntityManager em = emf.createEntityManager();
	    List<Usermodel> userList = new ArrayList<Usermodel>();
	    try {
	        // MÃ THAO TÁC
	        // Câu lệnh truy vấn JPQL
	        String jpql = "SELECT o FROM Usermodel o";
	        // Tạo đối tượng truy vấn
	        TypedQuery<Usermodel> query = em.createQuery(jpql, Usermodel.class);
	        // Truy vấn
	        userList = query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    } finally {
	        em.close();
	        emf.close();
	    }
	    // Trả về danh sách người dùng
	    return userList;
	}
	public static Usermodel findById(int id) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
	    // Tạo EntityManager để bắt đầu làm việc với CSDL
	    EntityManager em = emf.createEntityManager();
	    Usermodel foundUser = null;

	    try {
	        em.getTransaction().begin(); // Bắt đầu Transaction
	        // MÃ THAO TÁC
	        String jpql = "SELECT o FROM Usermodel o WHERE o.id = :id";
	        // Tạo đối tượng truy vấn
	        TypedQuery<Usermodel> query = em.createQuery(jpql, Usermodel.class);
	        query.setParameter("id", id); // Đặt tham số trong truy vấn
	        foundUser = query.getSingleResult(); // Lấy kết quả của truy vấn
	        em.getTransaction().commit(); // Chấp nhận kết quả thao tác
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    } finally {
	        em.close();
	        emf.close();
	    }
	    return foundUser;
	}
	public Usermodel findByUserName(String user) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
	    // Tạo EntityManager để bắt đầu làm việc với CSDL
	    EntityManager em = emf.createEntityManager();
	    Usermodel foundUser = null;

	    try {
	        em.getTransaction().begin(); // Bắt đầu Transaction
	        // MÃ THAO TÁC
	        String jpql = "SELECT o FROM Usermodel o WHERE o.Username = :user";
	        // Tạo đối tượng truy vấn
	        TypedQuery<Usermodel> query = em.createQuery(jpql, Usermodel.class);
	        query.setParameter("user", user); // Đặt tham số trong truy vấn
	        foundUser = query.getSingleResult(); // Lấy kết quả của truy vấn
	        em.getTransaction().commit(); // Chấp nhận kết quả thao tác
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    } finally {
	        em.close();
	        emf.close();
	    }
	    return foundUser;
	}

}
