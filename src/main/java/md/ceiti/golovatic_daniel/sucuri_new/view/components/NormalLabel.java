package md.ceiti.golovatic_daniel.sucuri_new.view.components;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class NormalLabel extends Label {

    public NormalLabel(String text) {
        super(text);
        setTextFill(Color.web("#2D67A7"));
        setFont(Font.font("OpenSans", 25));
    }
}
