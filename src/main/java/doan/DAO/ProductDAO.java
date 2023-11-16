package doan.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import doan.model.CategoryModel;
import doan.model.ProductModel;
import doan.model.SupplierModel;

public class ProductDAO {
	public List<ProductModel> getAllProduct() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> productList = new ArrayList<ProductModel>();
		try {
			String jpql = "SELECT o FROM ProductModel o";
			TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
			productList = query.getResultList();
			/*
			 * for(ProductModel us :productList) { System.out.println( us.getProductName());
			 * }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return productList;
	}

	public List<ProductModel> getAllProductByCategory(int cid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> productList = new ArrayList<ProductModel>();
		try {
			String jpql = "SELECT o FROM ProductModel o Where o.category_id = :cid ";
			TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
			query.setParameter("cid", cid);

			productList = query.getResultList();

			/*
			 * for(ProductModel us :productList) { System.out.println( us.getProductID());
			 * System.out.println( us.getProductName()); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return productList;
	}

	public static List<ProductModel> getAllProductBySupplier(int supid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> supList = new ArrayList<ProductModel>();
		try {
			String jpql = "SELECT o FROM ProductModel o Where o.SupplierID = :supid ";
			TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
			query.setParameter("supid", supid);

			supList = query.getResultList();

			/*
			 * for (SupplierModel us : supList) { System.out.println(us.getSupplierID());
			 * System.out.println(us.getProductsSupplied()); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return supList;
	}

	/*
	 * public static void main(String[] args) { getAllProductBySupplier(1); }
	 */
	public List<ProductModel> getAllProductByPrice(int minPrice, int maxPrice) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> priceList = new ArrayList<ProductModel>();
		try {
			String jpql = "SELECT o FROM ProductModel o Where o.Price > :minPrice AND o.Price <= :maxPrice";
			TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
			query.setParameter("minPrice", minPrice);
			query.setParameter("maxPrice", maxPrice);
			priceList = query.getResultList();

			for (ProductModel us : priceList) {
				System.out.println(us.getProductID());
				System.out.println(us.getPrice());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return priceList;
	}

	public List<ProductModel> getAllProductByPrice(int priceT) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> priceList = new ArrayList<ProductModel>();
		try {
			String jpql = "SELECT o FROM ProductModel o Where o.Price > :priceT";
			TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
			query.setParameter("priceT", priceT);
			priceList = query.getResultList();

			for (ProductModel us : priceList) {
				System.out.println(us.getProductID());
				System.out.println(us.getPrice());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return priceList;
	}

	public static List<ProductModel> getAllProductByColor(String coid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> colorList = new ArrayList<ProductModel>();
		try {
			String jpql = "SELECT o FROM ProductModel o Where o.Color = :coid";
			TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
			query.setParameter("coid", coid);
			colorList = query.getResultList();

			for (ProductModel us : colorList) {
				System.out.println(us.getProductID());
				System.out.println(us.getColor());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return colorList;
	}

	public static void main(String[] args) {
		getAllProductByColor("black");
	}

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

	public List<SupplierModel> getAllSupplier() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<SupplierModel> supList = new ArrayList<SupplierModel>();
		try {
			String jpql = "SELECT o FROM SupplierModel o";
			TypedQuery<SupplierModel> query = em.createQuery(jpql, SupplierModel.class);
			supList = query.getResultList();

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

		return supList;
	}
	public List<ProductModel> findByName(String productName) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
	    EntityManager em = emf.createEntityManager();
	    List<ProductModel> productList = new ArrayList<>();
	    String jpql = "SELECT p FROM ProductModel p WHERE p.ProductName LIKE :ProductName";
	    TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);

	    try {
	    	query.setParameter("ProductName", "%" + productName + "%"); // Thêm % vào tham số
	        productList = query.getResultList();
//	        for(ProductModel us :productList) { System.out.println( us.getProductName());
//			  }
//	        em.getTransaction().commit();//chấp nhận kết quả thao tác
	    } catch (Exception e) {
//	        e.printStackTrace();
//	        System.out.println("Không tìm thấy sản phẩm với tên: " + productName);
	    } finally {
	        em.close();
	        emf.close();
	    }
	    
	    return productList;
	}
}
