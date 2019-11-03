package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.Main;
import com.sda.practicalproject.phonebook.utils.Navigate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class AboutMeController {
    @FXML
    private Text aboutMeText;

    @FXML
    private ImageView aboutImage;

    @FXML
    private Hyperlink linkedinLink;

    @FXML
    private Hyperlink githubLink;

    @FXML
    private Hyperlink facebookLink;


    @FXML
    private void initialize(){
        linkedinLink.setTooltip(new Tooltip("https://www.linkedin.com/in/bkrmadtya/"));
        githubLink.setTooltip(new Tooltip("https://github.com/bkrmadtya"));
        facebookLink.setTooltip(new Tooltip("https://www.facebook.com/merophotos"));

    }


    @FXML
    private  void goToRegister(){
        Navigate.goTo(aboutImage, "/fxml/contact_list.fxml");
    }

}
