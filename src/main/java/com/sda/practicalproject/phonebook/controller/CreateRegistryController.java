package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.registry.Registry;
import com.sda.practicalproject.phonebook.database.registry.RegistryDAO;
import com.sda.practicalproject.phonebook.database.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

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

    private User creator;

    @FXML
    private void createRegistry() {
        Registry registry = new Registry(nameText.getText(), addressText.getText(), emailText.getText(), Long.parseLong(phoneText.getText()));
        registry.setCreatorId(creator.getUserId());
        RegistryDAO.createRegistry(registry);
        goToRegister();
    }

    public void setCreatorId(User user){
        System.out.println("Setting creator id : " + user.getUsername());
        this.creator = user;
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
