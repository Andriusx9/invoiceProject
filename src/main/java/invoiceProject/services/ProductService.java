package invoiceProject.services;

import invoiceProject.model.Customer;
import invoiceProject.model.Product;
import invoiceProject.repository.CustomerRepository;
import invoiceProject.repository.ProductRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProductService {

    public static void addProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Product name: ");
        String name = scanner.nextLine();

        while (String.valueOf(name).length() == 0) {
            System.out.println("Product name can't be empty!");
            System.out.println("Product name: ");
            name = scanner.nextLine();
        }

        System.out.println("Product category: ");
        String category = scanner.nextLine();

        System.out.println("Product unitPrice: ");
        Double unitPrice = scanner.nextDouble();
        scanner.nextLine();

        while (String.valueOf(unitPrice).length() == 0) {
            System.out.println("Product unit price can't be empty!");
            System.out.println("Product unit price: ");
            unitPrice = scanner.nextDouble();
            scanner.nextLine();
        }

        System.out.println("Product weight: ");
        Double weight = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Product quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        while (String.valueOf(quantity).length() == 0) {
            System.out.println("Product unit price can't be empty!");
            System.out.println("Product unit price: ");
            quantity = scanner.nextInt();
            scanner.nextLine();
        }

        Product product = Product.builder()
                .name(name)
                .category(category)
                .unitPrice(unitPrice)
                .weight(weight)
                .quantity(quantity)
                .build();

        ProductRepository.persist(product);
    }

    public static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        List<Product> allProducts = ProductRepository.findAll();
        System.out.println("Choose product: ");

        for (Product product : allProducts) {
            System.out.println(product.getProductId() + ". " + product.getName());
        }

        int chosenId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Choose product to delete: ");

        ProductRepository.deleteById(chosenId);
    }

    public static List<Product> getProductsNamesStartByGivenLetter(String letter){
        List<Product> products = ProductRepository.findAll();
        return products.stream()
                .filter(product -> product.getName().startsWith(letter))
                .collect(Collectors.toList());
    }

    public static List<Product> mostExpensiveProduct() {
        List<Product> products = ProductRepository.findAll();
        return Collections.singletonList(products.stream()
                .max(Comparator.comparing(Product::getUnitPrice))
                .get());
    }

    public static List<Product> lowestPriceProduct() {
        List<Product> products = ProductRepository.findAll();
        return Collections.singletonList(products.stream()
                .min(Comparator.comparing(Product::getUnitPrice))
                .get());
    }

    public static List<Product> sortByPrices() {
        List<Product> products = ProductRepository.findAll();
        return products.stream()
                .sorted(Comparator.comparing(Product::getUnitPrice))
                .collect(Collectors.toList());
    }

    public static Product getProductThatWeighsTheMost() {
        List<Product> products = ProductRepository.findAll();
        return products.stream()
                .max(Comparator.comparing(Product::getWeight))
                .get();
    }

    public static Product getProductThatWeighsTheLowest() {
        List<Product> products = ProductRepository.findAll();
        return products.stream()
                .min(Comparator.comparing(Product::getWeight))
                .get();
    }

    public static Product biggestQuantity() {
        List<Product> products = ProductRepository.findAll();
        return products.stream()
                .max(Comparator.comparing(Product::getQuantity))
                .get();
    }

    public static Product lowestQuantity() {
        List<Product> products = ProductRepository.findAll();
        return products.stream()
                .min(Comparator.comparing(Product::getQuantity))
                .get();
    }

    public static List<Double> totalCost() {
        List<Product> products = ProductRepository.findAll();
        return products.stream()
                .map(product -> Double.parseDouble(String.format("%.2f", (product.getUnitPrice() * product.getQuantity())).replace(
                        ",", "."
                )))
                .collect(Collectors.toList());
    }


}
