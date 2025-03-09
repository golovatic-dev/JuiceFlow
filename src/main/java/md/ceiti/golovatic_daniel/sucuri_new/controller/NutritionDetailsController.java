package md.ceiti.golovatic_daniel.sucuri_new.controller;

import md.ceiti.golovatic_daniel.sucuri_new.model.DAO.nutritionDetails.NutritionDetailsDAO;
import md.ceiti.golovatic_daniel.sucuri_new.model.DAO.nutritionDetails.NutritionDetailsDAOImpl;
import md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities.NutritionProductInfo;

public class NutritionDetailsController {
    private NutritionDetailsDAO nutritionDetailsDAO;

    public NutritionDetailsController() {
        nutritionDetailsDAO = new NutritionDetailsDAOImpl();
    }

    public boolean insertNutritionDetails(int produsId, double procentFructe, double procentAdaosZahar, double calorii, double carbohidrati, double proteine, double grasimi) {
        return nutritionDetailsDAO.insertNutritionDetails(produsId, procentFructe, procentAdaosZahar, calorii, carbohidrati, proteine, grasimi);
    }

    public boolean updateNutritionDetails(int id, double procentFructe, double procentAdaosZahar, double calorii, double carbohidrati, double proteine, double grasimi) {
        return nutritionDetailsDAO.updateNutritionDetails(id, procentFructe, procentAdaosZahar, calorii, carbohidrati, proteine, grasimi);
    }

    public boolean deleteNutritionDetails(int id) {
        return nutritionDetailsDAO.deleteNutritionDetails(id);
    }

    public NutritionProductInfo getNutritionDetailsById(int id) {
        return nutritionDetailsDAO.getNutritionDetailsById(id);
    }
}
