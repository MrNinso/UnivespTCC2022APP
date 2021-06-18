package com.developer.projetounivesp2021_frontrnd.objects;

public class Login {
    private final String email, password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
