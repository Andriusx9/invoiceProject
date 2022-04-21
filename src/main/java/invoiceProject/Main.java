package invoiceProject;

import invoiceProject.model.Customer;
import invoiceProject.repository.CustomerRepository;
import invoiceProject.services.CreatePDF;
import invoiceProject.services.MenuService;
import invoiceProject.services.NumbersToWords;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args)  {

        MenuService.showMenuSelections();

        //CustomerRepository.updateFullName(new Customer(null, " Lukaas Lukaitis", " adresas", " 65464654564", " email", " LT", 25256L), "Ponas Ponaitis");
        //CustomerRepository.updateFullName(CustomerRepository.findById(2), "Ponas Ponaitis");

    }
}
