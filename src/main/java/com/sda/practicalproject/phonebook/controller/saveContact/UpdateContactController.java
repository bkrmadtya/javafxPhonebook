package com.sda.practicalproject.phonebook.controller.saveContact;

import com.sda.practicalproject.phonebook.utils.QueryDAO;
import com.sda.practicalproject.phonebook.database.contact.Contact;
import com.sda.practicalproject.phonebook.database.contact.ContactDAO;
import com.sda.practicalproject.phonebook.database.user.User;
import com.sda.practicalproject.phonebook.utils.Navigate;
import com.sda.practicalproject.phonebook.utils.ValidateInput;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class UpdateContactController extends CreateContactController {
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
    private void handleUpdateContact() {
        if (ValidateInput.isNotEmpty(nameText, addressText, emailText, phoneText)) {
            if (ValidateInput.isNumber(phoneText.getText())) {
                updateContact();
            } else {
                errorText.setText("Phone number is invalid format!");
            }
        } else {
            errorText.setText("Input fields cannot be empty!");
        }
    }

    private void updateContact() {
        Long number = Long.parseLong(phoneText.getText());
        if (QueryDAO.numberIsUnique(number)) {
            Contact updatedContact = new Contact(nameText.getText(), addressText.getText(), emailText.getText(), Long.parseLong(phoneText.getText()));
            updatedContact.setCreatorId(creator.getUserId());

            ContactDAO.updateContact(updatedContact, id);

            goToContactList();
        } else {
            errorText.setText("Phone number should be unique!");
        }
    }

    @FXML
    private void goToContactList() {
        Navigate.goTo(cancelButton, "/fxml/contact_list.fxml");
    }

    @FXML
    private void emptyErrorText() {
        ValidateInput.resetError(errorText);
    }
}
