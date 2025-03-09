package md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "nutrition_product_info", schema = "public")
public class NutritionProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id", nullable = false, unique = true)
    private ProductDetails product;

    @Column(name = "procent_fructe", nullable = false)
    private Double procentFructe;

    @Column(name = "procent_adaos_zahar", nullable = false)
    private Double procentAdaosZahar;

    @Column(nullable = false)
    private Double calorii;

    @Column(nullable = false)
    private Double carbohidrati;

    @Column(nullable = false)
    private Double proteine;

    @Column(nullable = false)
    private Double grasimi;
}
