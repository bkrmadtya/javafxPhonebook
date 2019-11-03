package com.sda.practicalproject.phonebook;


import com.sda.practicalproject.phonebook.controller.MyPreloader;
import com.sda.practicalproject.phonebook.utils.Navigate;
import com.sda.practicalproject.phonebook.utils.sessionManager.SessionManager;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;

public class Main extends Application {

    @Override
    public void init() throws Exception{
        int countLimit = 50000;
        for(int i = 0; i < countLimit; i++){
            int progress = (100 * i)/countLimit;
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load((getClass().getResource("/fxml/login.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(("/styles/style.css"));

        primaryStage.setTitle("Phone Book");
        primaryStage.getIcons().add(new Image("icons/phone-book.png"));
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Session session = SessionManager.getSessionFactory().openSession();
        LauncherImpl.launchApplication(Main.class, MyPreloader.class, args);
    }
//
//    public static void setURL(){
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.getClass().
//    }
}
