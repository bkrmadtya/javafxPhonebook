package com.sda.practicalproject.phonebook.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class AboutMeController {
    @FXML
    private Text aboutMeText;

    @FXML
    private ImageView aboutImage;

    @FXML
    private  void goToRegister(){
        Navigate.goTo(aboutImage, "/fxml/contact_list.fxml");
    }

}
