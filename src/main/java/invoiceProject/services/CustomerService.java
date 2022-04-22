package invoiceProject.services;

import invoiceProject.model.Customer;
import invoiceProject.model.Product;
import invoiceProject.repository.CustomerRepository;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CustomerService {

    public static void addCustomer() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Customer full name: ");
        String fullName = scanner.nextLine();

        while (String.valueOf(fullName).length() == 0) {
            System.out.println("Full name can't be empty!");
            System.out.println("Customer full name: ");
            fullName = scanner.nextLine();

        }

        System.out.println("Customer address: ");
        String address = scanner.nextLine();

        while (String.valueOf(address).length() == 0) {
            System.out.println("Customer address can't be empty!");
            System.out.println("Customer address:: ");
            address = scanner.nextLine();

        }

        System.out.println("Customer phone number: ");
        String phoneNumber = scanner.nextLine();

        while (String.valueOf(phoneNumber).length() == 0) {
            System.out.println("Customer phone number can't be empty!");
            System.out.println("Customer phone number: ");
            phoneNumber = scanner.nextLine();
        }

        System.out.println("Customer email: ");
        String email = scanner.nextLine();

        while (String.valueOf(email).length() == 0) {
            System.out.println("Customer email can't be empty!");
            System.out.println("Customer email: ");
            email = scanner.nextLine();
        }

        System.out.println("Customer country: ");
        String country = scanner.nextLine();

        while (String.valueOf(country).length() == 0) {
            System.out.println("Customer country can't be empty!");
            System.out.println("Customer country: ");
            country = scanner.nextLine();
        }

        System.out.println("Customer personal code: ");
        Long personalCode = scanner.nextLong();

        do {
            System.out.println("Personal code must be from 11 digits!");
            System.out.println("Customer personal code: ");
            personalCode = scanner.nextLong();

        } while (String.valueOf(personalCode).length() != 11);

        System.out.println(fullName + address + phoneNumber + email + country + personalCode);

        Customer customer = Customer.builder()
                .fullName(fullName)
                .address(address)
                .phoneNumber(phoneNumber)
                .email(email)
                .country(country)
                .personalCode(personalCode)
                .build();

        CustomerRepository.persist(customer);

    }

    public static void deleteCustomer() {
        Scanner scanner = new Scanner(System.in);
        List<Customer> allCustomers = CustomerRepository.findAll();
        System.out.println("Choose customer: ");
        for (Customer customer : allCustomers) {
            System.out.println(customer.getCustomerId() + ". " + customer.getFullName());
        }
        int chosenId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Choose customer to delete: ");

        CustomerRepository.deleteById(chosenId);
    }

    public static void updateCustomerFullName() {
        Scanner scanner = new Scanner(System.in);
        List<Customer> allCustomers = CustomerRepository.findAll();
        System.out.println("Choose customer: ");
        for (Customer customer : allCustomers) {
            System.out.println(customer.getCustomerId() + ". " + customer.getFullName());
        }
        int chosenId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Write new customer full name: ");

        String newFullName = scanner.nextLine();

        CustomerRepository.updateFullName(CustomerRepository.findById(chosenId), newFullName);
    }

    public static List<String> nameToUpperCase() {
        List<Customer> customers = CustomerRepository.findAll();

        return customers.stream()
                .map(customer -> customer.getFullName().toUpperCase())
                .collect(Collectors.toList());
    }

    public static List<Customer> getCustomersNamesStartByGivenLetter(String letter){
        List<Customer> customers = CustomerRepository.findAll();
        return customers.stream()
                .filter(customer -> customer.getFullName().startsWith(letter))
                .collect(Collectors.toList());
    }

    public static List<String> sortCustomerNames() {
        List<Customer> customers = CustomerRepository.findAll();
        return customers.stream()
                .map(customer -> customer.getFullName())
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Customer> getCustomersByCountry(String country) {
        List<Customer> customers = CustomerRepository.findAll();
        return customers.stream()
                .filter(customer -> customer.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }



}
