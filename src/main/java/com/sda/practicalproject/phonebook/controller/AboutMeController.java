package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.utils.Navigate;
import com.sda.practicalproject.phonebook.utils.OriginOfAbout;
import com.sda.practicalproject.phonebook.utils.Utils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;


public class AboutMeController {

    @FXML
    private ImageView aboutImage;

    @FXML
    private Hyperlink linkedinLink;

    @FXML
    private Hyperlink githubLink;

    @FXML
    private Hyperlink facebookLink;

    @FXML
    private Hyperlink amirLinkedin;

    @FXML
    private Hyperlink githubRepoLink;

    @FXML
    private void initialize() {
        linkedinLink.setTooltip(new Tooltip("https://www.linkedin.com/in/bkrmadtya/"));
        githubLink.setTooltip(new Tooltip("https://github.com/bkrmadtya"));
        facebookLink.setTooltip(new Tooltip("https://www.facebook.com/merophotos"));
        amirLinkedin.setTooltip(new Tooltip("https://www.linkedin.com/in/amir-jalal/"));
        githubRepoLink.setTooltip(new Tooltip("https://github.com/bkrmadtya/javafxPhonebook"));
    }

    @FXML
    private void goBack() {
        Navigate.goTo(aboutImage, OriginOfAbout.getOrigin());
    }

    @FXML
    private void openLink(Event event) {
        Object source = event.getSource();
        if (source instanceof Hyperlink) {
            Hyperlink clickedHyperLink = (Hyperlink) source;
            Utils.getHostServices().showDocument(clickedHyperLink.getTooltip().getText());
        }
    }
}
