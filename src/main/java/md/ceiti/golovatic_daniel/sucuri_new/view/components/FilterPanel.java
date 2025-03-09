package md.ceiti.golovatic_daniel.sucuri_new.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import md.ceiti.golovatic_daniel.sucuri_new.controller.ProductsController;
import md.ceiti.golovatic_daniel.sucuri_new.model.Product.Product;
import md.ceiti.golovatic_daniel.sucuri_new.view.panels.ListaProdusePanel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FilterPanel extends FlowPane {
    private ProductsController productsController;
    private Boolean isNatural = null;
    private Boolean forKids = null;
    private Double maxPrice = null;
    private Double maxSugar = null;
    private Double minSugar = null;
    private Double minFruits = null;

    private boolean secondRow = false;

    private FilterButton pret;
    private FilterButton zaharFilter;
    private FilterRadio naturalFilter;
    private FilterRadio copiiFilter;
    private FilterButton natuuraleFructeFilter;
    private FilterButton sortFilter;
    private FilterButton ascendingDescendingButton;
    public Button imageButton;

    private List<Product> products;

    public FilterPanel() {
        productsController = new ProductsController();

        naturalFilter = new FilterRadio("Natural");
        copiiFilter = new FilterRadio("Pentru copii");
        pret = new FilterButton("Pret ↓");
        zaharFilter = new FilterButton("Zahar % ↓");
        natuuraleFructeFilter = new FilterButton("Fructe / naturale ↓");
        sortFilter = new FilterButton("Sortare: - ↓");
        ascendingDescendingButton = new FilterButton("Cresc 📈");
        imageButton = new Button();

        ImageView imageView = new ImageView("file:src/main/resources/images/filter.png");
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        imageView.setPreserveRatio(true);
        imageButton.setGraphic(imageView);
        imageButton.setStyle("-fx-background-color: transparent; -fx-border: none;");
        imageButton.setOnMouseEntered(e ->
        {

            imageButton.setCursor(Cursor.HAND);
            imageView.setFitWidth(30);
            imageView.setFitHeight(30);
        });
        imageButton.setOnMouseExited(e ->
        {
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
        });

        imageButton.setOnMouseClicked(e -> resetFilters());
        pret.setOnAction(e -> showPricePopup());
        zaharFilter.setOnAction(e -> showZaharPopup());
        natuuraleFructeFilter.setOnAction(e -> showNaturaleFructePopup());
        sortFilter.setOnAction(e -> showSortPopup());
        ascendingDescendingButton.setOnAction(e -> toggleAscendingDescending());
        naturalFilter.setOnAction(e -> naturalClicked());
        copiiFilter.setOnAction(e -> kidsClicked());


        this.setPadding(new Insets(10));
        this.setHgap(5);
        this.setVgap(10);
        this.setStyle("-fx-background-color: #FBFBFB; -fx-border-color: #E5E5E5;");
        this.setPrefHeight(50);

        this.getChildren().addAll(
                imageButton, naturalFilter, copiiFilter,
                pret, zaharFilter, natuuraleFructeFilter,
                sortFilter, ascendingDescendingButton
        );
    }

    public void filterImageCLicked() {
        secondRow = !secondRow;
    }

    public boolean secondRowState() {
        return secondRow;
    }

    private void kidsClicked() {
        forKids = copiiFilter.isSelected() ? true : null;
        ListaProdusePanel.refreshProducts(forKids, isNatural, maxPrice, maxSugar, minFruits, minSugar, products);
    }

    private void naturalClicked() {
        isNatural = naturalFilter.isSelected() ? true : null;
        ListaProdusePanel.refreshProducts(forKids, isNatural, maxPrice, maxSugar, minFruits, minSugar, products);
    }

    private void toggleAscendingDescending() {
        if (ascendingDescendingButton.getText().contains("Cresc")) {
            ascendingDescendingButton.setText("Desc 📉");
        } else {
            ascendingDescendingButton.setText("Cresc 📈");
        }
        applySorting();
    }

    private void showPricePopup() {
        Stage popup = new Stage();
        popup.setTitle("Setează preț maxim");

        Slider priceSlider = new Slider(0, 1000, 500);
        TextField priceValueField = new TextField(String.valueOf((int) priceSlider.getValue()));
        priceValueField.setEditable(false);

        priceSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                priceValueField.setText(String.valueOf(newVal.intValue()))
        );

        Button confirmButton = new Button("Confirmă");
        confirmButton.setOnAction(e -> {
            maxPrice = Double.parseDouble(priceValueField.getText());
            ListaProdusePanel.refreshProducts(forKids, isNatural, maxPrice, maxSugar, minFruits, minSugar, products);
            popup.close();
        });

        VBox layout = new VBox(10, new Label("Setează preț maxim:"), priceSlider, priceValueField, confirmButton);
        layout.setPadding(new Insets(10));
        popup.setScene(new Scene(layout));
        popup.show();
    }

    private void showZaharPopup() {
        Stage popup = new Stage();
        popup.setTitle("Setează procentaj zahăr");

        TextField sugarInputField = new TextField();
        Button confirmButton = getButton(sugarInputField, popup);
        VBox layout = new VBox(10, new Label("Introduceți % maxim de zahăr:"), sugarInputField, confirmButton);
        layout.setPadding(new Insets(10));
        popup.setScene(new Scene(layout));
        popup.show();
    }

    private Button getButton(TextField sugarInputField, Stage popup) {
        Button confirmButton = new Button("Confirmă");

        confirmButton.setOnAction(e -> {
            try {
                maxSugar = Double.parseDouble(sugarInputField.getText());
                zaharFilter.setText("Zahar: " + maxSugar + "%");
                ListaProdusePanel.refreshProducts(forKids, isNatural, maxPrice, maxSugar, minFruits, minSugar, products);
                popup.close();
            } catch (NumberFormatException ex) {
                new Popup().getContent().add(new Label("Introduceți un număr valid!"));
            }
        });
        return confirmButton;
    }

    private void showNaturaleFructePopup() {
        Stage popup = new Stage();
        popup.setTitle("Setează procentajurile");

        TextField sugarField = new TextField();
        TextField fruitField = new TextField();
        Button confirmButton = getButton(sugarField, fruitField, popup);

        GridPane layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(10);
        layout.addRow(0, new Label("Introduceți % adaosului de zahăr (Min):"), sugarField);
        layout.addRow(1, new Label("Introduceți % fructe (Min):"), fruitField);
        layout.addRow(2, new Label(), confirmButton);
        layout.setPadding(new Insets(10));

        popup.setScene(new Scene(layout));
        popup.show();
    }

    private Button getButton(TextField sugarField, TextField fruitField, Stage popup) {
        Button confirmButton = new Button("Confirmă");

        confirmButton.setOnAction(e -> {
            try {
                minSugar = Double.parseDouble(sugarField.getText());
                minFruits = Double.parseDouble(fruitField.getText());
                ListaProdusePanel.refreshProducts(forKids, isNatural, maxPrice, maxSugar, minFruits, minSugar, products);
                popup.close();
            } catch (NumberFormatException ex) {
                new Popup().getContent().add(new Label("Introduceți valori numerice valide!"));
            }
        });
        return confirmButton;
    }

    private void showSortPopup() {
        Stage popup = new Stage();
        popup.setTitle("Selectează modul de sortare");
        popup.initModality(Modality.APPLICATION_MODAL);

        ToggleGroup sortGroup = new ToggleGroup();

        RadioButton aToZ = new RadioButton("A-Z");
        aToZ.setToggleGroup(sortGroup);
        aToZ.setSelected(true);

        RadioButton price = new RadioButton("Price");
        price.setToggleGroup(sortGroup);

        RadioButton popularitate = new RadioButton("Popularitate");
        popularitate.setToggleGroup(sortGroup);

        Button confirmButton = new Button("Confirmă");
        confirmButton.setOnAction(event -> {
            if (aToZ.isSelected()) {
                sortFilter.setText("Sortare: A-Z");
            } else if (price.isSelected()) {
                sortFilter.setText("Sortare: Price");
            } else {
                sortFilter.setText("Sortare: Popularitate");
            }
            popup.close();
            applySorting();

        });

        VBox contentPanel = new VBox(10, aToZ, price, popularitate, confirmButton);
        contentPanel.setPadding(new Insets(10));
        contentPanel.setAlignment(Pos.CENTER);

        Scene scene = new Scene(contentPanel, 300, 200);
        popup.setScene(scene);
        popup.showAndWait();
    }
    public void applySorting() {
        List<Product> originalProducts = productsController.getProducts();
        String sortCriteria = sortFilter.getText().toLowerCase();
        boolean isAscending = ascendingDescendingButton.getText().contains("Cresc");

        Comparator<Product> comparator = null;

        if (sortCriteria.contains("price")) {
            comparator = Comparator.comparing(product -> product.getProductDetails().getPrice());
        } else if (sortCriteria.contains("a-z")) {
            comparator = Comparator.comparing(product -> product.getProductDetails().getName().toLowerCase());
        } else if (sortCriteria.contains("popularitate")) {
            comparator = Comparator.comparing(product -> product.getProductDetails().getSoldDaily());
        }

        if (comparator != null) {
            if (!isAscending) {
                comparator = comparator.reversed();
            }
            products = originalProducts.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } else {
            products = originalProducts;
        }

        ListaProdusePanel.refreshProducts(forKids, isNatural, maxPrice, maxSugar, minFruits, minSugar, products);
    }

    public void resetFilters() {
        isNatural = null;
        forKids = null;
        maxPrice = null;
        maxSugar = null;
        minSugar = null;
        minFruits = null;

        pret.setText("Pret ↓");
        zaharFilter.setText("Zahar % ↓");
        natuuraleFructeFilter.setText("Frute / naturale ↓");
        sortFilter.setText("Sortare: - ↓");

        naturalFilter.setSelected(false);
        copiiFilter.setSelected(false);

        ascendingDescendingButton.setText("Cresc 📈");

        ListaProdusePanel.refreshProducts(forKids, isNatural, maxPrice, maxSugar, minFruits, minSugar, products);
    }


}
