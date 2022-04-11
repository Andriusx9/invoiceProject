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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String email;
    private String country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Orders> orders;
}
