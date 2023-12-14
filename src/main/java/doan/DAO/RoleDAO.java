package doan.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import doan.model.RoleModel;


public class RoleDAO {
	public static void main(String[] args) {
//		findAll();

	}
	public List<RoleModel> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		List<RoleModel> roleList = new ArrayList<RoleModel>();
		try {
			// MÃ THAO TÁC
			// Câu lệnh truy vấn JPQL
			String jpql = "SELECT o FROM RoleModel o";

			// Tạo đối tượng truy vấn
			TypedQuery<RoleModel> query = em.createQuery(jpql, RoleModel.class);
			// Truy vấn
			roleList = query.getResultList();
//	        for (RoleModel us : userList) {System.out.println(us.getCode());	}
		} catch (Exception e) {
//	        e.printStackTrace(); 
		} finally {
			em.close();
			emf.close();
		}
		// Trả về danh sách người dùng
		return roleList;
	}
}
