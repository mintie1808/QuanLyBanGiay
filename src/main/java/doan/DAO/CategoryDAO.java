package doan.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import doan.model.CategoryModel;

public class CategoryDAO {

	public List<CategoryModel> getAllCategory() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<CategoryModel> categoryList = new ArrayList<CategoryModel>();
		try {
			String jpql = "SELECT o FROM CategoryModel o";
			TypedQuery<CategoryModel> query = em.createQuery(jpql, CategoryModel.class);
			categoryList = query.getResultList();

			/*
			 * for(CategoryModel us :categoryList) { System.out.println( us.getSeotitle());
			 * }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return categoryList;
	}

	public Integer getCateIdByProductId(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		Integer categoryId = null;
		try {
			String jpql = "SELECT o.category_id FROM ProductModel o Where o.ProductID = :id";
			TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
			query.setParameter("id", id);
			categoryId = query.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return categoryId;
	}

	public String getNameCateById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		String categoryName = null;
		try {
			String jpql = "SELECT o.Seotitle FROM CategoryModel o Where o.category_id = :id";
			TypedQuery<String> query = em.createQuery(jpql, String.class);
			query.setParameter("id", id);
			categoryName = query.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return categoryName;
	}

}
