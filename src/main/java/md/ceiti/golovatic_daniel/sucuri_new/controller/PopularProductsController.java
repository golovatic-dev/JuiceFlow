package md.ceiti.golovatic_daniel.sucuri_new.controller;

import md.ceiti.golovatic_daniel.sucuri_new.model.DAO.popularProducts.PopularProductDAO;
import md.ceiti.golovatic_daniel.sucuri_new.model.DAO.popularProducts.PopularProductDAOImpl;

public class PopularProductsController {
    private PopularProductDAO popularProductDAO;

    public PopularProductsController() {
        popularProductDAO = new PopularProductDAOImpl();
    }

    public void createAndPopulatePopularProductsTable() {
        popularProductDAO.createAndPopulatePopularProductsTable();
    }

}
