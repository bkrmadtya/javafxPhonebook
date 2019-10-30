package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.registry.Registry;
import com.sda.practicalproject.phonebook.database.registry.RegistryDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class PhoneRegistryController {
    ObservableList<String> data = FXCollections.observableArrayList();

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
    private ListView registryList;

    @FXML
    private void initialize() {
        registryList.getItems().clear();
        List<Registry> registry = QueryDAO.getAllRegistry();

        registry.forEach(registry1 -> {
            data.add(registry1.toString());
        });

        registryList.setItems(data);
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
