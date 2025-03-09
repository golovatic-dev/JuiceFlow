package md.ceiti.golovatic_daniel.sucuri_new.controller;

import md.ceiti.golovatic_daniel.sucuri_new.model.DAO.product.ProductDAO;
import md.ceiti.golovatic_daniel.sucuri_new.model.DAO.product.ProductDAOImpl;
import md.ceiti.golovatic_daniel.sucuri_new.model.Product.Product;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductsController {
    private ProductDAO productDAO;

    public ProductsController() {
        this.productDAO = new ProductDAOImpl();
    }

    public List<Product> getProducts() {
        return productDAO.getProducts();
    }
    public boolean deleteProduct(int productId) {
        return productDAO.deleteProduct(productId);
    }

    public void updateProduct(int id, String numeProdus, double pret, double volum, Boolean produs_natural,
                              Boolean pentru_copii, byte[] image, double procent_fructe,
                              double procent_adaos_zahar, double calorii, double carbohidrati, double proteine, double grasimi,
                              String additionalInformation) throws Exception {
        productDAO.updateProduct(id, numeProdus, pret, volum, produs_natural, pentru_copii, image, procent_fructe, procent_adaos_zahar, calorii, carbohidrati, proteine, grasimi, additionalInformation);
    }

    public static byte[] getDefaultImage() {
        try (InputStream is = new FileInputStream("src/main/resources/images/defaultProduct.png")) {
            return is.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }


}
