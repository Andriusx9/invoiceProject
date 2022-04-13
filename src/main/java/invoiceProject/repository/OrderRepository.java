package invoiceProject.repository;

import invoiceProject.HibernateUtil;
import invoiceProject.model.Customer;
import invoiceProject.model.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderRepository {
    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public static void persist(Orders order) {
        Transaction transaction = session.beginTransaction();
        session.persist(order);
        transaction.commit();
        System.out.println("Order was saved: " + order);
    }

    public static List<Orders> findAll() {
        List<Orders> orders = session.createQuery("from Orders", Orders.class).list();
        return orders;
    }

    public static Orders findById(Integer orderId) {
        return session.find(Orders.class, orderId);
    }

    public void delete(Orders orders) {
        Transaction transaction = session.beginTransaction();
        session.delete(orders);
        transaction.commit();
        System.out.println("Order was deleted: " + orders);
    }

    public void deleteById(Integer orderId) {
        Transaction transaction = session.beginTransaction();
        session.delete(findById(orderId));
        transaction.commit();
        System.out.println("Order was deleted: " + findById(orderId));
    }
}
