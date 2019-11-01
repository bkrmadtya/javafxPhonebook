package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.registry.Registry;
import com.sda.practicalproject.phonebook.database.registry.RegistryDAO;
import com.sda.practicalproject.phonebook.database.user.User;
import com.sda.practicalproject.phonebook.services.LoggedInUser;
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
    private void initialize(){
        this.creator = LoggedInUser.getUser();
        System.out.println("Creator id : " + creator.getUserId());
    }

    @FXML
    private void createRegistry() {
        Registry registry = new Registry(nameText.getText(), addressText.getText(), emailText.getText(), Long.parseLong(phoneText.getText()));
        registry.setCreatorId(creator.getUserId());
        RegistryDAO.createRegistry(registry);
        goToRegister();
    }
    @FXML
    private void goToRegister() {
        Navigate.goTo(createButton, "/fxml/register_user.fxml");
    }

}
