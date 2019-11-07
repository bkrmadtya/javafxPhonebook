package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.controller.saveContact.UpdateContactController;
import com.sda.practicalproject.phonebook.database.contact.Contact;
import com.sda.practicalproject.phonebook.database.contact.ContactDAO;
import com.sda.practicalproject.phonebook.database.user.User;
import com.sda.practicalproject.phonebook.utils.LoggedInUser;
import com.sda.practicalproject.phonebook.utils.Navigate;
import com.sda.practicalproject.phonebook.utils.OriginOfAbout;
import com.sda.practicalproject.phonebook.utils.QueryDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class ContactListController {

    public MenuButton kebabMenu;
    @FXML
    private HBox createNewButton;

    @FXML
    private HBox editButton;

    @FXML
    private HBox deleteButton;

    @FXML
    private TextField nameText;

    @FXML
    private TextField addressText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField phoneText;

    @FXML
    private TableView<Contact> contactListTableView;

    @FXML
    private TableColumn<String, Contact> nameColumn;

    @FXML
    private TableColumn<String, Contact> phoneColumn;

    @FXML
    private TableColumn<String, Contact> addressColumn;

    @FXML
    private TableColumn<Long, Contact> emailColumn;

    private User loggedInUser;

    private Long previousSelection;

    @FXML
    private void initialize() {
        disableEditAndDeleteButtons();

        this.loggedInUser = LoggedInUser.getUser();
        List<Contact> contacts = QueryDAO.getAllContacts();

        contactListTableView.getItems().clear();

        setColumnValues();

        contacts.forEach(contact -> {
            contactListTableView.getItems().add(contact);
        });
    }

    public void setUser(User user) {
        System.out.println("Logged in user : " + loggedInUser.getUsername());
    }

    // Maps the field's name from entry class to the column name
    private void setColumnValues() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("personName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    @FXML
    private void rowIsSelected() {
        Long id = null;
        try {
            id = (Long) contactListTableView.getSelectionModel().getSelectedItem().getContactId();
            Long creatorId = contactListTableView.getSelectionModel().getSelectedItem().getCreatorId();

            boolean isCreator = creatorId.equals(loggedInUser.getUserId());

            if (id != null && isCreator) {

                // toggle selection of the row
                // for first selection (previousSelection == null) buttons are enabled and row is highlighted
                // for second selection
                // if it is the same row, then it is disabled and previousSelection is reset
                // if it is a new row, then the row is highlighted
                if (previousSelection == null || previousSelection != id) {
                    previousSelection = id;
                    enableEditAndDeleteButtons();
                } else {
                    previousSelection = null;
                    deselectRow();
                    disableEditAndDeleteButtons();
                }
            }
        } catch (Exception e) {
            System.out.println("Empty row selected");
        }
    }

    private void deselectRow() {
        contactListTableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void deleteContact() {
        Long id = (Long) contactListTableView.getSelectionModel().getSelectedItem().getContactId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete registry?");
        alert.setHeaderText(null);
        alert.setContentText("Do you really want to delete this entry?");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("icons/warning.png"));

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.YES);
        yesButton.setDefaultButton(false);

        Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.NO);
        noButton.setDefaultButton(true);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.YES) {
            ContactDAO.deleteContact(id);
            this.initialize();
        } else {
            previousSelection = null;
            deselectRow();
        }
    }

    @FXML
    private void goToUpdateContact() {
        String name = contactListTableView.getSelectionModel().getSelectedItem().getPersonName();
        String address = contactListTableView.getSelectionModel().getSelectedItem().getAddress();
        String email = contactListTableView.getSelectionModel().getSelectedItem().getEmail();
        Long phoneNumber = contactListTableView.getSelectionModel().getSelectedItem().getPhoneNumber();
        Long id = contactListTableView.getSelectionModel().getSelectedItem().getContactId();

        Navigate.withParameter(loader -> {
            UpdateContactController updateContactController = loader.getController();
            updateContactController.fillData(name, address, email, phoneNumber, id, loggedInUser);

            return updateContactController;
        }, contactListTableView, "/fxml/update_contact.fxml");
    }

    @FXML
    private void goToCreateContact() {
        Navigate.goTo(contactListTableView, "/fxml/create_contact.fxml");
    }

    @FXML
    private void onMenuClick() {
        System.out.println("Menu Clicked");
    }

    @FXML
    private void logout() {
        LoggedInUser.removeUser();
        Navigate.goTo(contactListTableView, "/fxml/login.fxml");
    }

    @FXML
    private void exit() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void goToAbout() {
        OriginOfAbout.setOrigin("/fxml/contact_list.fxml");
        Navigate.goTo(contactListTableView, "/fxml/aboutme.fxml");
    }

    private void enableEditAndDeleteButtons() {
        editButton.setVisible(true);
        deleteButton.setVisible(true);
    }

    private void disableEditAndDeleteButtons() {
        editButton.setVisible(false);
        deleteButton.setVisible(false);
    }

}
