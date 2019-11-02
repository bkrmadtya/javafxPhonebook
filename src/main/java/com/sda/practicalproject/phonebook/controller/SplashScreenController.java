package com.sda.practicalproject.phonebook.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenController implements Initializable {

    @FXML
    private Label progressBar;

    public static Label label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        label = progressBar;
    }
}
