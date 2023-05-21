package com.example.e_learningplatform.HomeProfessorClasses;

public class ProfessorAddVideo {
    String nume_materie, nume_curs, video, curs;

    public ProfessorAddVideo(String nume_materie, String nume_curs, String video, String curs) {

        this.nume_materie = nume_materie;
        this.nume_curs = nume_curs;
        this.video = video;
        this.curs = curs;
    }
    public ProfessorAddVideo(){

    }

    public String getNume_materie() {
        return nume_materie;
    }

    public void setNume_materie(String nume_materie) {
        this.nume_materie = nume_materie;
    }

    public String getNume_curs() {
        return nume_curs;
    }

    public void setNume_curs(String nume_curs) {
        this.nume_curs = nume_curs;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getCurs() {
        return curs;
    }

    public void setCurs(String curs) {
        this.curs = curs;
    }
}
