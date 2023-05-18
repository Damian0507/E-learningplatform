package com.example.e_learningplatform.HomeProfessorClasses;

public class ProfessorMaterii {


    String nume_materie;
    String imagine;


    public ProfessorMaterii(String nume_materie, String imagine) {
        this.nume_materie = nume_materie;
        this.imagine = imagine;
    }

    public String getNume_materie() {
        return nume_materie;
    }

    public void setNume_materie(String nume_materie) {
        this.nume_materie = nume_materie;
    }

    public String getImagine() {
        return imagine;
    }

    public void setImagine(String imagine) {
        this.imagine = imagine;
    }

    public ProfessorMaterii(){

    }

}
