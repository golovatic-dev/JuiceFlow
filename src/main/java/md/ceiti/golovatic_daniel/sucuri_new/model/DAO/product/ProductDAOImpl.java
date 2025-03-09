package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.product;

import md.ceiti.golovatic_daniel.sucuri_new.controller.*;
import md.ceiti.golovatic_daniel.sucuri_new.model.Product.Product;
import md.ceiti.golovatic_daniel.sucuri_new.model.database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    ProductInfoController productInfoController;
    AdditionalInfoController additionalInfoController;
    NutritionDetailsController nutritionDetailsController;

    @Override
    public List<Product> getProducts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = ProductQueries.getProductsQuery;

            NativeQuery<Object[]> query = session.createNativeQuery(hql);
            List<Object[]> results = query.list();
            return results.stream().map(row -> {
                Product product = new Product();
                product.getProductDetails().setId((Integer) row[0]);
                product.getProductDetails().setName((String) row[1]);
                product.getProductDetails().setPrice((Double) row[2]);
                product.getProductDetails().setVolume((Double) row[3]);
                product.getProductDetails().setIsNatural((Boolean) row[4]);
                product.getProductDetails().setForKids((Boolean) row[5]);
                product.getProductDetails().setSoldDaily((Integer) row[6]);
                product.getProductDetails().setImage(row[7] != null ? (byte[]) row[7] : ProductsController.getDefaultImage());

                product.getNutritionProductInfo().setProcentFructe((Double) row[8]);
                product.getNutritionProductInfo().setProcentAdaosZahar((Double) row[9]);
                product.getNutritionProductInfo().setCalorii((Double) row[10]);
                product.getNutritionProductInfo().setCarbohidrati((Double) row[11]);
                product.getNutritionProductInfo().setProteine((Double) row[12]);
                product.getNutritionProductInfo().setGrasimi((Double) row[13]);

                product.getAdditionalProductInfo().setAdditionalInfo(row[14] != null ? (String) row[14] : "Nu sunt informații adăugătoare.");
                return product;
            }).toList();
        }
    }

    @Override
    public boolean deleteProduct(int productId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            productInfoController = new ProductInfoController();
            productInfoController.deleteProduct(productId);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateProduct(int id, String numeProdus, double pret, double volum, Boolean produs_natural,
                              Boolean pentru_copii, byte[] image, double procent_fructe,
                              double procent_adaos_zahar, double calorii, double carbohidrati, double proteine,
                              double grasimi, String additionalInformation) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            productInfoController = new ProductInfoController();
            additionalInfoController = new AdditionalInfoController();
            nutritionDetailsController = new NutritionDetailsController();

            productInfoController.updateProduct(id, numeProdus, pret,  volum, produs_natural,  pentru_copii, image);
            additionalInfoController.updateAdditionalInfo(id, additionalInformation);
            nutritionDetailsController.updateNutritionDetails(id, procent_fructe, procent_adaos_zahar, calorii, carbohidrati, proteine, grasimi );

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new Exception("Eroare la update produs cu id: " + id, e);
        }
    }
}
