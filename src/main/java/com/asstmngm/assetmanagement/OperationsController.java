package com.asstmngm.assetmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class OperationsController {

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
    private RadioButton createDBButton;
    @FXML
    private RadioButton useDBButton;

    // Text Fields
    @FXML
    private TextField dbNameTextField;
    @FXML
    private TextField tabelTextField;

    // Buttons
    @FXML
    private Button okButton;
    @FXML
    private Button exitButton;

    @FXML
    private void onDBSelected(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
