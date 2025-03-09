package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.product;

public class ProductQueries {

    public static  String getProductsQuery = "SELECT p.id, p.name, p.price, p.volume, p.is_natural, p.for_kids, p.sold_daily, p.image, " +
            "d.procent_fructe, d.procent_adaos_zahar, d.calorii, d.carbohidrati, d.proteine, d.grasimi, " +
            "a.additional_info FROM product_details p " +
            "LEFT JOIN nutrition_product_info d ON p.id = d.id " +
            "LEFT JOIN additional_product_info a ON p.id = a.id";

    public static String updateProductQuery = "UPDATE produse " +
            "SET numeProdus = ?, pret = ?, volum = ?, produs_natural = ?, pentru_copii = ?, image = ? " +
            "WHERE id = ?";

    public static String  deleteProductQuery = "DELETE FROM produse WHERE id = ?";

}
