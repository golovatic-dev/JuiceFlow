package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.productInfo;

import md.ceiti.golovatic_daniel.sucuri_new.controller.ProductInfoController;
import md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities.ProductDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import md.ceiti.golovatic_daniel.sucuri_new.model.database.HibernateUtil;

public class ProductInfoDAOImpl implements ProductInfoDAO {

    @Override
    public int insertProduct(String name, double price, double volume, boolean isNatural, boolean forKids, byte[] image) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            ProductDetails product = ProductDetails.builder()
                    .name(name)
                    .price(price)
                    .volume(volume)
                    .isNatural(isNatural)
                    .forKids(forKids)
                    .soldDaily(ProductInfoController.randomizeNumber(50, 1500))
                    .image(image)
                    .build();

            session.save(product);
            transaction.commit();
            return product.getId();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean updateProduct(int id, String name, double price, double volume, boolean isNatural, boolean forKids, byte[] image) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            ProductDetails product = session.get(ProductDetails.class, id);
            if (product != null) {
                product.setName(name);
                product.setPrice(price);
                product.setVolume(volume);
                product.setIsNatural(isNatural);
                product.setForKids(forKids);
                product.setImage(image);

                session.merge(product);
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
    public boolean deleteProduct(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            ProductDetails product = session.get(ProductDetails.class, id);
            if (product != null) {
                session.delete(product);
                session.flush();
                transaction.commit();
                return true;
            } else {
                System.out.println("Produsul cu  " + id + " nu a fost gasit");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Eroare la stergere produs: " + id + " cauza: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ProductDetails getProductById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(ProductDetails.class, id);
        }
    }
}
