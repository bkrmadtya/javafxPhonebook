package com.sda.practicalproject.phonebook;


import com.sda.practicalproject.phonebook.services.sessionManager.SessionManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

public class Main extends Application {

    public static void main(String[] args) {
        Session session = SessionManager.getSessionFactory().openSession();
        launch(args);

    }

    private static void queries() {

//        RegistryDAO.createPerson(new Registry("Bikram Karki", "Kathmandu, Nepal", "karki.bikram007@gmail.com",358442354248L ));
//        UserDAO.createUser(new User("bikram", "Karki"));

//        QueryDAO.getAllRegistry();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load((getClass().getClassLoader().getResource("fxml/login.fxml")));
        primaryStage.setTitle("Phone Book");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
