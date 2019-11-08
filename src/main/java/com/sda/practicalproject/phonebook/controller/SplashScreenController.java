package com.sda.practicalproject.phonebook.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenController implements Initializable {

    @FXML
    private Label progress;

    public static Label label;

    @FXML
    private ProgressBar progressBar;

    public static ProgressBar statProgressBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        label = progress;
        statProgressBar = progressBar;
    }

}
