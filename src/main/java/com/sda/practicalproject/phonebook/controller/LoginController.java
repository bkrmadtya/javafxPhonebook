package com.sda.practicalproject.phonebook.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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
        if(usernameText.getText().isEmpty() || passwordText.getText().isEmpty()){
            errorText.setText("Missing credentials!");
        } else {
            login();
        }
    }

    private void login(){
        if (QueryDAO.isValidUser(usernameText.getText(), passwordText.getText())) {
            Navigate.goTo(loginButton, "/fxml/phonebook_registry.fxml");
        } else {
            errorText.setText("Invalid Username or Password!");
        }
    }

    @FXML
    private void goToRegister() {
        Navigate.goTo(loginButton, "/fxml/register_user.fxml");
    }

}
