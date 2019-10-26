package com.sda.practicalproject.phonebook.database.registry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="registry")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(RegistryListener.class)
public class Registry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registryId;

    @Column
    private String personName;

    @Column
    private String address;

    @Column
    private String email;

    @Column
    private long phoneNumber;

    public Registry(String name, String address, String email, long phoneNumber){
        this.personName = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return personName + " " +
                address + " " +
                email + " " +
                + phoneNumber ;
    }
}
