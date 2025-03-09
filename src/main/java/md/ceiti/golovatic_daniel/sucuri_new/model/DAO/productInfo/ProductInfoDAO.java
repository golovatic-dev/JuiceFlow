package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.productInfo;

import md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities.ProductDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import md.ceiti.golovatic_daniel.sucuri_new.model.database.HibernateUtil;

public interface ProductInfoDAO{
    int insertProduct(String name, double price, double volume, boolean isNatural, boolean forKids, byte[] image);
    boolean updateProduct(int id, String name, double price, double volume, boolean isNatural, boolean forKids, byte[] image);
    boolean deleteProduct(int id);
    ProductDetails getProductById(int id);
}