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

public class UpdateRegistryController {

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

    private Long id;

    @FXML
    private void initialize(){
//        this.id = new PhoneRegistryController().getSelectedRegistry();
//        System.out.println(id);
    }

    public void fillData(String name , String address , String email, Long phoneNumber,Long id){
        this.nameText.setText(name);
        this.addressText.setText(address);
        this.emailText.setText(email);
        this.phoneText.setText(String.valueOf(phoneNumber));
        this.id = id;
    }

    @FXML
    private void updateRegistry() {
        Registry updatedRegistry = new Registry(nameText.getText(), addressText.getText(), emailText.getText(), Long.parseLong(phoneText.getText()));
        RegistryDAO.updateRegistry(updatedRegistry, id);

        nameText.setText("");
        addressText.setText("");
        emailText.setText("");
        phoneText.setText("");

        goToRegister();
    }

    @FXML
    private void goToRegister() {
        try {
            Parent root = FXMLLoader.load((getClass().getClassLoader().getResource("fxml/phonebook_registry.fxml")));
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}
