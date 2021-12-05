package com.example.hackcoming;

public class UserAccount {
    private String email;
    private String password;
    private String name;
    private String location;

    public UserAccount() { }

    public String getEmail() {return email; }
    public void setEmailId(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) { this.password = password; }

    public String getName() {return name;}
    public void setName(String name) { this.name = name; }

    public String getLocation() {return location;}
    public void setLocation(String location) { this.location = location; }
}