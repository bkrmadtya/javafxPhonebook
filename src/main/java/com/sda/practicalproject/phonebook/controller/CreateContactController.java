package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.contact.Contact;
import com.sda.practicalproject.phonebook.database.contact.ContactDAO;
import com.sda.practicalproject.phonebook.database.user.User;
import com.sda.practicalproject.phonebook.services.LoggedInUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class CreateContactController {

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
        Contact contact = new Contact(nameText.getText(), addressText.getText(), emailText.getText(), Long.parseLong(phoneText.getText()));
        contact.setCreatorId(creator.getUserId());
        ContactDAO.createContact(contact);
        goToContactList();
    }
    @FXML
    private void goToContactList() {
        Navigate.goTo(createButton, "/fxml/contact_list.fxml");
    }

}
