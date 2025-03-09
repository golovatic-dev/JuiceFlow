package md.ceiti.golovatic_daniel.sucuri_new.controller;

import md.ceiti.golovatic_daniel.sucuri_new.model.DAO.kidProducts.KidProductDAO;
import md.ceiti.golovatic_daniel.sucuri_new.model.DAO.kidProducts.KidProductDAOImpl;

public class KidProductController {
    private KidProductDAO kidProductDAO;

     public KidProductController() {
        kidProductDAO = new KidProductDAOImpl();
    }

    public void createAndPopulateForKidsTable() {
        kidProductDAO.createAndPopulateForKidsTable();
    }

}
