package invoiceProject.services;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import invoiceProject.model.Orders;
import invoiceProject.model.Product;
import invoiceProject.repository.OrderRepository;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

public class CreatePDF {

    public static void createPDF(Integer orderId) {
        String patch = "src/main/resources/invoice.pdf";

        PdfWriter pdfWriter = null;
        try {
            pdfWriter = new PdfWriter(patch);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        float column = 280f;
        float[] columnWidth = {column, column};
        Table table = new Table(columnWidth);

        table.setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE);
        table.addCell(new Cell().add("Saskaitos faktura")
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setFontSize(30f)
                .setBorder(Border.NO_BORDER)
        );
        table.addCell(new Cell().add("UAB Pasaku salis\nLT-52158 Pasvalys\n+37068127600")
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setBorder(Border.NO_BORDER)
                .setMarginRight(20f)

        );


        Orders orderInformation = OrderRepository.findById(orderId);
        String customerFullName = orderInformation.getCustomer().getFullName();
        String customerAddress = orderInformation.getCustomer().getAddress();
        String customerPhoneNumber = orderInformation.getCustomer().getPhoneNumber();
        Long customerPersonalCode = orderInformation.getCustomer().getPersonalCode();
        LocalDate orderDate = orderInformation.getOrderDate();
        Double orderAmount = orderInformation.getAmount();
        orderAmount = Math.floor((orderAmount * 100) / 100);

        float[] secondColumnWidth = {80, 300, 100, 80};
        Table customerInfoTable = new Table(secondColumnWidth);

        customerInfoTable.addCell(new Cell(0, 4)
                .add("Pirkejo informacija")
                .setBorder(Border.NO_BORDER)
                .setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
        );

        customerInfoTable.addCell(new Cell().add("Pavadinimas:").setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER));
        customerInfoTable.addCell(new Cell().add(customerFullName).setBorder(Border.NO_BORDER)
                 //.setTextAlignment(TextAlignment.CENTER)
        );

        customerInfoTable.addCell(new Cell().add("Order nr:").setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER));
        customerInfoTable.addCell(new Cell().add(String.valueOf(orderId)).setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER));

        customerInfoTable.addCell(new Cell().add("Adresas:").setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER));
        customerInfoTable.addCell(new Cell().add(customerAddress).setBorder(Border.NO_BORDER)
                //.setTextAlignment(TextAlignment.CENTER)
                );

        customerInfoTable.addCell(new Cell().add("Pirkejo kodas:").setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER));
        customerInfoTable.addCell(new Cell().add(String.valueOf(customerPersonalCode)).setBorder(Border.NO_BORDER)
                  .setTextAlignment(TextAlignment.CENTER)
        );

        customerInfoTable.addCell(new Cell().add("Tel. Nr.:").setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER));
        customerInfoTable.addCell(new Cell().add(customerPhoneNumber).setBorder(Border.NO_BORDER)
                //.setTextAlignment(TextAlignment.CENTER)
                );

        customerInfoTable.addCell(new Cell().add("Data:").setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER));
        customerInfoTable.addCell(new Cell().add(String.valueOf(orderDate)).setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER));



        float[] productsInfoColumnWidth = {40, 270, 70, 90, 90};
        Table productInfoTable = new Table(productsInfoColumnWidth);

        productInfoTable.addCell(new Cell()
                .add("Nr.")
                .setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
                .setTextAlignment(TextAlignment.CENTER));
        productInfoTable.addCell(new Cell().add("Pavadinimas.")
                .setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
                .setTextAlignment(TextAlignment.CENTER));
        productInfoTable.addCell(new Cell().add("Mato vnt.")
                .setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
                .setTextAlignment(TextAlignment.CENTER));
        productInfoTable.addCell(new Cell().add("Kiekis")
                .setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
                .setTextAlignment(TextAlignment.CENTER));
        productInfoTable.addCell(new Cell().add("kaina")
                .setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
                .setTextAlignment(TextAlignment.CENTER));

        List<Product> orderProducts = orderInformation.getProducts();

        for (int i = 0; i < orderProducts.size(); i++) {
            int rowNumber = i + 1;
            String productName = orderProducts.get(i).getName();
            Integer productQuantity = orderProducts.get(i).getQuantity();
            Double productUnitPrice = orderProducts.get(i).getUnitPrice();

            productInfoTable.addCell(new Cell().add(String.valueOf(rowNumber))
                    .setTextAlignment(TextAlignment.CENTER));
            productInfoTable.addCell(new Cell().add(productName)
                    .setTextAlignment(TextAlignment.CENTER));
            productInfoTable.addCell(new Cell().add("vnt")
                    .setTextAlignment(TextAlignment.CENTER));
            productInfoTable.addCell(new Cell().add(String.valueOf(productQuantity))
                    .setTextAlignment(TextAlignment.CENTER));
            productInfoTable.addCell(new Cell().add(String.valueOf(productUnitPrice))
                    .setTextAlignment(TextAlignment.CENTER));

        }

        Double pvmFromOrderAmount = orderAmount * 0.21;
        pvmFromOrderAmount = Math.floor(pvmFromOrderAmount * 100) / 100;

        Double amountWithPVM = orderAmount + pvmFromOrderAmount;
        amountWithPVM = Math.floor(amountWithPVM * 100) / 100;

        productInfoTable.addCell(new Cell().add("")
                .setBorder(Border.NO_BORDER));
        productInfoTable.addCell(new Cell().add("")
                .setBorder(Border.NO_BORDER));
        productInfoTable.addCell(new Cell().add("")
                .setBorder(Border.NO_BORDER));
        productInfoTable.addCell(new Cell().add("Suma be PVM"));
        productInfoTable.addCell(new Cell().add(String.valueOf(orderAmount))
                .setTextAlignment(TextAlignment.CENTER));

        productInfoTable.addCell(new Cell().add("")
                .setBorder(Border.NO_BORDER));
        productInfoTable.addCell(new Cell().add("")
                .setBorder(Border.NO_BORDER));
        productInfoTable.addCell(new Cell().add("")
                .setBorder(Border.NO_BORDER));
        productInfoTable.addCell(new Cell().add("PVM"));
        productInfoTable.addCell(new Cell().add(String.valueOf(pvmFromOrderAmount))
                .setTextAlignment(TextAlignment.CENTER));

        productInfoTable.addCell(new Cell().add("")
                .setBorder(Border.NO_BORDER));
        productInfoTable.addCell(new Cell().add("")
                .setBorder(Border.NO_BORDER));
        productInfoTable.addCell(new Cell().add("")
                .setBorder(Border.NO_BORDER));
        productInfoTable.addCell(new Cell().add("Viso"));
        productInfoTable.addCell(new Cell().add(String.valueOf(amountWithPVM))
                .setTextAlignment(TextAlignment.CENTER));



        document.add(table);
        document.add(new Paragraph("\n")); // add blank line
        document.add(customerInfoTable);
        document.add(new Paragraph("\n")); // add blank line
        document.add(productInfoTable);
        document.close();
        System.out.println("Pdf was created");
    }

    public static String amountInWords(Integer number) {

        if( number < 0 & String.valueOf(number).length() > 9){
            return "Number to big, only to billion";
        }

        if(number == 0) { return "nulis"; }

        String[] units = {"", "vienas", "du", "trys", "keturi", "penki", "šeši", "septyni",
                "aštuoni", "devyni"};

        String[] niolikai = { "", "vienuolika", "dvylika", "trylika", "keturiolika",
                "penkiolika", "šešiolika", "septyniolika", "aštuoniolika", "devyniolika"};

        String[] tens = {"", "dešimt", "dvidešimt", "trisdešimt", "keturiasdešimt", "penkiasdešimt",
                "šešiasdešimt", "septyniasdešimt", "aštuoniasdešimt", "devyniasdešimt" };

        String[][] name = {{"milijonas", "milijonai", "milijonu"},{"tukstantis", "tukstanciai", "tukstanciu"}};

        String stringNumber = String.format("%09d", number);

        String[] skaicius = stringNumber.split("(?<=\\G...)");

        String zodziais;

        System.out.println(skaicius[1].substring(1));

        for (int i = 0; i < skaicius.length; i++) {
           // System.out.println("1.*" + skaicius[i] + "*");
            System.out.println(skaicius[i].charAt(0));
            int linksnis = 0;

            int firstNumberAtTrio = Character.getNumericValue(skaicius[i].charAt(0));
            // pridedam simtu pavadinima, jei pirmas tripleto skaitmuo > 0
            if(firstNumberAtTrio > 0 ){
                zodziais = units[firstNumberAtTrio];
                if(firstNumberAtTrio > 1) {
                    zodziais += "šimtai";
                } else {
                    zodziais += "šimtas";
                }
            }

            // du paskutiniai tripleto skaiciai
            int du = Integer.parseInt(skaicius[i].substring(1));
            // pacekinam nioliktus skaicius

            if(du > 10 && du < 20) {

            }

        }


        return stringNumber;

    }
}
