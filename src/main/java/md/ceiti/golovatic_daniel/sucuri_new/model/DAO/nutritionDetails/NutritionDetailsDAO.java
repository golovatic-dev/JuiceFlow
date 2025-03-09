package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.nutritionDetails;

import md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities.NutritionProductInfo;

public interface NutritionDetailsDAO {
    boolean insertNutritionDetails(int produsId, double procentFructe, double procentAdaosZahar, double calorii, double carbohidrati, double proteine, double grasimi);
    boolean updateNutritionDetails(int id, double procentFructe, double procentAdaosZahar, double calorii, double carbohidrati, double proteine, double grasimi);
    boolean deleteNutritionDetails(int id);
    NutritionProductInfo getNutritionDetailsById(int id);
}
