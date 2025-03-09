package md.ceiti.golovatic_daniel.sucuri_new.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.RadioButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FilterRadio extends RadioButton {

    private final StackPane toggleSwitch;

    public FilterRadio(String text) {
        super(text);
        getStyleClass().remove("radio-button");
        getStyleClass().add("toggle-button");
        setBackground(Background.EMPTY);
        setBorder(Border.EMPTY);
        setFont(Font.font("OpenSans", FontWeight.NORMAL, 20));
        setTextFill(Color.web("#A7A7A7"));
        setCursor(Cursor.HAND);
        toggleSwitch = new StackPane();
        toggleSwitch.setPrefSize(40, 20);
        toggleSwitch.setMaxSize(40, 20);
        toggleSwitch.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));

        toggleSwitch.setAlignment(Pos.CENTER_LEFT);
        toggleSwitch.setPadding(new Insets(2));

        addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            setStyle("-fx-background-color: #f4f4f4; -fx-alignment: center;");
            setEffect(new DropShadow(5, Color.GRAY));
        });

        addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            setStyle("-fx-background-color: transparent; -fx-alignment: center;");
            setEffect(null);
        });


        HBox container = new HBox(10, toggleSwitch, this);
        container.setAlignment(Pos.CENTER);
        container.setCursor(Cursor.HAND);


        selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            updateTextStyle(isSelected);
        });

        updateTextStyle(isSelected());

    }

    private void updateTextStyle(boolean isSelected) {
        if (isSelected) {
            setFont(Font.font("OpenSans", FontWeight.BOLD, 20));
        } else {
            setFont(Font.font("OpenSans", FontWeight.NORMAL, 20));
        }
    }
}
