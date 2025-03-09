package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.additionalInfo;

import md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities.AdditionalProductInfo;
import md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities.ProductDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import md.ceiti.golovatic_daniel.sucuri_new.model.database.HibernateUtil;

public class AdditionalInfoDAOImpl implements AdditionalInfoDAO {

    @Override
    public boolean insertAdditionalInfo(String additionalInfo, int productId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            ProductDetails product = session.get(ProductDetails.class, productId);
            if (product == null) {
                System.out.println("Nu a fost gasit produs cu ID: " + productId);
                return false;
            }

            AdditionalProductInfo additionalProductInfo = AdditionalProductInfo.builder()
                    .additionalInfo(additionalInfo)
                    .product(product)
                    .build();

            session.save(additionalProductInfo);
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
    public boolean updateAdditionalInfo(int id, String additionalInfo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            AdditionalProductInfo additionalProductInfo = session.get(AdditionalProductInfo.class, id);
            if (additionalProductInfo != null) {
                additionalProductInfo.setAdditionalInfo(additionalInfo);
                session.update(additionalProductInfo);
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
    public boolean deleteAdditionalInfo(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            AdditionalProductInfo additionalProductInfo = session.get(AdditionalProductInfo.class, id);
            if (additionalProductInfo != null) {
                session.delete(additionalProductInfo);
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
    public AdditionalProductInfo getAdditionalInfoById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(AdditionalProductInfo.class, id);
        }
    }
}
