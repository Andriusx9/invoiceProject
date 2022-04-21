package invoiceProject.repository;

import invoiceProject.HibernateUtil;
import invoiceProject.model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerRepository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    /**
     * Vienas is CRUD metodu
     * C - Create (save)
     * R - Read (select)
     * U - Update
     * D - Delete
     */
    public static void persist(Customer customer) {
        Transaction transaction = session.beginTransaction();
        //session.merge(customer);
        session.persist(customer);

        transaction.commit();
        System.out.println("Customer was saved: " + customer);
    }

    public static List<Customer> findAll() {

        return session.createQuery("from Customer", Customer.class).list();
    }

    public static Customer findById(Integer customerId) {
        return session.find(Customer.class, customerId);
    }

    public static Customer findByFullName(String customerFullName) {
        return session.find(Customer.class, customerFullName);
    }

    public static void delete(Customer customer) {
        Transaction transaction = session.beginTransaction();
        session.delete(customer);
        transaction.commit();
        System.out.println("Customer was deleted: " + customer);
    }

    public static void deleteById(int customerId) {
        Transaction transaction = session.beginTransaction();
        session.delete(findById(customerId));
        transaction.commit();
        System.out.println("Customer was deleted: " + findById(customerId)); // nebemato kai istrina
    }

    public static void updateFullName(Customer customer, String newFullName) {
        Transaction transaction = session.beginTransaction();
        customer.setFullName(newFullName);
        transaction.commit();
        System.out.println("Customer full name was changed to: " + newFullName);
    }


}
