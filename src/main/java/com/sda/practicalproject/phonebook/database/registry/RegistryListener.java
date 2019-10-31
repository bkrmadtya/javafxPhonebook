package com.sda.practicalproject.phonebook.database.registry;


import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;

public class RegistryListener {

    @PostPersist
    public void registryCreated(Registry registry){
        System.out.println("Registry created: " + registry.getPersonName());
    }

    @PostRemove
    public void registryUpdated(Registry registry){
        System.out.println("Registry deleted: " + registry.getPersonName());
    }
//    @PostLoad
//    public void printUser(Registry registry){
//        System.out.println("Registry loaded: " + registry.getPersonName());
//    }
}
