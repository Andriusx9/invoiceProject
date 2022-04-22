package invoiceProject.services;

import invoiceProject.HibernateUtil;
import invoiceProject.model.Customer;
import invoiceProject.model.Orders;
import invoiceProject.model.Product;
import invoiceProject.repository.CustomerRepository;
import invoiceProject.repository.OrderRepository;
import invoiceProject.repository.ProductRepository;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderService {

    public static void createOrder() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose customer: ");

        List<Customer> allCustomers = CustomerRepository.findAll();

        for (Customer allCustomer : allCustomers) {
            System.out.println("Id: " + allCustomer.getCustomerId() + ". " + allCustomer.getFullName());
        }

        int chosenCustomerId = scanner.nextInt();
        scanner.nextLine();

        while(chosenCustomerId == 0){
            System.out.println("Customer id can't be empty!");
            System.out.println("Choose customer: ");
            chosenCustomerId = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.println("Choose products: ");

        List<Product> allProducts = ProductRepository.findAll();

        for (Product allProduct : allProducts) {
            System.out.println("Id: " + allProduct.getProductId() + ". " + allProduct.getName() + "; Price: " +
                    allProduct.getUnitPrice());
        }

        int chosenProduct;

        List<Integer> chosenProductsList = new ArrayList<>();

        do {
            chosenProduct = scanner.nextInt();
            if (chosenProduct != 0) {
                chosenProductsList.add(chosenProduct);
                System.out.println("0. Create order");
            }

        } while (chosenProduct != 0);

        List<Product> orderProducts = new ArrayList<>();
        for (Integer integer : chosenProductsList) {
            orderProducts.add(ProductRepository.findById(integer));
        }

        double orderAmount = 0.00;

        for (Product orderProduct : orderProducts) {
            orderAmount += orderProduct.getUnitPrice() * orderProduct.getQuantity();
        }

        String format = String.format("%.2f", orderAmount);

        format = format.replace(",", ".");

        orderAmount = Double.parseDouble(format);

        Customer chosenCustomer = CustomerRepository.findById(chosenCustomerId);

        Orders order = Orders.builder()
                .customer(chosenCustomer)
                .orderDate(LocalDate.now())
                .amount(orderAmount)
                .products(orderProducts)
                .build();

        for (Integer newChosenProduct : chosenProductsList) {
            ProductRepository.findById(newChosenProduct).setOrders(List.of(order));
        }

        chosenCustomer.setOrders(List.of(order));
        session.beginTransaction();
        session.update(chosenCustomer);
        session.getTransaction().commit();

        session.close();
        CreatePDF.createPDF(order.getOrderId());

    }

    public static void deleteOrder() {
        Scanner scanner = new Scanner(System.in);
        List<Orders> allOrders = OrderRepository.findAll();
        System.out.println("Choose order: ");

        for (Orders order : allOrders) {
            System.out.println(order.getOrderId() + ". " + order.getCustomer().getFullName() + " " + order.getOrderDate());
        }

        int chosenId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Choose order to delete: ");

        OrderRepository.deleteById(chosenId);
    }

    public static Orders findOrder(int orderId) {

        return OrderRepository.findById(orderId);
    }
}
