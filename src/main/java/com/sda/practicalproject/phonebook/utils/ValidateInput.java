package com.sda.practicalproject.phonebook.utils;


import javafx.scene.control.TextField;

public class ValidateInput {
    private static boolean isNotEmpty(TextField input){
        return !(input.getText().trim().isEmpty());
    }

    public static boolean isNotEmpty(TextField textField1, TextField password){
        return isNotEmpty(textField1) && isNotEmpty(password);
    }

    public static boolean isNotEmpty(TextField textField1, TextField password, TextField password2){
        return isNotEmpty(textField1) && isNotEmpty(password) && isNotEmpty(password2);
    }


}
