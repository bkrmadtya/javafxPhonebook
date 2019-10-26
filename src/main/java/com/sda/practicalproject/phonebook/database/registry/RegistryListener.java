package com.sda.practicalproject.phonebook.database.registry;


import javax.persistence.PostLoad;
import javax.persistence.PostPersist;

public class RegistryListener {

    @PostPersist
    public void personCreated(Registry registry){
        System.out.println("Registry created: " + registry.getPersonName());
    }

    @PostLoad
    public void printUser(Registry registry){
        System.out.println("Registry loaded: " + registry.getPersonName());
    }
}
