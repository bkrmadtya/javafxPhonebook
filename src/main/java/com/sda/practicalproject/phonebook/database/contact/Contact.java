package com.sda.practicalproject.phonebook.database.contact;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(ContactListener.class)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    @Column
    private String personName;

    @Column
    private String address;

    @Column
    private String email;

    @Column
    private Long phoneNumber;

    @Column
    private Long creatorId;

    public Contact(String name, String address, String email, long phoneNumber) {
        this.personName = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setCreatorId(Long id) {
        this.creatorId = id;
    }

    @Override
    public String toString() {
        return personName + " " +
                address + " " +
                email + " " +
                +phoneNumber;
    }
}
