package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.kidProducts;

import org.hibernate.Session;
import org.hibernate.Transaction;
import md.ceiti.golovatic_daniel.sucuri_new.model.database.HibernateUtil;

import static md.ceiti.golovatic_daniel.sucuri_new.model.DAO.kidProducts.kidProductsQueries.*;

public class KidProductDAOImpl implements KidProductDAO {


    @Override
    public void createAndPopulateForKidsTable() {
        tableQueries(createTableQuery, truncateTableQuery, populateTableQuery);
    }

    public static void tableQueries(String createTableQuery, String truncateTableQuery, String populateTableQuery) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.createNativeQuery(createTableQuery).executeUpdate();

            session.createNativeQuery(truncateTableQuery).executeUpdate();

            session.createNativeQuery(populateTableQuery).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
