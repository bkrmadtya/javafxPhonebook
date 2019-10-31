package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.database.registry.Registry;
import com.sda.practicalproject.phonebook.database.registry.RegistryDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.loader.Loader;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhoneRegistryController {
    @FXML
    private Label createNewButton;

    @FXML
    private Label editButton;

    @FXML
    private Label deleteButton;

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
        editButton.setVisible(false);
        deleteButton.setVisible(false);

        registryTableView.getItems().clear();

        setColumnValues();

        contacts.forEach(contact -> {
            registryTableView.getItems().add(contact);
        });
    }


    // Maps the field's name from entry class to the column name
    private void setColumnValues() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("personName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    @FXML
    private void isSelected() {
        Long id = null;
        try{
            id = (Long) registryTableView.getSelectionModel().getSelectedItem().getRegistryId();

            if(id != null){
                editButton.setVisible(true);
                deleteButton.setVisible(true);
            }
        }catch(Exception e){
            System.out.println("Empty row selected");
        }
    }

    @FXML
    private void deleteRegistry() {
        Long id = (Long) registryTableView.getSelectionModel().getSelectedItem().getRegistryId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete registry?");
        alert.setContentText("Do you really want to delete this entry?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            RegistryDAO.deleteRegistry(id);
            System.out.println(id);
        }
        this.initialize();
    }

    @FXML
    private void goToEditRegistry() {
        String name = registryTableView.getSelectionModel().getSelectedItem().getPersonName();
        String address = registryTableView.getSelectionModel().getSelectedItem().getAddress();
        String email = registryTableView.getSelectionModel().getSelectedItem().getEmail();
        Long phoneNumber =  registryTableView.getSelectionModel().getSelectedItem().getPhoneNumber();
        Long id =  registryTableView.getSelectionModel().getSelectedItem().getRegistryId();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/update_registry.fxml"));

        try{
            loader.load();
        }catch (IOException e){
            Logger.getLogger(PhoneRegistryController.class.getName()).log(Level.SEVERE, null, e);
        }


        UpdateRegistryController updateRegistryController = loader.getController();
        updateRegistryController.fillData(name , address , email, phoneNumber,id);

        Parent root = loader.getRoot();
        Stage stage = (Stage) registryTableView.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void goToCreateScene() {
        navigateTo("fxml/create_registry.fxml");
    }


    private void navigateTo(String path){
        Navigate.goTo(registryTableView, path);
    }
}
