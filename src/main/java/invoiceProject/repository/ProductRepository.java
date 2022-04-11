package invoiceProject.repository;

import invoiceProject.HibernateUtil;
import invoiceProject.model.Customer;
import invoiceProject.model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductRepository {
    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public static void persist(Product product) {
        Transaction transaction = session.beginTransaction();
        session.persist(product);
        transaction.commit();
        System.out.println("Product was saved" + product);
    }

    public static List<Product> findAll() {
        List<Product> products = session.createQuery("from Product", Product.class).list();
        return products;
    }

    public static Product findById(Integer productId) {
        return session.find(Product.class, productId);
    }

    public static void delete(Product product) {
        Transaction transaction = session.beginTransaction();
        session.delete(product);
        transaction.commit();
        System.out.println("Product was deleted: " + product);
    }

    public static void deleteById(Integer productId) {
        Transaction transaction = session.beginTransaction();
        session.delete(findById(productId));
        transaction.commit();
        System.out.println("Product was deleted: " + findById(productId));
    }

}
