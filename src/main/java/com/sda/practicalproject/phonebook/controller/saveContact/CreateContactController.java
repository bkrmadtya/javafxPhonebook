package com.sda.practicalproject.phonebook.controller.saveContact;

import com.sda.practicalproject.phonebook.database.contact.Contact;
import com.sda.practicalproject.phonebook.database.contact.ContactDAO;
import com.sda.practicalproject.phonebook.database.user.User;
import com.sda.practicalproject.phonebook.utils.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateContactController {

    @FXML
    TextField nameText;

    @FXML
    TextField addressText;

    @FXML
    TextField emailText;

    @FXML
    TextField phoneText;

    @FXML
    Button createButton;

    @FXML
    Hyperlink cancelButton;

    @FXML
    Label errorText;

    User creator;

    @FXML
    private void initialize() {
        this.creator = LoggedInUser.getUser();
    }

    @FXML
    private void handleCreateContact() {
        if (ValidateInput.isNotEmpty(nameText, addressText, emailText, phoneText)) {
            if (ValidateInput.isNumber(phoneText.getText())) {
                createContact();
            } else {
                errorText.setText("Phone number is invalid format!");
            }
        } else {
            errorText.setText("Input fields cannot be empty!");
        }
    }

    private void createContact() {
        Long number = Long.parseLong(phoneText.getText());
        if (QueryDAO.numberIsUnique(number)) {
            Contact contact = new Contact(nameText.getText(), addressText.getText(), emailText.getText(), number);
            contact.setCreatorId(creator.getUserId());

            ContactDAO.createContact(contact);

            goToContactList();
        } else {
            errorText.setText("Phone number should be unique!");
        }
    }

    @FXML
    private void goToContactList() {
        OriginOfAbout.setOrigin("/fxml/create_contact.fxml");
        Navigate.goTo(createButton, "/fxml/contact_list.fxml");
    }

    @FXML
    private void emptyErrorText() {
        ValidateInput.resetError(errorText);
    }

}
