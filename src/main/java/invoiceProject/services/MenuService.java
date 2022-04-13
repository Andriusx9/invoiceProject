package invoiceProject.services;

import invoiceProject.HibernateUtil;
import invoiceProject.controllers.LoadCustomersController;
import invoiceProject.controllers.LoadProductsController;
import invoiceProject.model.Customer;
import invoiceProject.model.Orders;
import invoiceProject.model.Product;
import invoiceProject.repository.CustomerRepository;
import invoiceProject.repository.ProductRepository;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        System.out.println("0. Exit/Stop program");
    }

    public static void executeUserSelection(int userSelectedOption) {
        if (userSelectedOption == 1) {
            addCustomer();
        } else if (userSelectedOption == 2) {
            addProduct();
        } else if (userSelectedOption == 3) {
            createOrder();
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
        }
    }

    public static void addCustomer() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Customer full name: ");
        String fullName = scanner.nextLine();

        System.out.println("Customer address: ");
        String address = scanner.nextLine();

        System.out.println("Customer phoneNumber: ");
        String phoneNumber = scanner.nextLine();

        System.out.println("Customer email: ");
        String email = scanner.nextLine();

        System.out.println("Customer country: ");
        String country = scanner.nextLine();

        System.out.println(fullName + address + phoneNumber + email + country);

        Customer customer = new Customer();

        customer.setFullName(fullName);
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(email);
        customer.setCountry(country);

        CustomerRepository.persist(customer);

    }

    public static void addProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Product name: ");
        String name = scanner.nextLine();

        System.out.println("Product category: ");
        String category = scanner.nextLine();

        System.out.println("Product unitPrice: ");
        Double unitPrice = scanner.nextDouble();

        System.out.println("Product weight: ");
        Double weight = scanner.nextDouble();

        System.out.println("Product quantity: ");
        int quantity = scanner.nextInt();

        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setUnitPrice(unitPrice);
        product.setWeight(weight);
        product.setQuantity(quantity);

        ProductRepository.persist(product);
    }

    public static void createOrder() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose customer: ");

        List<Customer> allCustomers = CustomerRepository.findAll();

        for (int i = 0; i < allCustomers.size(); i++) {
            System.out.println("Id: " + allCustomers.get(i).getCustomerId() + ". " + allCustomers.get(i).getFullName());
        }

        int chosenCustomer = scanner.nextInt();

        System.out.println("Choose products: ");

        List<Product> allProducts = ProductRepository.findAll();

        for (int i = 0; i < allProducts.size(); i++) {
            System.out.println("Id: " + allProducts.get(i).getProductId() + ". " + allProducts.get(i).getName() + "; Price: " +
                    allProducts.get(i).getUnitPrice());
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
        for (int i = 0; i < chosenProductsList.size(); i++) {
            orderProducts.add(ProductRepository.findById(chosenProductsList.get(i)));
        }

        Double orderAmount = 0.00;

        for (Product orderProduct : orderProducts) {
            orderAmount += orderProduct.getUnitPrice() * orderProduct.getQuantity();
        }

        Orders order = Orders.builder()
                .customer(allCustomers.get((chosenCustomer - 1)))
                .orderDate(LocalDate.of(2022, 03, 12))
                .amount(orderAmount)
                .products(orderProducts)
                .build();


        for (int i = 0; i < chosenProductsList.size(); i++) {
            ProductRepository.findById(chosenProductsList.get(i)).setOrders(List.of(order));
        }

        allCustomers.get((chosenCustomer - 1)).setOrders(List.of(order));

        session.beginTransaction();
        session.save(allCustomers.get((chosenCustomer - 1)));
        session.getTransaction().commit();

    }
}
