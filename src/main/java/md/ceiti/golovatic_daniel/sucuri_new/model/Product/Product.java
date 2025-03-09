package md.ceiti.golovatic_daniel.sucuri_new.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private ProductDetails productDetails = new ProductDetails();
    private AdditionalProductInfo additionalProductInfo = new AdditionalProductInfo();
    private NutritionProductInfo nutritionProductInfo = new NutritionProductInfo();

}
