package com.sda.practicalproject.phonebook.utils

import com.sda.practicalproject.phonebook.database.user.User
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import javax.management.Query

@RunWith(JUnit4.class)
class QueryDAOTest {
    void setUp() {
    }

    @Test
    void testGetUserByName() {
        User user = new User("user", "password");
    }

    void testGetAllUser() {
    }

    void testUsernameIsUnique() {
    }

    void testGetAllContacts() {
    }

    void testGetContactByNumber() {
    }

    void testNumberIsUnique() {
    }
}
