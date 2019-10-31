package com.sda.practicalproject.phonebook.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorText;

    @FXML
    private Hyperlink registerUser;

    @FXML
    private void initialize() {
        System.out.println("Controller initialized!");
    }

    @FXML
    private void handleLogin() {
       if(QueryDAO.isValidUser(usernameText.getText(), passwordText.getText())){
           try {
               Parent root = FXMLLoader.load((getClass().getClassLoader().getResource("fxml/phonebook_registry.fxml")));
               Stage stage = (Stage) loginButton.getScene().getWindow();
               stage.setScene(new Scene(root));

           } catch (IOException io) {
               io.printStackTrace();
           }
       } else {
            errorText.setText("Invalid Username or Password!");
       }
    }

    @FXML
    private void goToRegister(){
        try {
            Parent root = FXMLLoader.load((getClass().getClassLoader().getResource("fxml/register_user.fxml")));
            Stage stage = (Stage) registerUser.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException io) {
            io.printStackTrace();
        }

    }

}
