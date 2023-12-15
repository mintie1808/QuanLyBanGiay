package doan.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import doan.model.CategoryModel;
import doan.model.ProductModel;
public class ProductDAO {
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
//		findByName("áo", 5, 1);
//		count("áo");
//		getLatestHotProducts(6);
//		getsoldPrduct();
	}
	public List<ProductModel> getAllProduct() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> productList = new ArrayList<ProductModel>();
		try {
			String jpql = "SELECT o FROM ProductModel o";
			TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
			productList = query.getResultList();

//			for (ProductModel us : productList) {
//				System.out.println(us.getProductName());
//			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return productList;
	}
	public int getsoldPrduct() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
	    EntityManager em = emf.createEntityManager();

	    try {
	        String jpql = "SELECT SUM(o.Sold) FROM ProductModel o";
	        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
	        Long totalAmount = query.getSingleResult();
	        System.out.println(totalAmount);
	        return totalAmount != null ? totalAmount.intValue() : 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    } finally {
	        em.close();
	        emf.close();
	    }
	}
	public List<String> getAllColor() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<String> colorList = new ArrayList<>();
		try {
			String jpql = "SELECT  DISTINCT o.Color FROM ProductModel o";
			TypedQuery<String> query = em.createQuery(jpql, String.class);
			colorList = query.getResultList();

//			for (String us : colorList) {
//				System.out.println(us);
//			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return colorList;
	}


	public List<ProductModel> getProductById(int proid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> productList = new ArrayList<ProductModel>();
		try {
			String jpql = "SELECT o FROM ProductModel o Where o.ProductID = :proid ";
			TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
			query.setParameter("proid", proid);

			productList = query.getResultList();

//			for (ProductModel us : productList) {
//				System.out.println(us.getProductID());
//				System.out.println(us.getProductName());
//			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return productList;
	}

	@SuppressWarnings("unchecked")
	public List<ProductModel> getAllProductByCategory(int cid,int limit,int pages) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> productList = new ArrayList<ProductModel>();
		String jpql = "SELECT * FROM Product__tb WHERE category_id LIKE :cid "
				+ "ORDER BY ProductID OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY";
		try {
			int offset = (pages - 1) * limit;
			Query nativeQuery = em.createNativeQuery(jpql, ProductModel.class);
			nativeQuery.setParameter("cid", cid); // Thêm % vào tham số
	        nativeQuery.setParameter("limit", limit);
	        nativeQuery.setParameter("offset", offset);
	        productList = nativeQuery.getResultList();
//	         for (ProductModel product : productList) {
//	             System.out.println("Username: " + product.getProductID() + ", Fullname: " + product.getColor());
//	         }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return productList;
	}
	public long countCategory_id(int product) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();

		try {
			// Native SQL query
			String sql = "SELECT COUNT(*) FROM Product__tb WHERE category_id LIKE :Product ";

			Query countQuery = em.createNativeQuery(sql);
			countQuery.setParameter("Product", "%" + product + "%");
			long result = ((Number) countQuery.getSingleResult()).longValue();
			System.out.println("Count of products with name like : " + result);
			return ((Number) countQuery.getSingleResult()).longValue();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

		return 0;
	}
	public List<ProductModel> getAllProductBySupplier(int supid) {
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

	public List<ProductModel> getAllProductByPrice(int minPrice, int maxPrice) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> priceList = new ArrayList<ProductModel>();
		try {
			String jpql = "SELECT o FROM ProductModel o Where o.Price >= :minPrice AND o.Price <= :maxPrice";
			TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
			query.setParameter("minPrice", minPrice);
			query.setParameter("maxPrice", maxPrice);
			priceList = query.getResultList();

//			for (ProductModel us : priceList) {
//				System.out.println(us.getProductID());
//				System.out.println(us.getPrice());
//			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return priceList;
	}

	public List<ProductModel> getAllProductByPriceMin(int priceT) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> priceList = new ArrayList<ProductModel>();
		try {
			String jpql = "SELECT o FROM ProductModel o Where o.Price >= :priceT";
			TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
			query.setParameter("priceT", priceT);
			priceList = query.getResultList();

//			for (ProductModel us : priceList) {
//				System.out.println(us.getProductID());
//				System.out.println(us.getPrice());
//			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return priceList;
	}

	public List<ProductModel> getAllProductByPriceMax(int priceT) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> priceList = new ArrayList<ProductModel>();
		try {
			String jpql = "SELECT o FROM ProductModel o Where o.Price <=  :priceT";
			TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
			query.setParameter("priceT", priceT);
			priceList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return priceList;
	}

	public List<ProductModel> getAllProductByPriceLowToHigh() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> priceList = new ArrayList<ProductModel>();
		try {
			String jpql = "SELECT o FROM ProductModel o ORDER BY o.Price ASC";
			TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
			priceList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return priceList;
	}

	public List<ProductModel> getAllProductByPriceHighToLow() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> priceList = new ArrayList<ProductModel>();
		try {
			String jpql = "SELECT o FROM ProductModel o ORDER BY o.Price DESC";
			TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
			priceList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return priceList;
	}

	public List<ProductModel> getAllProductByColor(String coid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> colorList = new ArrayList<ProductModel>();
		try {
			String jpql = "SELECT o FROM ProductModel o Where o.Color = :coid";
			TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
			query.setParameter("coid", coid);
			colorList = query.getResultList();
//
//			for (ProductModel us : colorList) {
//				System.out.println(us.getProductID());
//				System.out.println(us.getColor());
//			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return colorList;
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

	@SuppressWarnings("unchecked")
	public List<ProductModel> findByName(String productName,int limit,int pages) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> productList = new ArrayList<>();
		String jpql = "SELECT * FROM Product__tb WHERE (ProductName LIKE :ProductName OR Seotitle LIKE :ProductName)"
				+ "ORDER BY ProductID OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY";
		try {
			int offset = (pages - 1) * limit;
			Query nativeQuery = em.createNativeQuery(jpql, ProductModel.class);
			nativeQuery.setParameter("ProductName", "%" + productName + "%"); // Thêm % vào tham số
	        nativeQuery.setParameter("limit", limit);
	        nativeQuery.setParameter("offset", offset);
	        productList = nativeQuery.getResultList();
//	         for (ProductModel product : productList) {
//	             System.out.println("Username: " + product.getProductID() + ", Fullname: " + product.getColor());
//	         }
		} catch (Exception e) {
	        e.printStackTrace();
//	        System.out.println("Không tìm thấy sản phẩm với tên: " + productName);
		} finally {
			em.close();
			emf.close();
		}
		return productList;
	}
	public long count(String productName) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();

		try {
			// Native SQL query
			String sql = "SELECT COUNT(*) FROM Product__tb WHERE ProductName LIKE :ProductName ";

			Query countQuery = em.createNativeQuery(sql);
			countQuery.setParameter("ProductName", "%" + productName + "%");
//			long result = ((Number) countQuery.getSingleResult()).longValue();
//			System.out.println("Count of products with name like '" + productName + "': " + result);
			return ((Number) countQuery.getSingleResult()).longValue();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

		return 0;
	}
	public void addProduct(ProductModel newProduct) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();

			entityManager.persist(newProduct); // Thêm sản phẩm vào cơ sở dữ liệu

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				// Hủy thao tác khi có ngoại lệ
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	public ProductModel findById(int productID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		ProductModel product = null;

		try {
			product = em.find(ProductModel.class, productID);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

		return product;
	}

	public void deleteProductById(int productId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager entityManager = emf.createEntityManager();

		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();

			ProductModel product = entityManager.find(ProductModel.class, productId);

			if (product != null) {
				entityManager.remove(product);
				// Không cần gọi entityManager.flush() ở đây (đẩy các thay đổi vào csdl)
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}

	}

	public void updateProduct(int product, ProductModel Product) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();

			ProductModel existingProduct = entityManager.find(ProductModel.class, product);

			if (existingProduct != null) {
				// Cập nhật thông tin sản phẩm từ updatedProduct vào existingProduct
				existingProduct.setProductName(Product.getProductName());
				existingProduct.setPrice(Product.getPrice());
				existingProduct.setSeotitle(Product.getSeotitle());
				existingProduct.setColor(Product.getColor());
				existingProduct.setDescription(Product.getDescription());
				existingProduct.setAmount(Product.getAmount());
				existingProduct.setCategory_id(Product.getCategory_id());
				existingProduct.setSupplierID(Product.getSupplierID());
				existingProduct.setImg(Product.getImg());

				// Kích hoạt cơ chế tự động cập nhật của Hibernate
				entityManager.merge(existingProduct);
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				// Hủy thao tác khi có ngoại lệ
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	public void deleteById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			ProductModel foundProduct = em.find(ProductModel.class, id); // Tìm kiếm đối tượng cần xóa

			if (foundProduct != null) {
				em.remove(foundProduct); // Xóa đối tượng
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

	public ProductModel updateProductAmountAndSold(int productId, int newAmount, int newSold, boolean newStatus) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

			// Kiểm tra xem sản phẩm đã tồn tại trong cơ sở dữ liệu chưa
			ProductModel existingProduct = em.find(ProductModel.class, productId);

			if (existingProduct != null) {
				existingProduct.setAmount(newAmount);
				existingProduct.setSold(newSold);
				existingProduct.setStatus(newStatus);
				// Cập nhật sản phẩm trong cơ sở dữ liệu
				em.merge(existingProduct);

				transaction.commit();
				return existingProduct;
			} else {
				return null;
			}
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		} finally {
			em.close();
			emf.close();
		}
	}

	public List<ProductModel> getRelatedProductByCategory(int cid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
		EntityManager em = emf.createEntityManager();
		List<ProductModel> productList = new ArrayList<>();
		try {
			String jpql = "SELECT o FROM ProductModel o WHERE o.category_id = :cid";
			TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
			query.setParameter("cid", cid);
			query.setMaxResults(4); // Giới hạn số lượng kết quả trả về là 4
			productList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return productList;
	}
	public List<ProductModel> getLatestHotProducts(int limit) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
	    EntityManager em = emf.createEntityManager();
		List<ProductModel> productList = new ArrayList<>();
	    try {
	        String jpql = "SELECT p FROM ProductModel p WHERE p.Status = true ORDER BY p.Hot DESC";
	        TypedQuery<ProductModel> query = em.createQuery(jpql, ProductModel.class);
	        query.setMaxResults(limit);
	        productList = query.getResultList();
//	        for (ProductModel product : productList) {
//	            System.out.println("ProductID: " + product.getProductID() +
//	                               ", ProductName: " + product.getProductName() +
//	                               ", Price: " + product.getPrice() +
//	                               ", Hot: " + product.isHot());
//	            // Add more fields as needed
//	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle exceptions if needed
	    } finally {
	        em.close();
	        emf.close();
	    }
	    return productList;
	}
}
