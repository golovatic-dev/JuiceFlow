module md.ceiti.golovatic_daniel.sucuri_new {
    requires java.naming;
    requires javafx.fxml;
    requires javafx.controls;
    requires jakarta.persistence;
    requires static lombok;
    requires org.hibernate.orm.core;
    requires org.controlsfx.controls;


    exports md.ceiti.golovatic_daniel.sucuri_new.application;
    opens md.ceiti.golovatic_daniel.sucuri_new.application to javafx.fxml;
}