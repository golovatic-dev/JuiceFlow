
package md.ceiti.golovatic_daniel.sucuri_new.controller;

import md.ceiti.golovatic_daniel.sucuri_new.model.DAO.productInfo.ProductInfoDAO;
import md.ceiti.golovatic_daniel.sucuri_new.model.DAO.productInfo.ProductInfoDAOImpl;
import md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities.ProductDetails;

public class ProductInfoController {
    private ProductInfoDAO productInfoDAO;

    public ProductInfoController() {
        productInfoDAO = new ProductInfoDAOImpl();
    }

    public int insertProduct(String numeProdus, double pret, double volum, boolean produsNatural, boolean pentruCopii, byte[] image) {
        return productInfoDAO.insertProduct(numeProdus, pret, volum, produsNatural, pentruCopii, image);
    }

    public boolean updateProduct(int id, String numeProdus, double pret, double volum, boolean produsNatural, boolean pentruCopii, byte[] image) {
        return productInfoDAO.updateProduct(id, numeProdus, pret, volum, produsNatural, pentruCopii, image);
    }

    public boolean deleteProduct(int id) {
        return productInfoDAO.deleteProduct(id);
    }

    public ProductDetails getProductById(int id) {
        return productInfoDAO.getProductById(id);
    }

        public static int randomizeNumber(int min, int max) {
            return (int) ((Math.random() * (max - min)) + min);
        }

}
