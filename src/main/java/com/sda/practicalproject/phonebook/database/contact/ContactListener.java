package com.sda.practicalproject.phonebook.database.contact;


import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

public class ContactListener {

    @PostPersist
    public void contactCreated(Contact contact) {
        System.out.println("Contact created: " + contact.getPersonName()  + " by creator " + contact.getCreatorId());
    }

    @PostRemove
    public void contactRemoved(Contact contact) {
        System.out.println("Contact deleted: " + contact.getPersonName());
    }

    @PostUpdate
    public void contactUpdated(Contact contact) {
        System.out.println("Contact updated: " + contact.getPersonName());
    }



//    @PostLoad
//    public void printUser(Registry registry){
//        System.out.println("Registry loaded: " + registry.getPersonName());
//    }
}
