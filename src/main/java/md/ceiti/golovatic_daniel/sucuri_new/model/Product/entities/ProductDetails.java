package md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_details", schema = "public")
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double volume;

    @Column(name = "is_natural", nullable = false)
    private Boolean isNatural;

    @Column(name = "for_kids", nullable = false)
    private Boolean forKids;

    @Column(name = "sold_daily", nullable = false)
    private int soldDaily;

    private byte[] image;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private NutritionProductInfo nutritionInfo;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private AdditionalProductInfo additionalInfo;
}
