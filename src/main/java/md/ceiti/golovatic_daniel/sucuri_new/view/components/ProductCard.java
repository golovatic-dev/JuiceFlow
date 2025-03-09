package md.ceiti.golovatic_daniel.sucuri_new.view.components;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import md.ceiti.golovatic_daniel.sucuri_new.controller.*;

import java.io.ByteArrayInputStream;

public class ProductCard extends StackPane {
    private final ImageView imageView;
    private final Label productTitle;
    private final int IMAGE_WIDTH = 185;
    private final int IMAGE_HEIGHT = 185;
    private final int CARD_WIDTH = 215;
    private final int CARD_HEIGHT = 245;

    public ProductCard(byte[] imageData, String productName) {
        super();

        if (imageData == null || imageData.length == 0) {
            imageData = ProductsController.getDefaultImage();
        }

        Image image = new Image(new ByteArrayInputStream(imageData), IMAGE_WIDTH, IMAGE_HEIGHT, true, true);
        imageView = new ImageView(image);
        imageView.setFitWidth(IMAGE_WIDTH);
        imageView.setFitHeight(IMAGE_HEIGHT);

        productTitle = new Label(productName.toUpperCase());
        productTitle.setFont(Font.font("OpenSans", 20));
        productTitle.setTextAlignment(TextAlignment.CENTER);
        productTitle.setWrapText(true);

        BorderPane contentPane = new BorderPane();
        contentPane.setCenter(imageView);
        contentPane.setBottom(productTitle);

        contentPane.setPrefSize(CARD_WIDTH, CARD_HEIGHT);
        contentPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        this.getChildren().add(contentPane);
        this.setPrefSize(CARD_WIDTH, CARD_HEIGHT);

        this.setOnMouseEntered(e -> applyHoverStyle());
        this.setOnMouseExited(e -> resetStyle());
    }

    public String getName() {
        return productTitle.getText();
    }

    private void applyHoverStyle() {
        this.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #eeeeee; -fx-border-width: 1;");
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.LIGHTGRAY);
        setCursor(Cursor.HAND);
        this.setEffect(shadow);
    }

    private void resetStyle() {
        this.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        this.setEffect(null);
    }
}
