package com.sda.practicalproject.phonebook.utils;

import com.sda.practicalproject.phonebook.database.user.User;

public class LoggedInUser {
    private static User user;

    public static void setUser(User loggedUser) {
        if (user == null) {
            user = loggedUser;
        }
    }

    public static User getUser() {
        return user;
    }

    public static void removeUser() {
        user = null;
    }
}
