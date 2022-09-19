package com.asstmngm.assetmanagement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class OperationsController {

    public ToggleGroup dbSelection;
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Labels
    @FXML
    private Label dbNameLabel;
    @FXML
    private Label tabelLabel;

    // Radio Buttons
    @FXML
    private RadioButton createDBButton, useDBButton;

    // Text Fields
    @FXML
    private TextField dbNameTextField;
    @FXML
    private TextField tableTextField;

    // Buttons
    @FXML
    private Button okButton;
    @FXML
    private Button exitButton;

    @FXML
    private void getDB() {

    }

    @FXML
    private void createDB() {
        String dbName = dbNameTextField.getText();
        String tableName = tableTextField.getText();
        try {
            if(dbName.isEmpty() || tableName.isEmpty()) {
                emptyFieldsAlert();
            } else {
                String newDB = "CREATE SCHEMA ?" ;
                String newTable = "'CREATE TABLE ?. ? (Sr INT NOT NULL AUTO_INCREMENT, ID VARCHAR(45) NOT NULL, PurchaseDate DATE NOT NULL, Type VARCHAR(45) NOT NULL, Price INT NOT NULL, Status VARCHAR(45) NOT NULL)'";
                DatabaseConnection dbConnection = new DatabaseConnection();
                Connection connection = dbConnection.getConnection();
                PreparedStatement newDBStatement = connection.prepareStatement(newDB);
                PreparedStatement newTableStatement = connection.prepareStatement(newTable);
                newDBStatement.setString(1, dbName);
                newTableStatement.setString(1, dbName);
                newTableStatement.setString(2, tableName);
                newDBStatement.executeUpdate();
                newTableStatement.executeUpdate();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onDBSelected(ActionEvent event) throws IOException {
        String dbName = dbNameTextField.getText();
        String tableName = tableTextField.getText();
        toNextPage(event);
//        if(createDBButton.isSelected()) {
//            System.out.println("Create DB");
////            createDB();
//            toNextPage(event);
//        } else if(useDBButton.isSelected()) {
//            System.out.println("Use DB");
//            getDB();
//        } else {
//            noDBSelectedAlert();
//        }

    }

    private void toNextPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onExitAction(ActionEvent event) {
        Platform.exit();
    }

    // alerts
    private void noDBSelectedAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Empty Fields");
        alert.setContentText("Database and Table name must not be empty!");
        alert.showAndWait();
    }

    private void emptyFieldsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Empty Fields");
        alert.setContentText("Please fill in all the fields to proceed.");
        alert.showAndWait();
    }
}
