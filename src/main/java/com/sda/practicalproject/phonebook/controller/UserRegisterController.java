package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.user.User;
import com.sda.practicalproject.phonebook.database.user.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UserRegisterController {

    @FXML
    private TextField usernameText;

    @FXML
    private TextField passwordText;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorText;

    @FXML
    private void handleUserRegister(ActionEvent event) {
        if(QueryDAO.usernameIsUnique(usernameText.getText())){
            User user = new User(usernameText.getText(), passwordText.getText());
            UserDAO.createUser(user);

            try {
                Parent root = FXMLLoader.load((getClass().getClassLoader().getResource("fxml/login.fxml")));
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));

                QueryDAO.getAllRegistry();
            } catch (IOException io) {
                io.printStackTrace();
            }
        } else {
            errorText.setText("Username already taken!");
        }
    }

}
