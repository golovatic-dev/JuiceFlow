package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.popularProducts;

import md.ceiti.golovatic_daniel.sucuri_new.model.DAO.kidProducts.KidProductDAOImpl;
import static md.ceiti.golovatic_daniel.sucuri_new.model.DAO.popularProducts.PopularProductsQuery.*;

public class PopularProductDAOImpl implements PopularProductDAO {
    @Override
    public void createAndPopulatePopularProductsTable() {
        KidProductDAOImpl.tableQueries(createKTableQuery, truncateKTableQuery, populateKTableQuery);
    }
    }

