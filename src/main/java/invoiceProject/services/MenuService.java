package invoiceProject.services;

import invoiceProject.controllers.LoadCustomersController;
import invoiceProject.controllers.LoadProductsController;
import invoiceProject.model.Customer;
import invoiceProject.model.Orders;
import invoiceProject.model.Product;
import invoiceProject.repository.CustomerRepository;
import invoiceProject.repository.OrderRepository;
import invoiceProject.repository.ProductRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuService {

    public static void showMenuSelections() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Invoices management application");
        System.out.println("==========================================");

        int userSelectedOption;

        do {
            System.out.println("Select an option from the list:");

            menuProvideOptions();

            userSelectedOption = scanner.nextInt();

            executeUserSelection(userSelectedOption);

        } while (userSelectedOption != 0);

    }

    private static void menuProvideOptions() {
        System.out.println("1. Add customer ");
        System.out.println("2. Add product");
        System.out.println("3. Create Order");
        System.out.println("4. Load Customers from JSON");
        System.out.println("5. Load Products from JSON");
        System.out.println("6. Update customer full name");
        System.out.println("7. Delete customer");
        System.out.println("8. Delete order");
        System.out.println("9. Delete product");
        System.out.println("0. Exit/Stop program");
    }

    public static void executeUserSelection(int userSelectedOption) {
        if (userSelectedOption == 1) {
            CustomerService.addCustomer();
        } else if (userSelectedOption == 2) {
            ProductService.addProduct();
        } else if (userSelectedOption == 3) {
            List<Customer> customers = CustomerRepository.findAll();
            List<Product> products = ProductRepository.findAll();
            if(customers.size() != 0 & products.size() != 0) {
                OrderService.createOrder();
            } else {
                System.out.println("Customer or product list is empty!");
                showMenuSelections();
            }

        } else if(userSelectedOption == 4) {
            try {
                LoadCustomersController.loadCustomersFromJSON();
            } catch (SQLException | IOException exception) {
                exception.printStackTrace();
            }
        } else if (userSelectedOption == 5) {
            try {
                LoadProductsController.LoadProductsFromJson();
            } catch (SQLException | IOException exception) {
                exception.printStackTrace();
            }
        } else if(userSelectedOption == 6) {

            List<Customer> customers = CustomerRepository.findAll();
            if(customers.size() != 0) {
                CustomerService.updateCustomerFullName();
            } else {
                System.out.println("Customer list is empty! Here nothing to update.");
                showMenuSelections();
            }
        } else if(userSelectedOption == 7) {

            List<Customer> customers = CustomerRepository.findAll();
            if(customers.size() != 0) {
                CustomerService.deleteCustomer();
            } else {
                System.out.println("Customer list is empty! Here nothing to delete.");
                showMenuSelections();
            }

        } else if(userSelectedOption == 8) {

            List<Orders> orders = OrderRepository.findAll();
            if(orders.size() != 0) {
                OrderService.deleteOrder();
            } else {
                System.out.println("Order list is empty! Here nothing to delete.");
                showMenuSelections();
            }

        } else if(userSelectedOption == 9 ) {

            List<Product> products = ProductRepository.findAll();
            if(products.size() != 0) {
                ProductService.deleteProduct();
            } else {
                System.out.println("Product list is empty! Here nothing to delete.");
                showMenuSelections();
            }

        }
    }

}
