package com.twu.biblioteca;

public class AppUser {
    protected long userId;
    protected String name;
    protected String email;
    protected String phoneNumber;

    public AppUser(long userId, String name, String email, String phoneNumber) {
        this.userId = userId;
        this.name = name == null?"Not set":name;
        this.email = email== null?"Not set":email;
        this.phoneNumber = phoneNumber == null?"Not set":phoneNumber;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
