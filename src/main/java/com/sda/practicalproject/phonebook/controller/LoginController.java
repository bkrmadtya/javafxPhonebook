package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.user.User;
import com.sda.practicalproject.phonebook.utils.*;
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

    private User user;

    @FXML
    private void initialize() {
        System.out.println("Controller initialized!");
    }

    @FXML
    private void handleLogin() {
        if (ValidateInput.isNotEmpty(usernameText, passwordText)) {
            login();
        } else {
            errorText.setText("The fields cannot be empty!");
        }
    }

    private void login() {
        User user = QueryDAO.getUserByName(usernameText.getText());
        if (isValidUser(user, usernameText.getText(), passwordText.getText())) {
            LoggedInUser.setUser(user);

            Navigate.goTo(loginButton, "/fxml/contact_list.fxml");

        } else {
            errorText.setText("Invalid Username or Password!");
        }
    }

    public static boolean isValidUser(User user, String username, String password) {
        boolean result = false;

        if (user != null) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                result = true;
            }
        }
        return result;
    }

    @FXML
    private void goToRegister() {
        Navigate.goTo(loginButton, "/fxml/register_user.fxml");
    }

    @FXML
    private void goToAbout() {
        OriginOfAbout.setOrigin("/fxml/login.fxml");
        Navigate.goTo(loginButton, "/fxml/aboutme.fxml");
    }

    @FXML
    private void emptyErrorText(){
        ValidateInput.resetError(errorText);
    }

}
