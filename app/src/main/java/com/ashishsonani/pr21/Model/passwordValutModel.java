package com.ashishsonani.pr21.Model;

public class passwordValutModel {
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;

    public passwordValutModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String password;
}
