package com.asstmngm.assetmanagement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Labels
    @FXML
    private Label loginLabel;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordLabel;

    // Text and Password Fields
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordTextField;

    // Buttons
    @FXML
    private Button loginButton;
    @FXML
    private Button exitButton;

    // Mics
    @FXML
    private Line loginLine;

    // Methods
    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("operations.fxml"));
        root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}