package com.sda.practicalproject.phonebook.database.user;


import javax.persistence.PostLoad;
import javax.persistence.PostPersist;

public class UserListerner {

    @PostPersist
    public void personCreated(User user){
        System.out.println("User created: " + user.getUsername());
    }

    @PostLoad
    public void printUser(User user){
        System.out.println("User loaded: " + user.getUsername());
    }
}
