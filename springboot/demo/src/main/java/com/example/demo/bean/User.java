package com.example.demo.bean;

import javax.validation.constraints.NotBlank;

public class User {

    @NotBlank
    private String firstName;
    private String lastName;
    private boolean  hasEmail;
    private String mail;
    private String password;

    private Hobby[] hobbies;

    public Hobby[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(Hobby[] hobbies) {
        this.hobbies = hobbies;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isHasEmail() {
        return hasEmail;
    }

    public void setHasEmail(boolean hasEmail) {
        this.hasEmail = hasEmail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
