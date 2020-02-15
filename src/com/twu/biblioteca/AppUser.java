package com.twu.biblioteca;

public class AppUser {
    protected long userId;

    public AppUser(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
