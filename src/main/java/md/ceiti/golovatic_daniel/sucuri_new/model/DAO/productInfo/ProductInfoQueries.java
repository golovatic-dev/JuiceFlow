package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.productInfo;

public class ProductInfoQueries {
    public static  String insertProductQuery = " INSERT INTO produse " +
            "(numeProdus, pret, volum, produs_natural, pentru_copii, nr_vandute_pe_zi, image) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
}
