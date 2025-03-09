package md.ceiti.golovatic_daniel.sucuri_new.model.database;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities.AdditionalProductInfo;

public class HibernateUtil {

    @Getter
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.addAnnotatedClass(AdditionalProductInfo.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
