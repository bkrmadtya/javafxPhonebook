package com.sda.practicalproject.phonebook.utils;


import javafx.scene.control.TextField;

public class ValidateInput {
    private static boolean isNotEmpty(TextField input) {
        return !(input.getText().trim().isEmpty());
    }

    public static boolean isNotEmpty(TextField textField1, TextField password) {
        return isNotEmpty(textField1) && isNotEmpty(password);
    }

    public static boolean isNotEmpty(TextField textField1, TextField password, TextField password2) {
        return isNotEmpty(textField1) && isNotEmpty(password) && isNotEmpty(password2);
    }

    public static boolean isNotEmpty(TextField name, TextField phone, TextField email, TextField address){
        return isNotEmpty(name, phone, email) && isNotEmpty(address);
    }

    public static boolean isNumber(String input) {
        String regex = "[0-9]+";
        return (input.matches(regex));
    }

}
