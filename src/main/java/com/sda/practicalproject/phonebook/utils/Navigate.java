package com.sda.practicalproject.phonebook.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Function;

public class Navigate {

    public static void goTo(Node node, String path) {
        try {
            Parent root = FXMLLoader.load((Navigate.class.getResource(path)));
            setUpScene(node, root);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static <T> void withParameter(Function<FXMLLoader, T> parameter, Node node, String path) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Navigate.class.getResource(path));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        parameter.apply(loader);

        Parent root = loader.getRoot();

        setUpScene(node, root);
    }

    private static void setUpScene(Node node, Parent root) {
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/style.css");
        stage.setScene(scene);
    }
}


