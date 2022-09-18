module com.asstmngm.assetmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires java.sql;
    requires mysql.connector.java;


    opens com.asstmngm.assetmanagement to javafx.fxml;
    exports com.asstmngm.assetmanagement;
}