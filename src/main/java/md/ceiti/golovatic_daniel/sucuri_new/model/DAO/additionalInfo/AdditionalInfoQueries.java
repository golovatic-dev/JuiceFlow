package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.additionalInfo;

public class AdditionalInfoQueries {

    public static String insertAdditionalQuery = "INSERT INTO additional_product_info " +
            "(productID , information) " +
            "VALUES (?, ?)";

    public static String  deleteAdditionalInfoQuery = "DELETE FROM additional_product_info WHERE productID = ?";

    public static String updateAdditionalInfoQuery = "UPDATE additional_product_info " +
            "SET information = ? " +
            "WHERE productID = ?";

}
