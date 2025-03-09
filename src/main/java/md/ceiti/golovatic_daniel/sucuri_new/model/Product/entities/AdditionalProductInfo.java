package md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "additional_product_info", schema = "public")
public class AdditionalProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "additional_info", nullable = false)
    private String additionalInfo;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private ProductDetails product;
}
