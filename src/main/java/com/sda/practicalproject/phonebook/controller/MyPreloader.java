package com.sda.practicalproject.phonebook.controller;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyPreloader extends Preloader {
    private Stage splashStage;
    private Scene scene;

    @Override
    public void init() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/splash_screen.fxml"));
        root.setStyle("-fx-background-color: transparent;");
        scene = new Scene(root, Color.TRANSPARENT);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.splashStage = primaryStage;

        splashStage.setScene(scene);
        splashStage.initStyle(StageStyle.TRANSPARENT);
        splashStage.show();
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
        if(info instanceof ProgressNotification){
            SplashScreenController.label.setText("Loading " + ((ProgressNotification) info).getProgress() + "%");
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if(stateChangeNotification.getType() == StateChangeNotification.Type.BEFORE_START){
            splashStage.hide();
        }

    }
}
