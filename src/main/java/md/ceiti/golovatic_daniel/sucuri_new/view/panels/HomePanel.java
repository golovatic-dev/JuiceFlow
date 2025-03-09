package md.ceiti.golovatic_daniel.sucuri_new.view.panels;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import md.ceiti.golovatic_daniel.sucuri_new.controller.*;
import md.ceiti.golovatic_daniel.sucuri_new.model.Product.Product;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePanel extends VBox {
    private VBox vanduteZiCard;
    private VBox fructUtilizatCard;
    private VBox produsIeftinCard;
    private VBox mediaPreturiCard;
    private ProductsController productsController;
    private List<Product> products;

    public HomePanel() {
        productsController = new ProductsController();
        products = productsController.getProducts();

        this.setSpacing(20);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #f0f0f0;");

        Label titleLabel = new Label("Dashboard");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        vanduteZiCard = createCardPane(Color.rgb(255, 223, 186));
        fructUtilizatCard = createCardPane(Color.rgb(255, 255, 204));
        produsIeftinCard = createCardPane(Color.rgb(204, 255, 204));
        mediaPreturiCard = createCardPane(Color.rgb(255, 204, 204));

        HBox topRow = new HBox(30, vanduteZiCard, fructUtilizatCard);
        HBox bottomRow = new HBox(30, produsIeftinCard, mediaPreturiCard);
        topRow.setAlignment(Pos.CENTER);
        bottomRow.setAlignment(Pos.CENTER);

        this.getChildren().addAll(titleLabel, topRow, bottomRow);
        paintCards();
    }

    private VBox createCardPane(Color backgroundColor) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPrefSize(330, 230);
        card.setStyle("-fx-background-color: #" + backgroundColor.toString().substring(2) + ";"
                + "-fx-border-color: #c8c8c8; -fx-border-radius: 10; -fx-padding: 10;");
        return card;
    }

    private void paintCards() {
        createCard(vanduteZiCard, "Total produse vândute timp de o zi", fillVandute(), "file:src/main/resources/images/soldProduct.png");
        createCard(fructUtilizatCard, "Cel mai utilizat fruct", fillFructUtilizat(), "file:src/main/resources/images/fruit.png");
        createCard(produsIeftinCard, "Cel mai ieftin produs", fillProdusIeftin(), "file:src/main/resources/images/cheapProduct.png");
        createCard(mediaPreturiCard, "Media prețurilor", fillMediaPreturi(), "file:src/main/resources/images/priceAvg.png");
    }

    private void createCard(VBox card, String title, TextFlow value, String imagePath) {
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-alignment: center;");

        value.setStyle("-fx-font-size: 20px; -fx-text-alignment: center; -fx-alignment: center;");

        ImageView imageView = new ImageView();
        try {
            imageView.setImage(new Image(imagePath));
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
        } catch (Exception e) {
            System.err.println("Eroare la incarcarea imaginii: " + imagePath + " - " + e.getMessage());
        }

        card.getChildren().setAll(titleLabel, imageView, value);
    }

    private TextFlow fillVandute() {
        int totalSold = products.stream()
                .mapToInt(product -> product.getProductDetails().getSoldDaily())
                .sum();
        return new TextFlow(styledText(String.valueOf(totalSold), "-fx-font-weight: bold;"));
    }

    public TextFlow fillFructUtilizat() {
        Map<String, Integer> fruitCounts = new HashMap<>();
        Pattern pattern = Pattern.compile("(fructe|fruct|fruit|fruits|fruit flavors|arome fructe|aroma fructe|arome fruct|aroma fruct)\\s*[:]?\\s*([^;]+)[,;]?", Pattern.CASE_INSENSITIVE);
        for (Product product : products) {
            String additionalInfo = product.getAdditionalProductInfo().getAdditionalInfo();

            if (additionalInfo != null && !additionalInfo.isEmpty()) {
                Matcher matcher = pattern.matcher(additionalInfo);

                while (matcher.find()) {
                    String fruits = matcher.group(2).trim();

                    String[] fruitList = fruits.split("[,\\s]+");

                    for (String fruit : fruitList) {
                        fruit = fruit.trim();
                        if (!fruit.isEmpty()) {
                            fruitCounts.put(fruit, fruitCounts.getOrDefault(fruit, 0) + 1);
                        }
                    }
                }
            }
        }

        String mostMatchedFruit = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : fruitCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostMatchedFruit = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        if (mostMatchedFruit != null) {
            return new TextFlow(styledText(mostMatchedFruit, "-fx-font-weight: bold;"),
                    new Text(" folosit de "),
                    styledText(maxCount + " ori", "-fx-font-weight: bold;"));
        } else {
            return new TextFlow( new Text("Niciun fruct găsit."));
        }
    }

    private TextFlow fillProdusIeftin() {
        return products.stream()
                .min(Comparator.comparingDouble(product -> product.getProductDetails().getPrice()))
                .map(product -> new TextFlow(
                        styledText(product.getProductDetails().getName(), "-fx-font-weight: bold;"),
                        new Text(" la doar "),
                        styledText(product.getProductDetails().getPrice() + " MDL", "-fx-font-weight: bold;")
                ))
                .orElse(new TextFlow(new Text("Nu există produse disponibile.")));
    }

    private TextFlow fillMediaPreturi() {
        double averagePrice = products.stream().mapToDouble(product -> product.getProductDetails().getPrice()).average().orElse(0.0);
        return new TextFlow(styledText(String.format("%.2f MDL", averagePrice), "-fx-font-weight: bold;"));
    }

    private Text styledText(String content, String style) {
        Text text = new Text(content);
        text.setStyle(style);
        return text;
    }

    public void refreshPanel() {
        products = productsController.getProducts();
        paintCards();
    }
}
