package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.kidProducts;

public class kidProductsQueries {

    public static String createTableQuery = "CREATE TABLE IF NOT EXISTS produse_pentru_copii (" +
            "id INT PRIMARY KEY, " +
            "numeProdus VARCHAR(255), " +
            "pret DOUBLE PRECISION, " +
            "image BYTEA, " +
            "procentFructe DOUBLE PRECISION, " +
            "procentAdaosZahar DOUBLE PRECISION, " +
            "calorii DOUBLE PRECISION, " +
            "carbohidrati DOUBLE PRECISION, " +
            "grasimi DOUBLE PRECISION, " +
            "proteine DOUBLE PRECISION," +
            "FOREIGN KEY (id) REFERENCES product_details(id) ON DELETE CASCADE" +
            ")";

    public static String truncateTableQuery = "TRUNCATE TABLE produse_pentru_copii";


    public static String populateTableQuery = "INSERT INTO produse_pentru_copii (id, numeProdus, pret, image, procentFructe, procentAdaosZahar, calorii, carbohidrati, grasimi, proteine) " +
            "SELECT p.id, p.name, p.price, p.image, d.procent_fructe, d.procent_adaos_zahar, d.calorii, d.carbohidrati, d.grasimi, d.proteine " +
            "FROM product_details p " +
            "LEFT JOIN nutrition_product_info d ON p.id = d.id " +
            "WHERE p.for_kids = true";

    public static String deleteTableQuery = "DROP TABLE produse_pentru_copii";
    }
