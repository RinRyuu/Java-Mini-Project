package com.asstmngm.assetmanagement;

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
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        byte[] digest = md.digest();
//        StringBuilder sb = new StringBuilder();
//        BigInteger bigInt = new BigInteger(1, digest);
//        passWord = bigInt.toString(16);
//        uName = userNameTextField.getText();

        passWord = passwordTextField.getText();
        uName = userNameTextField.getText();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try {
            if(uName.isEmpty() || passWord.isEmpty()) {
                failedLogin();
            } else {
                String query = "SELECT * FROM user_auth WHERE userName = ? AND password = ?";
                String forUName = "SELECT userName FROM user_auth WHERE userName = ?";
                String forPass = "SELECT password FROM user_auth WHERE password = ?";
                DatabaseConnection databaseConnection = new DatabaseConnection();
                Connection connection = databaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                PreparedStatement preparedSForUName = connection.prepareStatement(forUName);
                PreparedStatement preparedSForPass = connection.prepareStatement(forPass);
                preparedStatement.setString(1, uName);
                preparedStatement.setString(2, passWord);
                preparedSForUName.setString(1, uName);
                preparedSForPass.setString(1, passWord);
                ResultSet resultSet = preparedStatement.executeQuery();
                ResultSet resultSetForUName = preparedSForUName.executeQuery();
                ResultSet resultSetForPass = preparedSForPass.executeQuery();

                if(!resultSet.next()) {
                    if(!resultSetForUName.next()) {
                        sendToDB();
                        loadOperations(event);
                    } else {
                        wrongPass();
                    }
                } else if(resultSet.getString("userName").equals(uName) && resultSet.getString("password").equals(passWord)) {
                    loadOperations(event);
                } else {
                    wrongCreds();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        } finally {
            try {
                connectDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void failedLogin() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Failed!");
        alert.setHeaderText("Login Failed!");
        alert.setContentText("Username And Password Must Not Be Empty!");
        alert.showAndWait();
    }

    private void wrongCreds() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong Credentials!");
        alert.setContentText("Username Or Password Is Incorrect!");
        alert.showAndWait();
    }

    private void wrongPass() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid password");
        alert.setContentText("The Entered Password Is Incorrect!");
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