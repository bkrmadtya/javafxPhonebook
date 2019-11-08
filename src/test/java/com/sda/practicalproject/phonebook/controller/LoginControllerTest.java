package com.sda.practicalproject.phonebook.controller;

import com.sda.practicalproject.phonebook.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.isVisible;


public class LoginControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(Main.class.getResource("/fxml/login.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void testEmptyInputReturnsErrorMessage(){
        Label label = GuiTest.find("#errorText");

        clickOn("#usernameText");
        write("");
        clickOn("#passwordText");
        write("asdf");

        clickOn("#loginButton");

        assertThat(label.getText(), Is.is("Credentials cannot be empty!"));
    }

    @Test
    public void testNonEmptyInputDoesNotReturnsErrorMessage(){
        Label label = GuiTest.find("#errorText");

        clickOn("#usernameText");
        write("asdf");
        clickOn("#passwordText");
        write("asdf");

        clickOn("#loginButton");

        assertThat(label.getText(), Is.is("Invalid Username or Password!"));

    }

    @Test
    public void testLoginButtonIsVisible(){
        verifyThat("#loginButton", isVisible());
    }

    @Test
    public void testUsernameInputIsVisible(){
        verifyThat("#usernameText", isVisible());
    }

    @Test
    public void testPasswordInputIsVisible(){
        verifyThat("#passwordText", isVisible());
    }

    @Test
    public void testErrorTextIsEmpty(){
        Label label = (Label) GuiTest.find("#errorText");
        assertThat(label.getText(), Is.is(""));
    }
}