package doan.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import doan.model.OrderDetailModel;
import doan.model.OrderModel;
import doan.model.Usermodel;

public class UserDAO {
	public static void main(String[] args) {
//		UserDAO dao = new UserDAO();
//		Usermodel newUser = new Usermodel();
//		newUser.setUsername("tin");
//		newUser.setPassword("123");
//		newUser.setEmail("tin@gmail.com");
//		newUser.setRole(2);
//		dao.create(newUser);

//		 update(newUser);
//		 delete();
//		findAll();
//		findById(1);
//		findByUserName("trongtruong");
//		updateStatus(4,"2");
//		isUsernameExists("tin");
//		newUser.setAddress("abch");
//		newUser.setFullname("hct");
//		dao.updateUserData(15, newUser);
//		isEmailExists("tranthuc@gmail.com");
//		findByEmail("tranthuc@gmail.com");

//		dao.updatePassword("trong123@gmail.com","Truonghct12@tg");
	}

	public Usermodel create(Usermodel newUser) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			if (!transaction.isActive()) {
				transaction.begin();
			}

			em.persist(newUser);
			transaction.commit();
			return newUser;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		} finally {
			em.close();
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

	public List<OrderModel> findOrdersByCustomerId(int customerId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		List<OrderModel> orderList = new ArrayList<>();

		try {
			// Câu lệnh truy vấn JPQL với điều kiện CustomerID
			String jpql = "SELECT o FROM OrderModel o WHERE o.CustomerID = :customerId";

			// Tạo đối tượng truy vấn
			TypedQuery<OrderModel> query = em.createQuery(jpql, OrderModel.class);
			query.setParameter("customerId", customerId); // Đặt giá trị cho tham số

			// Truy vấn
			orderList = query.getResultList();
		} catch (Exception e) {
			// Xử lý ngoại lệ nếu cần
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

		// Trả về danh sách đơn đặt hàng của khách hàng có CustomerID là 1
		return orderList;
	}
	public List<OrderDetailModel> findOrdersDetail(int orderID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		List<OrderDetailModel> orderList = new ArrayList<>();

		try {
			// Câu lệnh truy vấn JPQL với điều kiện CustomerID
			String jpql = "SELECT o FROM OrderDetailModel o WHERE o.OrderID = :orderID";

			// Tạo đối tượng truy vấn
			TypedQuery<OrderDetailModel> query = em.createQuery(jpql, OrderDetailModel.class);
			query.setParameter("orderID", orderID); // Đặt giá trị cho tham số

			// Truy vấn
			orderList = query.getResultList();
		for (OrderDetailModel product : orderList) {
		System.out.println("Username: " + product.getPaymentMethod() + ", Fullname: " + product.getProductID());
		// Add more fields as needed
	}
		} catch (Exception e) {
			// Xử lý ngoại lệ nếu cần
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

		// Trả về danh sách đơn đặt hàng của khách hàng có CustomerID là 1
		return orderList;
	}
	@SuppressWarnings("unchecked")
	public List<Usermodel> query(String searchQuery) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<Usermodel> userList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM User__tb WHERE (Username LIKE :SearchQuery OR Fullname LIKE :SearchQuery) ";
			Query nativeQuery = em.createNativeQuery(sql, Usermodel.class);
			nativeQuery.setParameter("SearchQuery", "%" + searchQuery + "%");
			userList = nativeQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

		return userList;
	}
//	@SuppressWarnings("unchecked")
//	public List<Usermodel> query(String searchQuery, int limit, int pages) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
//		EntityManager em = emf.createEntityManager();
//		List<Usermodel> userList = new ArrayList<>();
//
//		try {
//			int offset = (pages - 1) * limit;
//
//			// Native SQL query
//			String sql = "SELECT * FROM User__tb WHERE (Username LIKE :SearchQuery OR Fullname LIKE :SearchQuery) "
//					+ "AND Role NOT IN (1, 2) " + "ORDER BY Role OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY";
//
//			Query nativeQuery = em.createNativeQuery(sql, Usermodel.class);
//			nativeQuery.setParameter("SearchQuery", "%" + searchQuery + "%");
//			nativeQuery.setParameter("limit", limit);
//			nativeQuery.setParameter("offset", offset);
//			userList = nativeQuery.getResultList();
//
////			for (Usermodel product : userList) {
////				System.out.println("Username: " + product.getUsername() + ", Fullname: " + product.getFullname());
////				// Add more fields as needed
////			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			em.close();
//			emf.close();
//		}
//
//		return userList;
//	}

	public long count(String searchQuery) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();

		try {
			// Native SQL query
			String sql = "SELECT COUNT(*) FROM User__tb WHERE (Username LIKE :SearchQuery OR Fullname LIKE :SearchQuery) "
					+ "AND Role NOT IN (1, 2)";

			Query countQuery = em.createNativeQuery(sql);
			countQuery.setParameter("SearchQuery", "%" + searchQuery + "%");

			return ((Number) countQuery.getSingleResult()).longValue();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

		return 0;
	}

	public Usermodel findById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		Usermodel user = null;

		try {
			user = em.find(Usermodel.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return user;
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

	public Usermodel findByEmail(String email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		Usermodel foundUser = null;

		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			// MÃ THAO TÁC
			String jpql = "SELECT o FROM Usermodel o WHERE o.Email = :email";
			// Tạo đối tượng truy vấn
			TypedQuery<Usermodel> query = em.createQuery(jpql, Usermodel.class);
			query.setParameter("email", email); // Đặt tham số trong truy vấn
			foundUser = query.getSingleResult(); // Lấy kết quả của truy vấn
			System.out.println(foundUser.getUsername());
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
			TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM Usermodel u WHERE u.Username = :username",
					Long.class);
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

	public boolean isEmailExists(String email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();

		try {
			// Sử dụng JPQL để kiểm tra xem email có tồn tại không
			String jpql = "SELECT COUNT(u) FROM Usermodel u WHERE u.Email = :email";
			Long count = em.createQuery(jpql, Long.class).setParameter("email", email).getSingleResult();
//			boolean emailExists = count > 0; // Trả về true nếu có ít nhất một email tồn tại
//			System.out.println("Email " + email + " tồn tại trong cơ sở dữ liệu: " + emailExists);
			return count > 0; // Trả về true nếu có ít nhất một email tồn tại
		} catch (Exception e) {
			// Xử lý ngoại lệ
			return false;
		} finally {
			em.close();
			emf.close();
		}
	}

	public void updateUserData(int userId, Usermodel usermodel) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			Usermodel user = em.find(Usermodel.class, userId);

			// Kiểm tra và cập nhật các trường chỉ khi giá trị mới khác giá trị cũ và không
			// rỗng
			if (usermodel.getAddress() != null && !usermodel.getAddress().isEmpty()
					&& !Objects.equals(usermodel.getAddress(), user.getAddress())) {
				user.setAddress(usermodel.getAddress());
			}

			if (usermodel.getEmail() != null && !usermodel.getEmail().isEmpty()
					&& !Objects.equals(usermodel.getEmail(), user.getEmail())) {
				user.setEmail(usermodel.getEmail());
			}

			if (usermodel.getFullname() != null && !usermodel.getFullname().isEmpty()
					&& !Objects.equals(usermodel.getFullname(), user.getFullname())) {
				user.setFullname(usermodel.getFullname());
			}

			if (usermodel.getImg() != null && !usermodel.getImg().isEmpty()
					&& !Objects.equals(usermodel.getImg(), user.getImg())) {
				user.setImg(usermodel.getImg());
			}

			if (usermodel.getPhone() != 0 && usermodel.getPhone() != user.getPhone()) {
				user.setPhone(usermodel.getPhone());
			}

			if (usermodel.getUsername() != null && !usermodel.getUsername().isEmpty()
					&& !Objects.equals(usermodel.getUsername(), user.getUsername())) {
				user.setUsername(usermodel.getUsername());
			}
			if (usermodel.getPassword() != null && !usermodel.getPassword().isEmpty()
					&& !Objects.equals(usermodel.getPassword(), user.getPassword())) {
				user.setPassword(usermodel.getPassword());
			}
			em.merge(user);

			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void updatePassword(String email, String newPassword) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			String jpql = "SELECT o FROM Usermodel o WHERE o.Email = :email";
			TypedQuery<Usermodel> query = em.createQuery(jpql, Usermodel.class);
			query.setParameter("email", email);
			Usermodel user = query.getSingleResult();

			// Nếu người dùng tồn tại, cập nhật mật khẩu
			if (user != null) {
				user.setPassword(newPassword);
				em.merge(user);
				em.getTransaction().commit();
			} else {
				// Người dùng không tồn tại, xử lý theo yêu cầu của bạn
				System.out.println("Người dùng không tồn tại.");
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void deleteById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			Usermodel foundUser = em.find(Usermodel.class, id); // Tìm kiếm đối tượng cần xóa

			if (foundUser != null) {
				em.remove(foundUser); // Xóa đối tượng
				em.getTransaction().commit(); // Chấp nhận kết quả thao tác
			}
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback(); // Rollback nếu có lỗi
		} finally {
			em.close();
			emf.close();
		}
	}

}
