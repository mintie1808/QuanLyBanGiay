package doan.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import doan.model.OrderModel;

public class OrderDAO {
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
}
