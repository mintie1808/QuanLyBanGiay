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
//	    Usermodel newUser = new Usermodel();
//	    newUser.setUsername("tin");
//	    newUser.setPassword("123");
//	    newUser.setEmail("tin@gmail.com");
//	    newUser.setRole(2);
//	    dao.create(newUser);

//		 update(newUser);
//		 delete();
//		findAll();
//		findById(1);
//		findByUserName("trongtruong");
//		updateStatus(4,"2");
//		isUsernameExists("tin");
	}

	public Usermodel create(Usermodel newUser) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(newUser);
            em.getTransaction().commit();
            return newUser;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            emf.close();
        }
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
//	        e.printStackTrace(); 
		} finally {
			em.close();
			emf.close();
		}
		// Trả về danh sách người dùng
		return userList;
	}

	public Usermodel findById(int id) {
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
//	        e.printStackTrace(); 
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
//			System.out.println(foundUser.getUsername());
			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
		} catch (Exception e) {
//	        e.printStackTrace(); 
		} finally {
			em.close();
			emf.close();
		}
		return foundUser;
	}

	public Usermodel updateStatus(int userid, String newStatus) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		Usermodel updatedEntity = null;

		try {
			em.getTransaction().begin();
			Usermodel entity = em.find(Usermodel.class, userid);
			if (entity != null) {
				entity.setStatus(newStatus);
				em.merge(entity);
				em.getTransaction().commit();
			}

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Cập nhật thất bại!");
		} finally {
			em.close();
			emf.close();
		}
		return updatedEntity;
	}
    public boolean isUsernameExists(String username) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
        EntityManager em = emf.createEntityManager();

        try {
            // Sử dụng JPQL để kiểm tra sự tồn tại của tên người dùng trong cơ sở dữ liệu
        	TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM Usermodel u WHERE u.Username = :username", Long.class);
        	query.setParameter("username", username);

            Long count = query.getSingleResult();
//            System.out.println(count);
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
}
