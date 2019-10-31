package com.sda.practicalproject.phonebook.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigate {
    public static void goTo(Node node, String path) {
        try {
            Parent root = FXMLLoader.load((Navigate.class.getResource(path)));
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
