package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.user.User;
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
        if (usernameText.getText().isEmpty() || passwordText.getText().isEmpty()) {
            errorText.setText("Missing credentials!");
        } else {
            login();
        }
    }

    private void login() {
        User user = QueryDAO.getUserByName(usernameText.getText());
        if (isValidUser(user, usernameText.getText(), passwordText.getText())) {

            // go to phoneRegistry page with logged in user,
            // so that newly created registry will have creator id too
            Navigate.withParameter(loader -> {
                PhoneRegistryController phoneRegistryController = loader.getController();
                phoneRegistryController.setUser(user);
                return phoneRegistryController;
            }, loginButton, "/fxml/phonebook_registry.fxml");

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

}
