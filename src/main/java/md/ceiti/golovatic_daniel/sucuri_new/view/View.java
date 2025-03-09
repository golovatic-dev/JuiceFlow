package md.ceiti.golovatic_daniel.sucuri_new.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import lombok.Getter;
import md.ceiti.golovatic_daniel.sucuri_new.view.panels.*;

import java.util.HashMap;
import java.util.Map;

public class View extends VBox {

    private ListaProdusePanel listaProdusePanel;
    private HomePanel homePanel;
    @Getter
    private ProdusPanel produsPanel;

    private HBox menuBar;
    private Label[] menu;
    private Map<String, Pane> panels;

    public static final String HOME_OPTION = "Acasa";
    public static final String PRODUS_OPTION = "Produs";
    public static final String LISTA_PRODUSE_OPTION = "Lista produse";

    public View() {
        menu = new Label[3];
        menuBar = new HBox();
        menuBar.setAlignment(Pos.BASELINE_LEFT);

        menu[0] = createMenuLabel(HOME_OPTION);
        menu[1] = createMenuLabel(PRODUS_OPTION);
        menu[2] = createMenuLabel(LISTA_PRODUSE_OPTION);

        menuBar.getChildren().addAll(menu);

        StackPane mainPane = new StackPane();
        panels = new HashMap<>();

        homePanel = new HomePanel();
        panels.put(HOME_OPTION, homePanel);

        produsPanel = new ProdusPanel();
        panels.put(PRODUS_OPTION, produsPanel);

        listaProdusePanel = new ListaProdusePanel();
        listaProdusePanel.setParentView(this);
        panels.put(LISTA_PRODUSE_OPTION, listaProdusePanel);

        for (String key : panels.keySet()) {
            mainPane.getChildren().add(panels.get(key));
            panels.get(key).setVisible(false);
        }

        panels.get(HOME_OPTION).setVisible(true);
        menuActionStyles(menu, menu[0]);

        VBox.setVgrow(mainPane, Priority.ALWAYS);
        this.getChildren().addAll(menuBar, mainPane);
    }

    public Pane getRoot()
    {
        return this;
    }

    private Label createMenuLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-background-color: white; -fx-text-fill: #2D67A7; -fx-padding: 5 15; -fx-font-family: SansSerif; -fx-font-size: 14");

        label.setOnMouseClicked(e -> {
            switch (text) {
                case HOME_OPTION -> {
                    menuActionStyles(menu, label);
                    homePanel.refreshPanel();
                    showPanel(HOME_OPTION);
                }
                case PRODUS_OPTION -> {
                    menuActionStyles(menu, label);
                    showPanel(PRODUS_OPTION);
                }
                case LISTA_PRODUSE_OPTION -> {
                    menuActionStyles(menu, label);
                    listaProdusePanel.refreshProducts();
                    showPanel(LISTA_PRODUSE_OPTION);
                }
            }
        });

        label.setOnMouseEntered(e -> label.setCursor(javafx.scene.Cursor.HAND));
        label.setOnMouseExited(e -> label.setCursor(javafx.scene.Cursor.DEFAULT));

        return label;
    }

    private void setSelectedMenu(Label menu) {
        menu.setStyle("-fx-background-color: #2D67A7; -fx-text-fill: white; -fx-padding: 5 30; -fx-font-weight: bold; -fx-font-size: 16; -fx-font-family: SansSerif");
    }

    private void setUnselectedMenu(Label menu) {
        menu.setStyle("-fx-background-color: white; -fx-text-fill: #2D67A7; -fx-padding: 5 15;-fx-font-size: 14; -fx-font-family: SansSerif");
    }

    public void menuActionStyles(Label[] menus, Label selectedMenu) {
        setSelectedMenu(selectedMenu);
        for (Label item : menus) {
            if (!item.equals(selectedMenu)) setUnselectedMenu(item);
        }
    }

    public void handleProdusMenu() {
        showPanel(PRODUS_OPTION);
    }

    public void showPanel(String panelName) {
        for (Pane pane : panels.values()) {
            pane.setVisible(false);
        }
        panels.get(panelName).setVisible(true);
    }

}
