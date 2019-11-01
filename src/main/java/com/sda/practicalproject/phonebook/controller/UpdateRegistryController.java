package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.registry.Registry;
import com.sda.practicalproject.phonebook.database.registry.RegistryDAO;
import com.sda.practicalproject.phonebook.database.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

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

    private User creator;

    public void fillData(String name, String address, String email, Long phoneNumber, Long id, User creator) {
        this.nameText.setText(name);
        this.addressText.setText(address);
        this.emailText.setText(email);
        this.phoneText.setText(String.valueOf(phoneNumber));
        this.id = id;
        this.creator = creator;
    }

    @FXML
    private void updateRegistry() {
        Registry updatedRegistry = new Registry(nameText.getText(), addressText.getText(), emailText.getText(), Long.parseLong(phoneText.getText()));
        updatedRegistry.setCreatorId(creator.getUserId());

        RegistryDAO.updateRegistry(updatedRegistry, id);

        nameText.setText("");
        addressText.setText("");
        emailText.setText("");
        phoneText.setText("");

        goToRegister();
    }

    @FXML
    private void goToRegister() {
        Navigate.withParameter(loader -> {
            PhoneRegistryController phoneRegistryController = loader.getController();
            phoneRegistryController.setUser(creator);
            return phoneRegistryController;
        }, createButton, "/fxml/phonebook_registry.fxml");
    }
}
