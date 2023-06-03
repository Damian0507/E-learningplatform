package com.example.e_learningplatform.HomeClasses;

public class Personal {

  String materie,punctaj;

    public Personal(String materie, String punctaj) {
        this.materie = materie;
        this.punctaj = punctaj;
    }

    public String getMaterie() {
        return materie;
    }
    public Personal(){

    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }

    public String getPunctaj() {
        return punctaj;
    }

    public void setPunctaj(String punctaj) {
        this.punctaj = punctaj;
    }
}
