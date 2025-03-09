package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.nutritionDetails;

import md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities.NutritionProductInfo;
import md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities.ProductDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import md.ceiti.golovatic_daniel.sucuri_new.model.database.HibernateUtil;

public class NutritionDetailsDAOImpl implements NutritionDetailsDAO {

    @Override
    public boolean insertNutritionDetails(int produsId, double procentFructe, double procentAdaosZahar,
                                          double calorii, double carbohidrati, double proteine, double grasimi) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            ProductDetails product = session.get(ProductDetails.class, produsId);
            if (product == null) {
                System.out.println("Nu a fost gasit produs cu ID: " + produsId);
                return false;
            }

            NutritionProductInfo nutritionProductInfo = NutritionProductInfo.builder()
                    .product(product)
                    .procentFructe(procentFructe)
                    .procentAdaosZahar(procentAdaosZahar)
                    .calorii(calorii)
                    .carbohidrati(carbohidrati)
                    .proteine(proteine)
                    .grasimi(grasimi)
                    .build();

            session.save(nutritionProductInfo);
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateNutritionDetails(int id, double procentFructe, double procentAdaosZahar, double calorii, double carbohidrati, double proteine, double grasimi) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            NutritionProductInfo nutritionProductInfo = session.get(NutritionProductInfo.class, id);
            if (nutritionProductInfo != null) {
                nutritionProductInfo.setProcentFructe(procentFructe);
                nutritionProductInfo.setProcentAdaosZahar(procentAdaosZahar);
                nutritionProductInfo.setCalorii(calorii);
                nutritionProductInfo.setCarbohidrati(carbohidrati);
                nutritionProductInfo.setProteine(proteine);
                nutritionProductInfo.setGrasimi(grasimi);
                session.update(nutritionProductInfo);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteNutritionDetails(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            NutritionProductInfo nutritionProductInfo = session.get(NutritionProductInfo.class, id);
            if (nutritionProductInfo != null) {
                session.delete(nutritionProductInfo);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public NutritionProductInfo getNutritionDetailsById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(NutritionProductInfo.class, id);
        }
    }
}
