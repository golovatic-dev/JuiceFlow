package md.ceiti.golovatic_daniel.sucuri_new.view.components;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lombok.Setter;

public class ButtonModel extends Button {
    @Setter
    private boolean over;
    private Color color;
    @Setter
    private Color colorOver;
    private Color colorClick;
    @Setter
    private Color borderColor;
    private int radius;

    public ButtonModel() {
        setColor(Color.rgb(22, 85, 157));
        colorOver = Color.rgb(20, 74, 136);
        colorClick = Color.rgb(9, 32, 58);
        borderColor = Color.rgb(22, 85, 157);
        radius = 10;

        setTextFill(Color.WHITE);
        setFont(Font.font(getFont().getName(), 20));
        setPrefHeight(50);
        updateBackground(color, radius);


        setOnMouseEntered(this::handleMouseEntered);
        setOnMouseExited(this::handleMouseExited);
        setOnMousePressed(this::handleMousePressed);
        setOnMouseReleased(this::handleMouseReleased);
    }

    public boolean isOver() {
        return over;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        updateBackground(color, radius);
    }

    public Color getColorOver() {
        return colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        updateBackground(color, radius);
    }

    private void updateBackground(Color backgroundColor, int cornerRadius) {
        setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(cornerRadius), null)));
    }

    private void handleMouseEntered(MouseEvent e) {
        over = true;
        updateBackground(colorOver, radius);
        setFont(Font.font(getFont().getName(), FontWeight.BOLD, 20));
        setCursor(Cursor.HAND);
    }

    private void handleMouseExited(MouseEvent e) {
        over = false;
        updateBackground(color, radius);
        setFont(Font.font(getFont().getName(), FontWeight.NORMAL, 20));
    }

    private void handleMousePressed(MouseEvent e) {
        updateBackground(colorClick, radius);
    }

    private void handleMouseReleased(MouseEvent e) {
        if (over) {
            updateBackground(colorOver, radius);
        } else {
            updateBackground(color, radius);
        }
    }

    public void setButtonProperties(String text, Color bgColor, Color borderColor) {
        setText(text);
        setColor(bgColor);
        setBorderColor(borderColor);
    }

}
