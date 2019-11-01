package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.registry.Registry;
import com.sda.practicalproject.phonebook.database.registry.RegistryDAO;
import com.sda.practicalproject.phonebook.database.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class PhoneRegistryController {

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
    private TableView<Registry> registryTableView;

    @FXML
    private TableColumn<String, Registry> nameColumn;

    @FXML
    private TableColumn<String, Registry> phoneColumn;

    @FXML
    private TableColumn<String, Registry> addressColumn;

    @FXML
    private TableColumn<Long, Registry> emailColumn;

    private User loggedInUser;

    @FXML
    private void initialize() {
        List<Registry> contacts = QueryDAO.getAllRegistry();
        editButton.setVisible(false);
        deleteButton.setVisible(false);

        registryTableView.getItems().clear();

        setColumnValues();

        contacts.forEach(contact -> {
            registryTableView.getItems().add(contact);
        });

    }

    public void setUser(User user) {
        this.loggedInUser = user;
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
            id = (Long) registryTableView.getSelectionModel().getSelectedItem().getRegistryId();

            if (id != null) {
                editButton.setVisible(true);
                deleteButton.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("Empty row selected");
        }
    }

    @FXML
    private void deleteRegistry() {
        Long id = (Long) registryTableView.getSelectionModel().getSelectedItem().getRegistryId();

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
            RegistryDAO.deleteRegistry(id);
            System.out.println(id);
        }
        this.initialize();
    }

    @FXML
    private void goToUpdateRegistry() {
        String name = registryTableView.getSelectionModel().getSelectedItem().getPersonName();
        String address = registryTableView.getSelectionModel().getSelectedItem().getAddress();
        String email = registryTableView.getSelectionModel().getSelectedItem().getEmail();
        Long phoneNumber = registryTableView.getSelectionModel().getSelectedItem().getPhoneNumber();
        Long id = registryTableView.getSelectionModel().getSelectedItem().getRegistryId();

        Navigate.withParameter(loader -> {
            UpdateRegistryController updateRegistryController = loader.getController();
            updateRegistryController.fillData(name, address, email, phoneNumber, id, loggedInUser);

            return updateRegistryController;
        }, registryTableView, "/fxml/update_registry.fxml");
    }

    @FXML
    private void goToCreateScene() {
        Navigate.withParameter(loader -> {
            CreateRegistryController createRegistryController = loader.getController();
            createRegistryController.setCreatorId(loggedInUser);

            return createRegistryController;
        }, registryTableView, "/fxml/create_registry.fxml");
    }

}
