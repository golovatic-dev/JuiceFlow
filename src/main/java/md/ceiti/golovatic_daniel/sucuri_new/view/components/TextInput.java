package md.ceiti.golovatic_daniel.sucuri_new.view.components;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextInput extends TextField {
    private String placeholder;
    private int borderRadius;

    public TextInput(String placeholder, double width, int borderRadius) {
        this.placeholder = placeholder;
        this.borderRadius = borderRadius;

        setPrefWidth(width);
        setPrefHeight(50);
        setFont(Font.font("SansSerif", 15));
        setBackground(new Background(new BackgroundFill(Color.web("#FBFBFB"), new CornerRadii(borderRadius), null)));
        setStyle("-fx-text-fill: #AEAEAE; -fx-padding: 10 15 10 15; -fx-border-color: #AEAEAE; -fx-border-radius: " + borderRadius + ";");
        setText(placeholder);

        focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (getText().equals(placeholder)) {
                    setText("");
                    setStyle(getStyle().replace("-fx-text-fill: #AEAEAE;", "-fx-text-fill: black;"));
                }
            } else {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setStyle(getStyle().replace("-fx-text-fill: black;", "-fx-text-fill: #AEAEAE;"));
                }
            }
        });

        addEventHandler(MouseEvent.MOUSE_ENTERED, e -> setStyle(getStyle() + "-fx-border-color: black;"));
        addEventHandler(MouseEvent.MOUSE_EXITED, e -> setStyle(getStyle().replace("-fx-border-color: black;", "-fx-border-color: #AEAEAE;")));
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (getText().equals(placeholder)) {
            super.replaceText(0, placeholder.length(), text);
        } else {
            super.replaceText(start, end, text);
        }
    }
}
