package invoiceProject;

import invoiceProject.services.CustomerService;
import invoiceProject.services.MenuService;
import invoiceProject.services.PrintingService;
import invoiceProject.services.ProductService;

public class Main {

    public static void main(String[] args) {

        MenuService.showMenuSelections();
       // System.out.println(CustomerService.getCustomersNamesStartByGivenLetter("R")); // reikejo istrinti is Customer toString Order
        //System.out.println(CustomerService.sortCustomerNames());
        //System.out.println(CustomerService.getCustomersByCountry("Lietuva"));

        //System.out.println(ProductService.getProductsNamesStartByGivenLetter("S"));
        //System.out.println(ProductService.sortByPrices());
        //System.out.println(ProductService.totalCost());

    }
}
