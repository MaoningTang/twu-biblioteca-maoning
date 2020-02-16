package com.twu.biblioteca;

public class Customer extends AppUser{

    public Customer(long userId, String name, String email, String phoneNumber) {
        super(userId, name, email, phoneNumber);
    }

    public Customer(long userId) {
        super(userId, "NoPermission", "NoPermission", "NoPermission");
    }
}
