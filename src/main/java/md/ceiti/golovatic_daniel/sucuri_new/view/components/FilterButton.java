package md.ceiti.golovatic_daniel.sucuri_new.view.components;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FilterButton extends Button {

    public FilterButton(String text) {
        super(text);

        setFont(Font.font("OpenSans", 20));
        setTextFill(Color.web("#A7A7A7"));
        setStyle("-fx-background-color: transparent; -fx-alignment: center;");
        setCursor(Cursor.HAND);

        addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            setStyle("-fx-background-color: #f4f4f4; -fx-alignment: center;");
            setEffect(new DropShadow(5, Color.GRAY));
        });

        addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            setStyle("-fx-background-color: transparent; -fx-alignment: center;");
            setEffect(null);
        });
    }
}
