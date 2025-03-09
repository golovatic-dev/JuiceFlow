package md.ceiti.golovatic_daniel.sucuri_new.view.panels;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import md.ceiti.golovatic_daniel.sucuri_new.controller.*;
import md.ceiti.golovatic_daniel.sucuri_new.model.Product.Product;
import md.ceiti.golovatic_daniel.sucuri_new.view.components.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
public class ProdusPanel extends VBox {

    RadioPanel copiiRadio;
    RadioPanel naturalRadio;
    private NormalLabel productName;
    private TextInput productNameInput;
    private ButtonModel modificaBtn;
    private CustomButton newProductBtn;
    private ButtonModel resetInputBtn;
    private Button imagePickerBtn;
    TextArea additionalInfo;

    private VBox leftPanel;
    private FlowPane innerPanel;
    private VBox rightPanel;

    private TextPanel volumPanel;
    private TextPanel carbsPanel;
    private TextPanel proteinPanel;
    private TextPanel caloriiPanel;
    private TextPanel grasimiPanel;
    private TextPanel zaharuriPanel;
    private TextPanel fructePanel;
    private HBox copiiNaturalPanel;
    private VBox additionalPanel;
    private TextPanel pricePanel;
    private File selectedImageFile;
    private ProductInfoController productInfoController;
    private ProductsController productsController;
    private NutritionDetailsController nutritionDetailsController;
    private AdditionalInfoController additionalInfoController;
    List<Product> products;
    byte[] imageBytesValue = null;

    public ProdusPanel() {
          productInfoController = new ProductInfoController();
          productsController = new ProductsController();
          nutritionDetailsController = new NutritionDetailsController();
          additionalInfoController = new AdditionalInfoController();
        setSpacing(10);
        setPadding(new Insets(20, 50, 20, 50));


        HBox productNamePanel = new HBox();
        productName = new NormalLabel("Nume produs ");
        productNameInput = new TextInput("Introdu nume produs...", 350, 5);
        productNamePanel.getChildren().addAll(productName, productNameInput);
        HBox.setHgrow(productNameInput, Priority.ALWAYS);

        volumPanel = new TextPanel("Volum ", " l");
        caloriiPanel = new TextPanel("Calorii ", " kcal");
        grasimiPanel = new TextPanel("Grăsimi ", " g");
        carbsPanel = new TextPanel("Carbs ", " g");
        zaharuriPanel = new TextPanel("Zaharuri  ", " %");
        proteinPanel = new TextPanel("Proteine ", " g");
        fructePanel = new TextPanel("Fructe  ", " %");

        innerPanel = new FlowPane();
        innerPanel.setHgap(50);
        innerPanel.setVgap(10);
        innerPanel.setAlignment(Pos.CENTER_LEFT);
        innerPanel.getChildren().addAll(caloriiPanel, grasimiPanel, carbsPanel, zaharuriPanel, proteinPanel, fructePanel);

        copiiRadio = new RadioPanel("Pentru copii");
        naturalRadio = new RadioPanel("Natural");
        copiiNaturalPanel = new HBox(10, copiiRadio, naturalRadio);
        HBox.setHgrow(copiiRadio, Priority.ALWAYS);
        HBox.setHgrow(naturalRadio, Priority.ALWAYS);

        additionalPanel = new VBox(10);
        additionalPanel.getChildren().add(new NormalLabel("Informații adiționale "));
        additionalInfo = new TextArea();
        additionalInfo.setWrapText(true);
        additionalInfo.setStyle("-fx-background-color: #FBFBFB; -fx-font-size: 15; -fx-padding: 8; -fx-border-color: #AEAEAE; -fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-border-radius: 5");
        additionalPanel.getChildren().add(additionalInfo);

        additionalInfo.setEditable(true);
        additionalInfo.setFont(new Font("OpenSans", 15));

        pricePanel = new TextPanel("Preț ", " MDL");

        leftPanel = new VBox(10, volumPanel, innerPanel, copiiNaturalPanel, additionalPanel, pricePanel);
        rightPanel = new VBox(10);
        imagePickerBtn = new Button("Alege imagine");
        imagePickerBtn.setStyle("-fx-background-color: #FBFBFB; -fx-border-color: gray; -fx-border-width: 1; -fx-pref-width: 285; -fx-pref-height: 285;");
        setPickerImage();
        rightPanel.getChildren().add(imagePickerBtn);

        HBox middleRow = new HBox(20, leftPanel, rightPanel);
        HBox.setHgrow(leftPanel, Priority.ALWAYS);

        HBox buttonsPanel = new HBox(10);
        modificaBtn = new ButtonModel();
        modificaBtn.setText("Modifică produs");
        newProductBtn = new CustomButton("Înregistrează produs nou");
        resetInputBtn = new ButtonModel();
        resetInputBtn.setText("Resetează");
        buttonsPanel.setAlignment(Pos.CENTER);
        buttonsPanel.getChildren().addAll(modificaBtn, newProductBtn, resetInputBtn);

        getChildren().addAll(productNamePanel, middleRow, buttonsPanel);

        modificaBtn.setOnAction(e -> modificaClicked());
        newProductBtn.setOnAction(e -> newBtnClicked());
        resetInputBtn.setOnAction(e -> resetInputs());
        imagePickerBtn.setOnAction(e -> pickImage());
    }

    private void pickImage() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Alege imagine");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg", "*.bmp"));


        File selectedFile = fileChooser.showOpenDialog(imagePickerBtn.getScene().getWindow());
        if (selectedFile != null) {
            selectedImageFile = selectedFile;
            imagePickerBtn.setText("");

            javafx.scene.image.Image image = new javafx.scene.image.Image(selectedFile.toURI().toString());
            ImageView imageView = new ImageView(image);


            imageView.setFitWidth(imagePickerBtn.getPrefWidth());
            imageView.setFitHeight(imagePickerBtn.getPrefHeight());
            imageView.setPreserveRatio(true);

            imagePickerBtn.setGraphic(imageView);
        }
    }

    public void modificaClicked() {
        String preselectedProductName = ListaProdusePanel.getSelectedProduct();

        products = productsController.getProducts();
        if (validateData()) {
            try {
                List<String> productNames = products.stream()
                        .map(product -> product.getProductDetails().getName())
                        .toList();

                ChoiceDialog<String> productDialog = new ChoiceDialog<>(preselectedProductName, productNames);
                productDialog.setTitle("Alege produs");
                productDialog.setHeaderText(null);
                productDialog.setContentText("Selectează un produs:");

                productDialog.getDialogPane().setPrefWidth(300);
                var result = productDialog.showAndWait();

                if (result.isPresent()) {

                    if (getCurrentProduct() == null) {
                        throw new Exception("Produsul nu a fost găsit.");
                    }

                    if (imageBytesValue == null) {
                        imageBytesValue = getCurrentProduct().getProductDetails().getImage();
                    }

                    productsController.updateProduct(
                            getCurrentProduct().getProductDetails().getId(),
                            numeProdusValue,
                            pretValue,
                            volumValue,
                            produsNaturalValue,
                            pentruCopiiValue,
                            imageBytesValue,
                            procentFructeValue,
                            procentAdaosZaharValue,
                            caloriiValue,
                            carbohidratiValue,
                            proteineValue,
                            grasimiValue,
                            additionalInformationValue
                    );

                    resetInputs();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Atentie", "Operațiune anulată.");
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Eroare", "Problemă: " + ex.getMessage());
            }
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Product getCurrentProduct() {
        String selectedProductName = ListaProdusePanel.getSelectedProduct();
        products = productsController.getProducts();

        Product currentProduct = null;
        for (Product p : products) {
            if (p.getProductDetails().getName().equals(selectedProductName)) {
                currentProduct = p;
                break;
            }
        }
        return currentProduct;
    }

    public void newBtnClicked()
    {
        if (validateData()) {
            try {
                if (imageBytesValue == null) {
                    if (getCurrentProduct() != null) {
                        imageBytesValue = getCurrentProduct().getProductDetails().getImage();
                    }
                }
                boolean produsNaturalBool = produsNaturalValue;
                boolean pentruCopiiBool = pentruCopiiValue;

                int produsId = productInfoController.insertProduct(numeProdusValue, pretValue, volumValue, produsNaturalBool, pentruCopiiBool, imageBytesValue);

                if (produsId != -1) {
                    boolean additionalInserted = additionalInfoController.insertAdditionalInfo(
                             additionalInformationValue,
                            produsId
                    );

                    boolean nutritionInserted = nutritionDetailsController.insertNutritionDetails(
                            produsId, procentFructeValue, procentAdaosZaharValue, caloriiValue, carbohidratiValue, proteineValue, grasimiValue
                    );

                    if (nutritionInserted && additionalInserted) {
                        showAlert(Alert.AlertType.INFORMATION, "", "Produsul a fost înregistrat cu succes!");

                    } else {
                        showAlert(Alert.AlertType.ERROR, "",  "Eroare la înregistrarea detaliilor nutriționale.");

                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, "",  "Eroare la înregistrarea produsului.");

                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Date invalide",  "Eroare: "+ ex.getMessage());
            }

            resetInputs();
        }
        else ;
    }

    public boolean validateData()
    {
        if (fieldValues1() && fieldValues2()) {
            if (numeProdusValue == null || !(numeProdusValue.matches(".*[a-zA-Z].*"))) {
                showAlert(Alert.AlertType.ERROR, validateMessage("nume"),  "Trebuie sa contina cel putin o litera");
                productNameInput.setText("");
                return false;
            }
            if (pretValue <= 0 || pretValue > 10000) {
                showAlert(Alert.AlertType.ERROR, validateMessage("pret"),  "Eroare");
                pricePanel.setText("");
                return false;
            } else if (volumValue <= 0 || volumValue > 100) {
                showAlert(Alert.AlertType.ERROR, validateMessage("volum"),  "Eroare");
                volumPanel.setText("");
                return false;

            } else if (Objects.equals(produsNaturalValue, "") || produsNaturalValue == null) {
                showAlert(Alert.AlertType.ERROR, validateMessage("produs natural"),  "Eroare");
                return false;

            } else if (Objects.equals(pentruCopiiValue, "") || pentruCopiiValue == null) {
                showAlert(Alert.AlertType.ERROR, validateMessage("produs copii"),  "Eroare");
                return false;

            } else if (procentFructeValue < 0 || procentFructeValue > 100) {
                showAlert(Alert.AlertType.ERROR, validateMessage("procent fructe"),  "procentul poate fi max 100");
                fructePanel.setText("");
                return false;

            } else if (procentAdaosZaharValue < 0 || procentAdaosZaharValue > 100) {
                showAlert(Alert.AlertType.ERROR, validateMessage("procent adaos zahar"),  "procentul poate fi max 100");
                zaharuriPanel.setText("");
                return false;

            } else if (isValidPercentage(caloriiValue)) {
                showAlert(Alert.AlertType.ERROR, validateMessage("produs calorii"),  "Eroare");
                caloriiPanel.setText("");
                return false;

            } else if (isValidPercentage(carbohidratiValue)) {
                showAlert(Alert.AlertType.ERROR, validateMessage("carbohidrati"),  "Eroare");
                carbsPanel.setText("");
                return false;

            } else if (isValidPercentage(proteineValue)) {
                showAlert(Alert.AlertType.ERROR, validateMessage("proteine"),  "Eroare");
                proteinPanel.setText("");
                return false;

            } else if (isValidPercentage(grasimiValue)) {
                showAlert(Alert.AlertType.ERROR, validateMessage("grasimi"),  "Eroare");
                grasimiPanel.setText("");
                return false;
            } else if (additionalInformationValue == null || additionalInformationValue.isEmpty()) {
                additionalInformationValue = "Nicio informație adițională.";
            }
        return true;
        }
            else return false;
    }

    private boolean isValidPercentage(double value) {
        return !(value > 0) || !(value <= 100);
    }

    public String validateMessage(String param)
    {
        return "Ati introdus " + param + " gresit!, \nre-introduceti va rog";
    }

    String numeProdusValue;
    double pretValue;
    double volumValue;
    Boolean produsNaturalValue;
    Boolean pentruCopiiValue;
    double procentFructeValue;
    double procentAdaosZaharValue;
    double caloriiValue;
    double carbohidratiValue;
    double proteineValue;
    double grasimiValue;
    String additionalInformationValue;

    public boolean fieldValues1() {
        try {
            pretValue = Double.parseDouble(pricePanel.getInput());
            volumValue = Double.parseDouble(volumPanel.getInput());
        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.ERROR, "Eroare", "Nu puteți introduce caractere în casetele pentru numere: " + ex.getMessage());
            return false;
        }

        numeProdusValue = productNameInput.getText();
        produsNaturalValue = naturalRadio.isChecked();
        pentruCopiiValue = copiiRadio.isChecked();

        if (selectedImageFile != null) {
            try (FileInputStream fis = new FileInputStream(selectedImageFile)) {
                imageBytesValue = fis.readAllBytes();
            } catch (IOException ioException) {
                showAlert(Alert.AlertType.ERROR, "Eroare", "Eroare la citirea fișierului imagine: " + ioException.getMessage());
                return false;
            }
        }

        if (selectedImageFile == null && imageBytesValue == null) {
            showAlert(Alert.AlertType.INFORMATION, "Atentie", "Imaginea nu a fost schimbată!");
        }

        return true;
    }

    public boolean fieldValues2()
    {
        try{
        procentAdaosZaharValue = Double.parseDouble(zaharuriPanel.getInput());
        caloriiValue = Double.parseDouble(caloriiPanel.getInput());
        carbohidratiValue = Double.parseDouble(carbsPanel.getInput());
        proteineValue = Double.parseDouble(proteinPanel.getInput());
        grasimiValue = Double.parseDouble(grasimiPanel.getInput());
        procentFructeValue = Double.parseDouble(fructePanel.getInput());
        additionalInformationValue = additionalInfo.getText();
        }catch(NumberFormatException ex)
        {
            showAlert(Alert.AlertType.ERROR, "Nu puteți introduce caractere în casetele pentru numere ",  ex.getMessage());
            return false;

        }
        return true;

    }

    public void setProductName(String name) {
        productNameInput.setText(name);
    }

    public void setPrice(double price) {
        if (price == -1) {
            pricePanel.setText("");
        } else {
            pricePanel.setText(String.valueOf(price));
        }
    }

    public void setVolume(double volume) {
        if (volume == -1) {
            volumPanel.setText("");
        } else {
            volumPanel.setText(String.valueOf(volume));
        }
    }

    public void setCalories(double calories) {
        if (calories == -1) {
            caloriiPanel.setText("");
        } else {
            caloriiPanel.setText(String.valueOf(calories));
        }
    }

    public void setCarbs(double carbs) {
        if (carbs == -1) {
            carbsPanel.setText("");
        } else {
            carbsPanel.setText(String.valueOf(carbs));
        }
    }

    public void setProteins(double proteine) {
        if (proteine == -1) {
            proteinPanel.setText("");
        } else {
            proteinPanel.setText(String.valueOf(proteine));
        }
    }

    public void setSugars(double sugar) {
         if (sugar == -1) {
            zaharuriPanel.setText("");
        } else {
            zaharuriPanel.setText(String.valueOf(sugar));
        }
    }

    public void setFats(double fat) {
        if (fat == -1) {
            grasimiPanel.setText("");
        } else {
            grasimiPanel.setText(String.valueOf(fat));
        }
    }

    public void setFruits(double fruit) {
        if (fruit == -1) {
            fructePanel.setText("");
        } else {
            fructePanel.setText(String.valueOf(fruit));
        }
    }

    public void setAdditionalInfo(String info) {
        additionalInfo.setText(info);
    }

    public void setImage(byte[] imageBytes) {
        if (imageBytes == null) {
            imagePickerBtn.setText("Alege imagine");
            imagePickerBtn.setGraphic(null);
        } else {
            javafx.scene.image.Image image = new javafx.scene.image.Image(new ByteArrayInputStream(imageBytes));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(285);
            imageView.setFitHeight(285);
            imageView.setPreserveRatio(true);

            imagePickerBtn.setText("");
            imagePickerBtn.setGraphic(imageView);
        }
    }

    public void setPickerImage()
    {
        javafx.scene.image.Image placeholderImage = new javafx.scene.image.Image("file:src/main/resources/images/placeholder-image.png");
        ImageView imageView = new ImageView(placeholderImage);
        imageView.setFitWidth(285);
        imageView.setFitHeight(285);
        imageView.setPreserveRatio(true);
        imagePickerBtn.setGraphic(imageView);
        imagePickerBtn.setText("");

    }

    public void resetInputs() {
        copiiRadio.setChecked(null);
        naturalRadio.setChecked(null);

        setProductName("");
        setPrice(-1);
        setImage(null);
        selectedImageFile = null;
        imageBytesValue = null;
        setPickerImage();
        setVolume(-1);
        setAdditionalInfo("");
        setCarbs(-1);
        setProteins(-1);
        setFats(-1);
        setSugars(-1);
        setCalories(-1);
        setFruits(-1);
    }
}
