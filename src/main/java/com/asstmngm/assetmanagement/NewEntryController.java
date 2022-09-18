package com.asstmngm.assetmanagement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewEntryController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // table fxml

    // text fields
    @FXML
    private TextField srNo;
    @FXML
    private TextField idNo;
    @FXML
    private TextField purchaseDate;
    @FXML
    private TextField type;
    @FXML
    private TextField price;
    @FXML
    private TextField status;

    // buttons
    @FXML
    private Button backButton;
    @FXML
    private Button appendButton;
    @FXML
    private Button exitButton;

    // methods
    @FXML
    private void onExitAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void onBackAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onAppendAction(ActionEvent event) throws IOException{
        System.out.println("DB Is Not Connected!");
    }
}
