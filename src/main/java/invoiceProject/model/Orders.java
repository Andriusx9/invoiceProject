package invoiceProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Double amount;
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "orders_products",
            joinColumns = { @JoinColumn(name = "orderId") },
            inverseJoinColumns = { @JoinColumn(name = "productId") }
    )
    private List<Product> products;
}
