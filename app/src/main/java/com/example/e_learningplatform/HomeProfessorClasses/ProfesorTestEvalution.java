package com.example.e_learningplatform.HomeProfessorClasses;

public class ProfesorTestEvalution {
    String nume_elev;
    int profile_pic;

    public ProfesorTestEvalution(String nume_elev, int profile_pic) {
        this.nume_elev = nume_elev;
        this.profile_pic = profile_pic;
    }

    public String getNume_elev() {
        return nume_elev;
    }

    public void setNume_elev(String nume_elev) {
        this.nume_elev = nume_elev;
    }

    public int getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(int profile_pic) {
        this.profile_pic = profile_pic;
    }
}
