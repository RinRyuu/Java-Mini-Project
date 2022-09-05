module com.asstmngm.assetmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.asstmngm.assetmanagement to javafx.fxml;
    exports com.asstmngm.assetmanagement;
}