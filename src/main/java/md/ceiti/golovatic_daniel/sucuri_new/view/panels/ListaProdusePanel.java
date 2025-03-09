package md.ceiti.golovatic_daniel.sucuri_new.view.panels;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import lombok.Getter;
import lombok.Setter;
import md.ceiti.golovatic_daniel.sucuri_new.controller.*;
import md.ceiti.golovatic_daniel.sucuri_new.model.Product.Product;
import md.ceiti.golovatic_daniel.sucuri_new.view.View;
import md.ceiti.golovatic_daniel.sucuri_new.view.components.*;

import java.io.ByteArrayInputStream;
import java.util.List;

public class ListaProdusePanel extends VBox {
    private ProductsController productsController;
    private KidProductController kidProductController;
    private PopularProductsController popularProductsController;

    @Setter
    private View parentView;
    private TextInput search;
    private HBox searchBar;
    private ButtonModel searchBtn;
    private static FlowPane productsPanel;
    ScrollPane scrollPane;
    List<Product> products;
    FilterPanel filterPanel;

    public ListaProdusePanel() {
        productsController = new ProductsController();
        kidProductController = new KidProductController();
        popularProductsController = new PopularProductsController();
        productsPanel = new FlowPane();
        productsPanel.setStyle("-fx-background-color: white;");

        int maxCardsPerRow = 4;
        int cardWidthPercentage = 24;
        int cardHeight = 225;
        int horizontalSpacing = 10;
        int verticalSpacing = 10;

        refreshProducts();


        int totalProducts = products.size();
        int rowCount = (int) Math.ceil((double) totalProducts / maxCardsPerRow);

        productsPanel.setHgap(horizontalSpacing);
        productsPanel.setVgap(verticalSpacing);
        productsPanel.setAlignment(Pos.TOP_CENTER);

        for (Product product : products) {
            ProductCard productCard = new ProductCard(product.getProductDetails().getImage(), product.getProductDetails().getName());
            productCard.setPrefWidth((this.getWidth() * (cardWidthPercentage / 100.0)));
            productCard.setPrefHeight(cardHeight);
            productCard.setOnMouseClicked(e -> showDetailedProduct(product));
            productsPanel.getChildren().add(productCard);
        }

        int panelHeight = rowCount * cardHeight + (rowCount - 1) * verticalSpacing;
        productsPanel.setPrefHeight(panelHeight);
        productsPanel.setPrefWidth(900);
        productsPanel.setAlignment(Pos.TOP_CENTER);
        productsPanel.setHgap(horizontalSpacing);
        productsPanel.setVgap(verticalSpacing);

        scrollPane = new ScrollPane(productsPanel);
        scrollPane.setBorder(null);
        scrollPane.setPrefSize(900, 580);
        scrollPane.setFitToWidth(true);

        searchBar = new HBox();
        searchBar.setStyle("-fx-background-color: #FBFBFB; -fx-border-color: #E5E5E5;");
        searchBar.setPadding(new Insets(10));
        searchBar.setSpacing(10);
        searchBar.setAlignment(Pos.CENTER);

        searchBtn = new ButtonModel();
        searchBtn.setText("Cauta");
        searchBtn.setPrefSize(125, 50);
        searchBtn.setOnAction(e -> filterProducts());

        search = new TextInput("Caută produs ...", 785, 50);
        searchBar.getChildren().addAll(search, searchBtn);
        search.setOnMouseClicked(e -> initSearchListener());
        filterPanel = new FilterPanel();
        filterPanel.setAlignment(Pos.CENTER);
        filterPanel.imageButton.setOnAction(e -> filterRowClicked());

        this.getChildren().addAll(searchBar, filterPanel, scrollPane);

        addProductsPanelChangeListener();

    }

    private void addProductsPanelChangeListener() {
        productsPanel.getChildren().addListener((ListChangeListener<Node>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    refreshProductCards();
                }
            }
        });
    }

    private void refreshProductCards() {
        products = productsController.getProducts();

        for (Node node : productsPanel.getChildren()) {
            if (node instanceof ProductCard productCard) {
                for (Product product : products) {
                    if (product.getProductDetails().getName().equalsIgnoreCase(productCard.getName())) {
                        productCard.setOnMouseClicked(e -> showDetailedProduct(product));
                        break;
                    }
                }
            }
        }
    }

    private void filterRowClicked() {
        filterPanel.filterImageCLicked();

    }

    private void filterProducts() {
            filterPanel.resetFilters();
            String query = search.getText().trim();
            productsPanel.getChildren().clear();

            if (!query.isEmpty() && !query.equals("Caută produs ...")) {
                int i = 0;
                for (Product product : products) {
                    if (product.getProductDetails().getName().toLowerCase().contains(query.toLowerCase())) {
                        i = getProductCard(i, product);
                    }
                }
            } else {

                refreshProducts();

            }

    }

    private void initSearchListener() {
            search.setOnMouseClicked(e -> {
                if (search.getText().equals("Caută produs ...")) {
                    search.clear();
                }
            });

        search.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && search.getText().trim().isEmpty()) {
                search.setText("Caută produs ...");
            }
        });

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filterProducts();
        });

    }

    private int getProductCard(int i, Product product) {
        ProductCard productCard = new ProductCard(
                product.getProductDetails().getImage(),
                product.getProductDetails().getName()
        );
        productCard.setFocusTraversable(false);

        i++;
        productsPanel.getChildren().add(productCard);
        refreshPanel(productsPanel);
        return i;
    }

    public  void refreshProducts()
    {
        productsPanel.getChildren().clear();

        products = productsController.getProducts();
        int i = 0;
        for (Product product : products) {
            i = getProductCard(i, product);
        }
        refreshPanel(productsPanel);

        kidProductController.createAndPopulateForKidsTable();
        popularProductsController.createAndPopulatePopularProductsTable();

    }

    public static void refreshProducts(Boolean forKids, Boolean isNatural, Double maxPrice, Double maxSugar, Double minFruits, Double minSugar, List<Product> sortedProducts) {


        productsPanel.getChildren().clear();

        ProductsController productsController = new ProductsController();
        KidProductController kidsController = new KidProductController();
        PopularProductsController popularController = new PopularProductsController();

        List<Product> products = (sortedProducts != null) ? sortedProducts : productsController.getProducts();


        for (Product product : products) {
            boolean showProduct = true;

            if (forKids != null) {
                showProduct = product.getProductDetails().getForKids() == forKids;
            }

            if (isNatural != null) {
                showProduct = showProduct && product.getProductDetails().getIsNatural() == isNatural;
            }

            if (maxPrice != null) {
                showProduct = showProduct && product.getProductDetails().getPrice() <= maxPrice;
            }

            if (maxSugar != null) {
                showProduct = showProduct && product.getNutritionProductInfo().getProcentAdaosZahar() <= maxSugar;
            }
            if (minFruits != null && minSugar != null) {
                showProduct = showProduct
                        && product.getProductDetails().getIsNatural()
                        && product.getNutritionProductInfo().getProcentAdaosZahar() >= minSugar
                        && product.getNutritionProductInfo().getProcentFructe() >= minFruits;
            }

            if (showProduct) {
                ProductCard productCard = new ProductCard(
                        product.getProductDetails().getImage(),
                        product.getProductDetails().getName()
                );
                productsPanel.getChildren().add(productCard);

            }
        }

        refreshPanel(productsPanel);
        kidsController.createAndPopulateForKidsTable();
        popularController.createAndPopulatePopularProductsTable();
    }

    private void showDetailedProduct(Product product) {
        search.requestFocus();
        HBox detailedPanel = new HBox();
        detailedPanel.setPadding(new Insets(20));
        detailedPanel.setSpacing(20);
        detailedPanel.setAlignment(Pos.CENTER);

        ImageView productImage = new ImageView(new Image(new ByteArrayInputStream(product.getProductDetails().getImage())));
        productImage.setPreserveRatio(true);
        productImage.setFitWidth(300);

        VBox imagePanel = new VBox(productImage);
        imagePanel.setAlignment(Pos.CENTER);
        imagePanel.setSpacing(10);

        HBox.setHgrow(imagePanel, Priority.ALWAYS);
        imagePanel.setMaxWidth(Double.MAX_VALUE);

        VBox productDetails = getProductDetails(product);
        TextFlow productDescription = new TextFlow();
        productDescription.setStyle("-fx-font-size: 18px; -fx-background-color: #f9f9f9; -fx-padding: 10;");

        Text descriptionText = new Text(product.getAdditionalProductInfo().getAdditionalInfo());
        descriptionText.setStyle("-fx-fill: #333333;");
        productDescription.getChildren().add(descriptionText);

        productDescription.setPrefHeight(150);
        productDescription.setPrefWidth(400);

        HBox buttonPanel = getButtonPanel(product);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        VBox rightPanel = new VBox(productDetails, productDescription,spacer, buttonPanel);
        rightPanel.setSpacing(15);
        rightPanel.setPadding(new Insets(10));


        HBox.setHgrow(rightPanel, Priority.ALWAYS);
        rightPanel.setMaxWidth(Double.MAX_VALUE);

        detailedPanel.getChildren().addAll(imagePanel, rightPanel);

        this.getChildren().setAll(searchBar, detailedPanel);

        detailedPanel.widthProperty().addListener((observable, oldWidth, newWidth) -> {
            double panelWidth = newWidth.doubleValue() / 2;
            productImage.setFitWidth(panelWidth);
        });

    }

    private VBox getProductDetails(Product product) {

        Label productName = new Label(product.getProductDetails().getName().toUpperCase());
        productName.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: rgb(22, 85, 157)");

        TextFlow productPrice = new TextFlow(
                new Text("Preț: ") {{
                    setStyle("-fx-font-weight: bold;");
                }},
                new Text(product.getProductDetails().getPrice() + " MDL")
        );

        VBox productDetails = getVBox(product, productName, productPrice);
        productDetails.setSpacing(10);
        productDetails.setPadding(new Insets(10));
        productDetails.setStyle("-fx-font-size: 20px; -fx-text-fill: #333333;");
        return productDetails;
    }

    private VBox getVBox(Product product, Label productName, TextFlow productPrice) {
        TextFlow productVolume = new TextFlow(
                new Text("Volum: ") {{
                    setStyle("-fx-font-weight: bold;");
                }},
                new Text(product.getProductDetails().getVolume() + " ml")
        );

        TextFlow productIsNatural = new TextFlow(
                new Text("Este Natural: ") {{
                    setStyle("-fx-font-weight: bold;");
                }},
                new Text(product.getProductDetails().getIsNatural() ? "Da" : "Nu")
        );

        TextFlow productForKids = new TextFlow(
                new Text("Pentru Copii: ") {{
                    setStyle("-fx-font-weight: bold;");
                }},
                new Text(product.getProductDetails().getForKids() ? "Da" : "Nu")
        );

        return new VBox(productName, productPrice, productVolume, productIsNatural, productForKids);
    }

    private HBox getButtonPanel(Product product) {

        HBox buttonPanel = new HBox(15);
        buttonPanel.setAlignment(Pos.CENTER);

        productsPanel.setHgap(15);
        productsPanel.setVgap(15);
        CustomButton backButton = new CustomButton("Înapoi");
        ButtonModel modifyButton = new ButtonModel();
        modifyButton.setText("Modifică");
        ButtonModel removeButton = new ButtonModel();
        removeButton.setButtonProperties("Șterge", Color.RED, Color.RED);


        backButton.setOnAction(e -> backButtonClick());
        modifyButton.setOnAction(e -> modifyButtonClick(product));
        removeButton.setOnAction(e -> removeButtonClick(product));

        buttonPanel.getChildren().addAll(backButton, modifyButton, removeButton);
        return buttonPanel;
    }

    @Getter
    public static String selectedProduct;

    private void modifyButtonClick(Product product) {
        parentView.handleProdusMenu();

        parentView.getProdusPanel().setProductName(product.getProductDetails().getName());
        setChecked(product);
        parentView.getProdusPanel().setImage(product.getProductDetails().getImage());
        parentView.getProdusPanel().setPrice(product.getProductDetails().getPrice());
        parentView.getProdusPanel().setVolume(product.getProductDetails().getVolume());
        parentView.getProdusPanel().setAdditionalInfo(product.getAdditionalProductInfo().getAdditionalInfo());
        parentView.getProdusPanel().setCarbs(product.getNutritionProductInfo().getCarbohidrati());
        parentView.getProdusPanel().setProteins(product.getNutritionProductInfo().getProteine());
        parentView.getProdusPanel().setFats(product.getNutritionProductInfo().getGrasimi());
        parentView.getProdusPanel().setSugars(product.getNutritionProductInfo().getProcentAdaosZahar());
        parentView.getProdusPanel().setCalories(product.getNutritionProductInfo().getCalorii());
        parentView.getProdusPanel().setFruits(product.getNutritionProductInfo().getProcentFructe());
        setSelectedProduct(product.getProductDetails().getName());
    }

    public static void setSelectedProduct(String selectedProduct) {
        ListaProdusePanel.selectedProduct = selectedProduct;
    }

    public void removeButtonClick(Product product) {
        int i = 0;
        try {
            for (Product element : products) {
                if (element.getProductDetails().getId() == product.getProductDetails().getId()) {
                        productsController.deleteProduct(product.getProductDetails().getId());
                    i = 1;
                    break;
                }
            }
            if (i == 1) {

                new Alert(Alert.AlertType.INFORMATION, "Produsul " + product.getProductDetails().getName() + " a fost șters!").show();
            } else {
                throw new Exception("Ștergerea produsului a eșuat");
            }

            this.getChildren().setAll(searchBar,filterPanel, scrollPane);
            refreshProducts();
            refreshPanel(this);
            refreshPanel(this);

        } catch (Exception e) {
            System.out.println("EXCEPTION HAPPENED REMOVEBUTTONCLICK" + product.getProductDetails().getName());

            new Alert(Alert.AlertType.ERROR, "Produsul nu a putut fi șters!" + e.getMessage()).show();

        }
    }

    private void setChecked(Product product)
    {
        parentView.getProdusPanel().copiiRadio.setChecked(product.getProductDetails().getForKids());

        parentView.getProdusPanel().naturalRadio.setChecked(product.getProductDetails().getIsNatural());
    }

    private void backButtonClick()
    {
            this.getChildren().setAll(searchBar,filterPanel, scrollPane);
        refreshPanel(this);
    }

    public static void refreshPanel(Pane panel) {
        panel.requestLayout();
    }
}
