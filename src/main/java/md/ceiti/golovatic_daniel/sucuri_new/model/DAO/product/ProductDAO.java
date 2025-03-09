package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.product;

import md.ceiti.golovatic_daniel.sucuri_new.model.Product.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

     List<Product> getProducts();

     boolean deleteProduct(int productId);

     void updateProduct(int id, String numeProdus, double pret, double volum, Boolean produs_natural,
                        Boolean pentru_copii, byte[] image, double procent_fructe,
                        double procent_adaos_zahar, double calorii, double carbohidrati, double proteine, double grasimi,
                        String additionalInformation) throws Exception;
}
