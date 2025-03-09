package md.ceiti.golovatic_daniel.sucuri_new.view.components;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomButton extends ButtonModel {

    public CustomButton(String text) {
        super();
        setText(text);

        setColor(Color.WHITE);
        setBorderColor(Color.rgb(22, 85, 157));
        setColorOver(Color.rgb(22, 85, 157));
        setTextFill(Color.rgb(22, 85, 157));
        setFont(Font.font(getFont().getName(), 20));

        setOnMouseEntered(e -> {
            setOver(true);
            setBackgroundColor(getColorOver());
            setTextFill(Color.WHITE);
            setFont(Font.font(getFont().getName(), FontWeight.BOLD, 20));
        });

        setOnMouseExited(e -> {
            setOver(false);
            setBackgroundColor(getColor());
            setTextFill(Color.rgb(22, 85, 157));
            setFont(Font.font(getFont().getName(), FontWeight.NORMAL, 20));
        });

        setOnMousePressed(e -> setBackgroundColor(getColorClick()));

        setOnMouseReleased(e -> {
            if (isOver()) {
                setBackgroundColor(getColorOver());
            } else {
                setBackgroundColor(getColor());
            }
        });
    }

    private void setBackgroundColor(Color color) {
        setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundFill(color, new javafx.scene.layout.CornerRadii(getRadius()), null)
        ));
    }
}
