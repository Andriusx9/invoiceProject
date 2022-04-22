package invoiceProject.services;

import invoiceProject.model.Product;
import invoiceProject.repository.ProductRepository;

import java.util.List;
import java.util.Scanner;

public class ProductService {

    public static void addProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Product name: ");
        String name = scanner.nextLine();

        while(String.valueOf(name).length() == 0){
            System.out.println("Product name can't be empty!");
            System.out.println("Product name: ");
            name = scanner.nextLine();
        }

        System.out.println("Product category: ");
        String category = scanner.nextLine();

        System.out.println("Product unitPrice: ");
        Double unitPrice = scanner.nextDouble();
        scanner.nextLine();

        while(String.valueOf(unitPrice).length() == 0){
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

        while(String.valueOf(quantity).length() == 0){
            System.out.println("Product unit price can't be empty!");
            System.out.println("Product unit price: ");
            quantity = scanner.nextInt();
            scanner.nextLine();
        }

        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setUnitPrice(unitPrice);
        product.setWeight(weight);
        product.setQuantity(quantity);

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
}
