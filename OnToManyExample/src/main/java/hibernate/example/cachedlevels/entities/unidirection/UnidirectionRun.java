package hibernate.example.cachedlevels.entities.unidirection;

import javax.persistence.JoinColumn;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UnidirectionRun {

	public static void main(String[] args) {
		/*
		 * Sử dụng code:
		 * tại file Category: Bước 1 thử COMMENT/KHÔNG COMMENT code @JoinColumn(name = "product_id")
		 * Thực hiện
		 *  Create 1 category & 2 product
		 * = checklog nhé các bạn
		 * 
		 * Note: để đỡ rối code, thì các bạn comment line 31-32 ở file Hibernate.cfg.xml nhé
		 */
		
		SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

    	Category cate1 = new Category("Cate 1");
    	cate1.getProducts().add(new Product("product 1"));
    	cate1.getProducts().add(new Product("product 2"));
    	
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
        	
            transaction = session.beginTransaction();
            // save the objects
            session.save(cate1);
            // commit transaction
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
		
	}

}
