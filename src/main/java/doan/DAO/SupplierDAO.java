package doan.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import doan.model.ProductModel;
import doan.model.SupplierModel;


public class SupplierDAO {
	public SupplierModel findById(int SupplierID) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
	    EntityManager em = emf.createEntityManager();
	    SupplierModel supplier = null;

	    try {
	    	supplier = em.find(SupplierModel.class, SupplierID);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        em.close();
	        emf.close();
	    }

	    return supplier;
	}
	
	public  List<SupplierModel> getAllSupplier() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
	    EntityManager em = emf.createEntityManager();
	    List<SupplierModel> supplier = new ArrayList<SupplierModel>();

	    try {
	        String jpql = "SELECT s FROM SupplierModel s";
	        TypedQuery<SupplierModel> query = em.createQuery(jpql, SupplierModel.class);
	        supplier = query.getResultList();
//	        for(SupplierModel o :supplier) {
//	        	
//	        	System.out.println(o.getAddress());
//	        	System.out.println(o.getAddress());
//	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        em.close();
	        emf.close();
	    }

	    return supplier;
	}
	
//	public static void main(String[] args) {
//		getAllSupplier();
//	}
	public List<String> getAllSupplierNames() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
	    EntityManager em = emf.createEntityManager();
	    List<String> supplierNames = new ArrayList<>();

	    try {
	        String jpql = "SELECT s.supplierName FROM SupplierModel s";
	        TypedQuery<String> query = em.createQuery(jpql, String.class);
	        supplierNames = query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        em.close();
	        emf.close();
	    }

	    return supplierNames;
	}
	
	public void addSupplier(SupplierModel newSupplier) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction transaction = null;

	    try {
	        transaction = em.getTransaction();
	        transaction.begin();

	        em.persist(newSupplier); // Thêm nhà cung cấp vào cơ sở dữ liệu

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            // Hủy thao tác khi có ngoại lệ
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }
	}
	
	public void deleteSupplierById(int supplierId,int sp) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction transaction = null;

	    try {
	        transaction = em.getTransaction();
	        transaction.begin();

	        // Tìm nhà cung cấp theo ID
	        SupplierModel supplierToDelete = em.find(SupplierModel.class, supplierId);

	        if (sp != 0) {
	            // Tìm tất cả sản phẩm liên quan có supplierID tương ứng
	            List<ProductModel> productsToDelete = em
	                    .createQuery("SELECT p FROM ProductModel p WHERE p.supplierID = :supplierId", ProductModel.class)
	                    .setParameter("supplierId", supplierId)
	                    .getResultList();

	            // Xóa từng sản phẩm liên quan
	            for (ProductModel product : productsToDelete) {
	                em.remove(product);
	            }

	            // Xóa nhà cung cấp
	            em.remove(supplierToDelete);
	        }
	        else {
	        	em.remove(supplierToDelete);
	        }
	        
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            // Rollback giao dịch nếu có ngoại lệ
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }
	}

	public void updateSupplier(SupplierModel updatedSupplier) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
	    EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Tìm nhà cung cấp cần cập nhật trong cơ sở dữ liệu
            SupplierModel existingSupplier = entityManager.find(SupplierModel.class, updatedSupplier.getSupplierID());

            if (existingSupplier != null) {
                // Cập nhật thông tin nhà cung cấp
                existingSupplier.setSupplierName(updatedSupplier.getSupplierName());
                existingSupplier.setAddress(updatedSupplier.getAddress());
                existingSupplier.setProductsSupplied(updatedSupplier.getProductsSupplied());
                existingSupplier.setPaymentTerms(updatedSupplier.getPaymentTerms());

                // Merge để đồng bộ dữ liệu
                entityManager.merge(existingSupplier);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                // Rollback giao dịch nếu có ngoại lệ
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
			SupplierModel foundSupplier = em.find(SupplierModel.class, id); // Tìm kiếm đối tượng cần xóa

			if (foundSupplier != null) {
				em.remove(foundSupplier); // Xóa đối tượng
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
