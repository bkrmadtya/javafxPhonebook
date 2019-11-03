package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.contact.Contact;
import com.sda.practicalproject.phonebook.database.contact.ContactDAO;
import com.sda.practicalproject.phonebook.database.user.User;
import com.sda.practicalproject.phonebook.utils.Navigate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class UpdateContactController {

    @FXML
    private TextField nameText;

    @FXML
    private TextField addressText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField phoneText;

    @FXML
    private Button updateButton;

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
        Contact updatedContact = new Contact(nameText.getText(), addressText.getText(), emailText.getText(), Long.parseLong(phoneText.getText()));
        updatedContact.setCreatorId(creator.getUserId());

        ContactDAO.updateContact(updatedContact, id);

        goToContactList();
    }

    @FXML
    private void goToContactList() {
        Navigate.goTo(updateButton, "/fxml/contact_list.fxml");
    }
}
