package invoiceProject.services;

import invoiceProject.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @BeforeEach
    void setUp() {
        Product milk = Product.builder().productId(1).name("Milk").quantity(3).unitPrice(2.0).weight(1.0).build();
    }

    @Test
    void addProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getProductsNamesStartByGivenLetter() {
    }

    @Test
    void mostExpensiveProduct() {
    }

    @Test
    void lowestPriceProduct() {
    }

    @Test
    void sortByPrices() {
    }

    @Test
    void getProductThatWeighsTheMost() {
    }

    @Test
    void getProductThatWeighsTheLowest() {
    }

    @Test
    void biggestQuantity() {
    }

    @Test
    void lowestQuantity() {
    }

    @Test
    void totalCost() {
    }
}