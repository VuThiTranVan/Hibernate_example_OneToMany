package hibernate.example.cachedlevels.entities.bidirection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class BidirectionRun {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

    	Item item = new Item("Cate 1");
    	item.getOrderItems().add(new OrderItem(3));
    	item.getOrderItems().add(new OrderItem(5));
    	
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
        	
            transaction = session.beginTransaction();
            // save the objects
            session.save(item);
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
