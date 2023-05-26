package com.example.e_learningplatform.HomeClasses;

public class Materii {

    String titlu;
    String imagine;
    String An;

    public Materii(String titlu, String image, String an) {
        this.titlu = titlu;
        this.imagine = image;
        this.An = an;
    }
    public Materii(){

    }

    public String getAn() {
        return An;
    }

    public void setAn(String an) {
        this.An = an;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getImagine() {
        return imagine;
    }

    public void setImagine(String imagine) {
        this.imagine = imagine;
    }
}
