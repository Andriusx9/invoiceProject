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

    public static void orderManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Order Management");
        System.out.println("==================");

        int userSelectedOption;
        do {
            System.out.println();
            System.out.println("Select an option from the list:");

            menuProvideOptionsForOrderManagement();

            userSelectedOption = scanner.nextInt();

            executeUserSelectionForOrderManagement(userSelectedOption);

        } while (userSelectedOption != 0);
    }

    private static void menuProvideOptionsForOrderManagement() {
        System.out.println("1. Create Order");
        System.out.println("2. Delete order");
        System.out.println("3. Print all orders");
        System.out.println("4. Print orders than more by selected amount");
        System.out.println("5. Find order by id");
        System.out.println("0. Exit/Stop program");
    }

    public static void executeUserSelectionForOrderManagement(int userSelectedOption) {
        if(userSelectedOption == 1) {

            List<Customer> customers = CustomerRepository.findAll();
            List<Product> products = ProductRepository.findAll();
            if(customers.size() != 0 & products.size() != 0) {
                OrderService.createOrder();
            } else {
                System.out.println("Customer or product list is empty!");
                showMenuSelections();
            }

        } else if(userSelectedOption == 2) {

            List<Orders> orders = OrderRepository.findAll();
            if(orders.size() != 0) {
                OrderService.deleteOrder();
            } else {
                System.out.println("Order list is empty! Here nothing to delete.");
                showMenuSelections();
            }

        } else if(userSelectedOption == 3) {
            PrintingService.printAllOrders();
        } else if(userSelectedOption == 4) {

            System.out.println("Write Amount: ");
            Scanner scanner = new Scanner(System.in);
            int amount = scanner.nextInt();
            scanner.nextLine();

            PrintingService.printAllOrdersByAmount(amount);
        } else if(userSelectedOption == 5) {

            System.out.println("Write id: ");
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            scanner.nextLine();
            PrintingService.printOrder(OrderService.findOrder(id));
        }
    }



    public static void productManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Product Management");
        System.out.println("==================");

        int userSelectedOption;
        do {
            System.out.println();
            System.out.println("Select an option from the list:");

            menuProvideOptionsForProductManagement();

            userSelectedOption = scanner.nextInt();

            executeUserSelectionForProductManagement(userSelectedOption);

        } while (userSelectedOption != 0);
    }

    private static void menuProvideOptions() {
        System.out.println("1. Product management");
        System.out.println("2. Customer management");
        System.out.println("3. Order management");
        System.out.println("4. Create PDF file for selected order");
        System.out.println("0. Exit/Stop program");
    }

    private static void menuProvideOptionsForProductManagement() {
        System.out.println("1. Add product ");
        System.out.println("2. Delete product");
        System.out.println("3. Find products names starting by given letter");
        System.out.println("4. Print all products");
        System.out.println("5. Find most expensive product");
        System.out.println("6. Find lowest price product");
        System.out.println("7. Print products sorted by prices");
        System.out.println("8. Find product that weighs the most");
        System.out.println("9. Find product that weighs the lowest");
        System.out.println("10. Find product with biggest quantity");
        System.out.println("11. Find product with lowest quantity");
        System.out.println("12. Load Products from JSON");
        System.out.println("0. Exit/Stop program");
    }

    public static void customerManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Customer Management");
        System.out.println("==================");

        int userSelectedOption;
        do {
            System.out.println();
            System.out.println("Select an option from the list:");

            menuProvideOptionsForCustomerManagement();

            userSelectedOption = scanner.nextInt();

            executeUserSelectionForCustomerManagement(userSelectedOption);

        } while (userSelectedOption != 0);
    }

    private static void menuProvideOptionsForCustomerManagement() {
        System.out.println("1. Add customer ");
        System.out.println("2. Load Customers from JSON");
        System.out.println("3. Update customer full name");
        System.out.println("4. Delete customer");
        System.out.println("5. Make customers names to upper case");
        System.out.println("6. Print all customers");
        System.out.println("7. Find customers names starting by given letter");
        System.out.println("8. Print customers sorted names");
        System.out.println("9. Find customers by selected country");
        System.out.println("0. Exit/Stop program");
    }

    public static void executeUserSelectionForCustomerManagement(int userSelectedOption) {
        if(userSelectedOption == 1) {
            CustomerService.addCustomer();
        } else if(userSelectedOption == 2) {
            try {
                LoadCustomersController.loadCustomersFromJSON();
            } catch (SQLException | IOException exception) {
                exception.printStackTrace();
            }
        } else if(userSelectedOption == 3) {

            List<Customer> customers = CustomerRepository.findAll();

            if(customers.size() != 0) {
                CustomerService.updateCustomerFullName();
            } else {
                System.out.println("Customer list is empty! Here nothing to update.");
                showMenuSelections();
            }

        } else if(userSelectedOption == 4) {

            List<Customer> customers = CustomerRepository.findAll();

            if(customers.size() != 0) {
                CustomerService.deleteCustomer();
            } else {
                System.out.println("Customer list is empty! Here nothing to delete.");
                showMenuSelections();
            }

        } else  if(userSelectedOption == 5) {
            System.out.println(CustomerService.nameToUpperCase());
        } else if(userSelectedOption == 6) {
            PrintingService.printAllCustomers();
        } else if(userSelectedOption == 7) {

            System.out.println("Write letter: ");
            Scanner scanner = new Scanner(System.in);
            String letter = scanner.nextLine();

            PrintingService.printCustomers(CustomerService.getCustomersNamesStartByGivenLetter(letter));
        } else if(userSelectedOption == 8) {
            System.out.println(CustomerService.sortCustomerNames());
        } else if(userSelectedOption == 9) {

            System.out.println("Write county name: ");
            Scanner scanner = new Scanner(System.in);
            String country = scanner.nextLine();

            PrintingService.printCustomers(CustomerService.getCustomersByCountry(country));
        }
    }

    public static void executeUserSelectionForProductManagement(int userSelectedOption) {
        if (userSelectedOption == 1) {
            ProductService.addProduct();
        } else if(userSelectedOption == 2) {

            List<Product> products = ProductRepository.findAll();
            if(products.size() != 0) {
                ProductService.deleteProduct();
            } else {
                System.out.println("Product list is empty! Here nothing to delete.");
                showMenuSelections();
            }

        } else if(userSelectedOption == 3) {

            System.out.println("Write letter: ");
            Scanner scanner = new Scanner(System.in);
            String letter = scanner.nextLine();

            PrintingService.printProducts(ProductService.getProductsNamesStartByGivenLetter(letter));
        } else if(userSelectedOption == 4) {
            PrintingService.printAllProducts();
        } else if(userSelectedOption == 5) {
            PrintingService.printProducts(ProductService.mostExpensiveProduct());
        } else if(userSelectedOption == 6) {
            PrintingService.printProducts(ProductService.lowestPriceProduct());
        } else if(userSelectedOption == 7) {
            PrintingService.printProducts(ProductService.sortByPrices());
        } else if(userSelectedOption == 8) {
            PrintingService.printProduct(ProductService.getProductThatWeighsTheMost());
        } else if(userSelectedOption == 9) {
            PrintingService.printProduct(ProductService.getProductThatWeighsTheLowest());
        } else if(userSelectedOption == 10) {
            PrintingService.printProduct(ProductService.biggestQuantity());
        } else if(userSelectedOption == 11) {
            PrintingService.printProduct(ProductService.lowestQuantity());
        } else if(userSelectedOption == 12) {
            try {
                LoadProductsController.LoadProductsFromJson();
            } catch (SQLException | IOException exception) {
                exception.printStackTrace();
            }
        }

    }

    public static void executeUserSelection(int userSelectedOption) {
        if (userSelectedOption == 1) {
            productManagementMenu();
        } else if (userSelectedOption == 2) {
            customerManagementMenu();
        } else if (userSelectedOption == 3) {
            orderManagementMenu();
        } else if(userSelectedOption == 4) {

            PrintingService.printAllOrders();

            System.out.println("Write order id to create PDF file: ");
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            scanner.nextLine();

            CreatePDF.createPDF(id);
        }
    }

}
