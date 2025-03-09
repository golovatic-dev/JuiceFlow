package md.ceiti.golovatic_daniel.sucuri_new.view.components;

import javafx.geometry.Pos;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

public class RadioPanel extends HBox {

    @Getter
    @Setter
    private CustomRadio yesButton;
    private CustomRadio noButton;

    private ToggleGroup group;

    public RadioPanel(String labelText) {
        this.setSpacing(5);
        setAlignment(Pos.CENTER_LEFT);

        this.getChildren().add(new NormalLabel(labelText + ": "));

        yesButton = new CustomRadio("Da", "#37FF00");
        noButton = new CustomRadio("Nu", "#FF0000");

        group = new ToggleGroup();
        yesButton.setToggleGroup(group);
        noButton.setToggleGroup(group);

        this.getChildren().addAll(yesButton, noButton);
    }

    public boolean isChecked() {
        return yesButton.isSelected();
    }

    public void setChecked(Boolean state) {
        if (state == null) {
            group.selectToggle(null);
        } else if (state) {
            yesButton.setSelected(true);
        } else {
            noButton.setSelected(true);
        }
    }

}
