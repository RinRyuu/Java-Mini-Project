package com.asstmngm.assetmanagement;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

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


    String uName;
    String passWord;

    // Methods
    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException, NoSuchAlgorithmException {


        // use messageDigest to hash the password
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        BigInteger bigInt = new BigInteger(1, digest);
        passWord = bigInt.toString(16);
        uName = userNameTextField.getText();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try {
            String query = "SELECT * FROM user_auth WHERE userName = ? AND password = ?";
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, uName);
            preparedStatement.setString(2, passWord);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                loadOperations(event);
            } else {
                if(uName.isEmpty() || passWord.isEmpty()) {
                    failedLogin();
                } else {
                    sendToDB();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private void failedLogin() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Failed!");
        alert.setHeaderText("Login Failed!");
        alert.setContentText("Please check your username and password!");
        alert.showAndWait();
    }

    private void wrongPassword() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Failed!");
        alert.setHeaderText("Login Failed!");
        alert.setContentText("Please check your password!");
        alert.showAndWait();
    }

    private void loadOperations(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Operations.fxml"));
        root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Asset Management System!");
        stage.setScene(scene);
        stage.show();
    }

    private void sendToDB() {
        try {
            String query = "INSERT INTO user_auth (userName, password) VALUES (?, ?)";
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, uName);
            preparedStatement.setString(2, passWord);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    private void onExitAction(ActionEvent event) throws IOException {
        Platform.exit();
    }
}