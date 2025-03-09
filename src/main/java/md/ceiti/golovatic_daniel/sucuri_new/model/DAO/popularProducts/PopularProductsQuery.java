package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.popularProducts;

public class PopularProductsQuery {

    public static String createKTableQuery = "CREATE TABLE IF NOT EXISTS produse_populare (" +
            "id INT PRIMARY KEY, " +
            "numeProdus VARCHAR(255), " +
            "pret DOUBLE PRECISION, " +
            "nr_vandute_pe_zi INT," +
            "FOREIGN KEY (id) REFERENCES product_details(id) ON DELETE CASCADE" +
            ")";

    public static String truncateKTableQuery = "TRUNCATE TABLE produse_populare";

    public static String populateKTableQuery = "INSERT INTO produse_populare (id, numeProdus, pret, nr_vandute_pe_zi) " +
            "SELECT p.id, p.name, p.price, p.sold_daily " +
            "FROM product_details p " +
            "ORDER BY p.sold_daily DESC";

    public static String deletePTableQuery = "DROP TABLE produse_populare;";


}
