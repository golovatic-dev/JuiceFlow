    package md.ceiti.golovatic_daniel.sucuri_new.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import md.ceiti.golovatic_daniel.sucuri_new.view.View;

import java.net.URL;

    public class RunApplication extends Application {
    @Override
    public void start(Stage stage)  {
        View view = new View();

        URL l = App.class.getResource("/images/pepsi.png");
        assert l != null;
        stage.getIcons().add(new Image(l.toExternalForm()));
        stage.setTitle("Gestionare bază de date sucuri");

        stage.setScene(new Scene(view.getRoot(), 1024, 768));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}