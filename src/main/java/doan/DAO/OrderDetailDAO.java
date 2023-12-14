package doan.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import doan.model.OrderDetailModel;

public class OrderDetailDAO {


    public OrderDetailModel addOrderDetail(OrderDetailModel oDetail) {
    	 EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sql_web_ban__quan_ao");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            // Sử dụng merge thay vì persist
            oDetail = em.merge(oDetail);

            em.getTransaction().commit();
            return oDetail;
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

 
}
