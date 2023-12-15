package doan.DAO;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import doan.model.OrderModel;

public class OrderDAO {
	public static void main(String[] args) {
//		getTotalOrderAmount();
	}
	@SuppressWarnings("null")
	public int addOrder(OrderModel order) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(order);
            em.getTransaction().commit();
            return order.getOrderID();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return (Integer) null;
        } finally {
            em.close();
            emf.close();
        }
	}
	public int getTotalOrderAmount() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
	    EntityManager em = emf.createEntityManager();

	    try {
	        String jpql = "SELECT SUM(o.TotalAmount) FROM OrderModel o";
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



}
