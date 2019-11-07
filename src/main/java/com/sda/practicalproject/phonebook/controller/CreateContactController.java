package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.contact.Contact;
import com.sda.practicalproject.phonebook.database.contact.ContactDAO;
import com.sda.practicalproject.phonebook.database.user.User;
import com.sda.practicalproject.phonebook.utils.LoggedInUser;
import com.sda.practicalproject.phonebook.utils.Navigate;
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
    private void initialize() {
        this.creator = LoggedInUser.getUser();
        System.out.println("Creator id : " + creator.getUserId());
    }

    @FXML
    private void createRegistry() {
        Long number = Long.parseLong(phoneText.getText());

        if (numberIsUnique(number)) {
            Contact contact = new Contact(nameText.getText(), addressText.getText(), emailText.getText(), number);
            contact.setCreatorId(creator.getUserId());
            ContactDAO.createContact(contact);
            goToContactList();
        } else {
            System.out.println("The number " + number + " is already taken!");
        }


    }

    @FXML
    private void goToContactList() {
        Navigate.goTo(createButton, "/fxml/contact_list.fxml");
    }

    private boolean numberIsUnique(Long number) {
        boolean result = false;

        Contact contact = QueryDAO.getContactByNumber(number);
        if (contact == null) {
            result = true;
        }

        return result;
    }

}
