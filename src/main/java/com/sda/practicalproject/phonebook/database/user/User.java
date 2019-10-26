package com.sda.practicalproject.phonebook.database.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor
@EntityListeners(UserListerner.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
