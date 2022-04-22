package invoiceProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String name;
    private String category;
    private Double unitPrice;
    private Double weight;
    private Integer quantity;

    @ManyToMany(mappedBy = "products")
    private List<Orders> orders;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", unitPrice=" + unitPrice +
                ", weight=" + weight +
                ", quantity=" + quantity +
                '}';
    }
}
