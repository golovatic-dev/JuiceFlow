package md.ceiti.golovatic_daniel.sucuri_new.view.components;

import javafx.scene.control.RadioButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomRadio extends RadioButton {

    private Color backgroundColor;
    private int cornerRadius = 15;

    public CustomRadio(String text, String backgroundColorHex) {
        super(text);

        this.getStyleClass().remove("radio-button");
        this.getStyleClass().add("toggle-button");

        this.backgroundColor = Color.web(backgroundColorHex);

        setFont(Font.font("OpenSans", FontWeight.NORMAL, 25));
        setTextFill(Color.BLACK);
        setBackground(null);
        setStyle("-fx-padding: 5px 15px;");

        selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                setFont(Font.font("OpenSans", FontWeight.BOLD, 25));
                setTextFill(Color.WHITE);
                setBackground(new Background(new BackgroundFill(this.backgroundColor, new CornerRadii(cornerRadius), null)));
            } else {
                setFont(Font.font("OpenSans", FontWeight.NORMAL, 25));
                setTextFill(Color.BLACK);
                setBackground(null);
            }
        });
    }
}
