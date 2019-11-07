package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.user.User;
import com.sda.practicalproject.phonebook.database.user.UserDAO;
import com.sda.practicalproject.phonebook.utils.Navigate;
import com.sda.practicalproject.phonebook.utils.OriginOfAbout;
import com.sda.practicalproject.phonebook.utils.QueryDAO;
import com.sda.practicalproject.phonebook.utils.ValidateInput;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserRegisterController {

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private PasswordField passwordText2;

    @FXML
    private Button registerButton;

    @FXML
    private Label errorText;

    @FXML
    private void handleUserRegister() {
        if(!ValidateInput.isNumber(usernameText.getText())){
            if(ValidateInput.isNotEmpty(usernameText, passwordText, passwordText2)){
                createUser();
            } else{
                errorText.setText("Input fields cannot be empty!");
            }
        } else {
            errorText.setText("Username should contain characters!");
        }
    }

    private void createUser(){
        if (QueryDAO.usernameIsUnique(usernameText.getText())) {
            if(passwordText.getText().equals(passwordText2.getText())){
                User user = new User(usernameText.getText(), passwordText.getText());
                UserDAO.createUser(user);

                goToLogin();
            } else{
                errorText.setText("Password mismatched!");
            }
        } else {
            errorText.setText("Username already taken!");
        }
    }

    @FXML
    private void goToLogin() {
        Navigate.goTo(registerButton, "/fxml/login.fxml");
    }

    @FXML
    private void goToAbout() {
        OriginOfAbout.setOrigin("/fxml/register_user.fxml");
        Navigate.goTo(registerButton, "/fxml/aboutme.fxml");
    }

    @FXML
    private void emptyErrorText() {
        ValidateInput.resetError(errorText);
    }
}