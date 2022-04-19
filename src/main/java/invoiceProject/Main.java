package invoiceProject;

import invoiceProject.services.CreatePDF;
import invoiceProject.services.MenuService;
import invoiceProject.services.NumbersToWords;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args)  {

        //MenuService.showMenuSelections();

        //System.out.println( CreatePDF.amountInWords(123056789));

        System.out.println(NumbersToWords.numbersToWords(500040013));
        System.out.println(NumbersToWords.numbersToWords(100478017));

    }
}
