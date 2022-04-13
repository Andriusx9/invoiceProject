package invoiceProject.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // meaning auto-incremented
    private Integer customerId;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String email;
    private String country;
    private Long personalCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer") //cascade = CascadeType.ALL,
    private List<Orders> orders;
}
