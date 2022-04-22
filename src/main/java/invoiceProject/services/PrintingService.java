package invoiceProject.services;

import invoiceProject.model.Customer;
import invoiceProject.model.Orders;
import invoiceProject.model.Product;
import invoiceProject.repository.CustomerRepository;
import invoiceProject.repository.OrderRepository;
import invoiceProject.repository.ProductRepository;

import java.util.List;

public class PrintingService {

    public static void printAllCustomers() {
        List<Customer> customers = CustomerRepository.findAll();
        System.out.println("=================================================================================");
        System.out.println("= ID ==   Full name ==  Personal code   == Address ==  Country ==  Phone Number =");
        System.out.println("=================================================================================");
        for (Customer customer : customers) {

            System.out.println(customer.getCustomerId() + ". " + customer.getFullName() + ".  " + customer.getPersonalCode() + ". " + customer.getAddress() + ". " +
                    customer.getCountry() + ". " + customer.getPhoneNumber() + ". ");
        }
    }

    public static void printCustomers(List<Customer> customers) {
        System.out.println("=================================================================================");
        System.out.println("= ID ==   Full name ==  Personal code   == Address ==  Country ==  Phone Number =");
        System.out.println("=================================================================================");
        for (Customer customer : customers) {

            System.out.println(customer.getCustomerId() + ". " + customer.getFullName() + ".  " + customer.getPersonalCode() + ". " + customer.getAddress() + ". " +
                    customer.getCountry() + ". " + customer.getPhoneNumber() + ". ");
        }
    }

    public static void printAllProducts() {
        List<Product> products = ProductRepository.findAll();
        System.out.println("=================================================================================");
        System.out.println("= ID ==     Name      ==    Category   ==    Weight ==  Quantity ==  Unit Price =");
        System.out.println("=================================================================================");
        for (Product product : products) {

            System.out.println(product.getProductId() + ". " + product.getName() + ". " + product.getCategory() + ". " + product.getWeight() + ". " +
                    product.getQuantity() + ". " + product.getUnitPrice());

        }
    }

    public static void printProducts(List<Product> products) {
        System.out.println("=================================================================================");
        System.out.println("= ID ==     Name      ==    Category   ==    Weight ==  Quantity ==  Unit Price =");
        System.out.println("=================================================================================");
        for (Product product : products) {

            System.out.println(product.getProductId() + " = " + product.getName() + " = " + product.getCategory() + " = " + product.getWeight() + " = " +
                    product.getQuantity() + " = " + product.getUnitPrice());

        }
    }

    public static void printProduct(Product product) {
        System.out.println("=================================================================================");
        System.out.println("= ID ==     Name      ==    Category   ==    Weight ==  Quantity ==  Unit Price =");
        System.out.println("=================================================================================");

            System.out.println(product.getProductId() + " = " + product.getName() + " = " + product.getCategory() + " = " + product.getWeight() + " = " +
                    product.getQuantity() + " = " + product.getUnitPrice());

    }

    public static void printAllOrders() {
        List<Orders> orders = OrderRepository.findAll();
        System.out.println("==========================================================");
        System.out.println("= ID ==     Order customer      ==    Date   ==   Amount= ");
        System.out.println("==========================================================");
        for (Orders order : orders) {

            System.out.println(order.getOrderId() + ". " + order.getCustomer().getFullName()  + ". " +
                    order.getOrderDate()  + ". " + order.getAmount());

        }
    }

    public static void printOrder(Orders order) {
        List<Orders> orders = OrderRepository.findAll();
        System.out.println("==========================================================");
        System.out.println("= ID ==     Order customer      ==    Date   ==   Amount= ");
        System.out.println("==========================================================");

            System.out.println(order.getOrderId() + ". " + order.getCustomer().getFullName()  + ". " +
                    order.getOrderDate()  + ". " + order.getAmount());


    }

    public static void printAllOrdersByAmount(int amount) {
        List<Orders> orders = OrderRepository.findAll();
        System.out.println("==========================================================");
        System.out.println("= ID ==     Order customer      ==    Date   ==   Amount= ");
        System.out.println("==========================================================");
        for (Orders order : orders) {

            if(order.getAmount() > (double) amount) {
                System.out.println(order.getOrderId() + ". " + order.getCustomer().getFullName()  + ". " +
                        order.getOrderDate()  + ". " + order.getAmount());
            }

        }
    }


}
