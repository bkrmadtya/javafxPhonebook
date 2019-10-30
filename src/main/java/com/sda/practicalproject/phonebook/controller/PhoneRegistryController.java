package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.registry.Registry;
import com.sda.practicalproject.phonebook.database.registry.RegistryDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.List;

public class PhoneRegistryController {

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
    private TableView<Registry> registryTableView;

    @FXML
    private TableColumn<String, Registry> nameColumn;

    @FXML
    private TableColumn<String, Registry> phoneColumn;

    @FXML
    private TableColumn<String, Registry> addressColumn;

    @FXML
    private TableColumn<Long, Registry> emailColumn;


    @FXML
    private void initialize() {
        List<Registry> contacts = QueryDAO.getAllRegistry();

        setColumnValues();

        contacts.forEach(contact -> {
            registryTableView.getItems().add(contact);
        });

    }

    // Maps the field's name from entry class to the column name
    private void setColumnValues(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("personName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    @FXML
    private void createRegistry() {
        Registry registry = new Registry(nameText.getText(), addressText.getText(), emailText.getText(), Long.parseLong(phoneText.getText()));
        RegistryDAO.createRegistry(registry);

        nameText.setText("");
        addressText.setText("");
        emailText.setText("");
        phoneText.setText("");

        this.initialize();
    }
}
