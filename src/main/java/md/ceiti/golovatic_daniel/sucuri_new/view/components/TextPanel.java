package md.ceiti.golovatic_daniel.sucuri_new.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class TextPanel extends HBox {
    private TextInput input;

    public TextPanel(String labelText, String unit) {
        super(5);
        this.setPadding(new Insets(5));

        setAlignment(Pos.CENTER_LEFT);
        NormalLabel label = new NormalLabel(labelText + " ");
        input = new TextInput("", 100, 5);
        input.setPrefWidth(100);

        this.getChildren().addAll(label, input);

        if (!unit.isEmpty()) {
            NormalLabel unitLabel = new NormalLabel(unit);
            this.getChildren().add(unitLabel);
        }
    }

    public String getInput() {
        return input.getText();
    }

    public void setText(String text) {
        this.input.setText(text);
    }
}
