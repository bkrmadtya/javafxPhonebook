package com.sda.practicalproject.phonebook;


import com.sda.practicalproject.phonebook.services.sessionManager.SessionManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;

public class Main extends Application {

    public static void main(String[] args) {
        Session session = SessionManager.getSessionFactory().openSession();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load((getClass().getResource("/fxml/login.fxml")));
        primaryStage.setTitle("Phone Book");
        primaryStage.getIcons().add(new Image("icons/phone-book.png"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
