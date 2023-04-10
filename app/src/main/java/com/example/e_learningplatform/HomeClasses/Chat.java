package com.example.e_learningplatform.HomeClasses;

public class Chat {
    String nume_utilizator, last_message;
    int profile_pic;

    public Chat(String nume_utilizator, String last_message, int profile_pic) {
        this.nume_utilizator = nume_utilizator;
        this.last_message = last_message;
        this.profile_pic = profile_pic;
    }

    public String getNume_utilizator() {
        return nume_utilizator;
    }

    public void setNume_utilizator(String nume_utilizator) {
        this.nume_utilizator = nume_utilizator;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }

    public int getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(int profile_pic) {
        this.profile_pic = profile_pic;
    }
}
