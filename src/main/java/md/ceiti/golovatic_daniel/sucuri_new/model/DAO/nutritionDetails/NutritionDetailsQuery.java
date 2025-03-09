package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.nutritionDetails;

public class NutritionDetailsQuery {

    public static String insertNutritionQuery = "INSERT INTO detalii_nutritionale " +
            "(produs_id, procent_fructe, procent_adaos_zahar, calorii, carbohidrati, proteine, grasimi) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static String  deleteNutritionDetailsQuery = "DELETE FROM detalii_nutritionale WHERE produs_id = ?";

    public static String updateNutritionQuery = "UPDATE detalii_nutritionale " +
            "SET procent_fructe = ?, procent_adaos_zahar = ?, calorii = ?, carbohidrati = ?, proteine = ?, grasimi = ?" +
            "WHERE produs_id = ?";

}
