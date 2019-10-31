package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.registry.Registry;
import com.sda.practicalproject.phonebook.database.registry.RegistryDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateRegistryController {

    @FXML
    private TextField nameText;

    @FXML
    private TextField addressText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField phoneText;

    @FXML
    private Button createButton;

    @FXML
    private Hyperlink cancelButton;

    @FXML
    private void createRegistry() {
        Registry registry = new Registry(nameText.getText(), addressText.getText(), emailText.getText(), Long.parseLong(phoneText.getText()));
        RegistryDAO.createRegistry(registry);

        nameText.setText("");
        addressText.setText("");
        emailText.setText("");
        phoneText.setText("");

        goToRegister();
    }

    @FXML
    private void goToRegister() {
        try {
            Parent root = FXMLLoader.load((getClass().getResource("/fxml/phonebook_registry.fxml")));
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}
