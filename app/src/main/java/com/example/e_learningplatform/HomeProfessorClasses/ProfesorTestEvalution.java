package com.example.e_learningplatform.HomeProfessorClasses;

public class ProfesorTestEvalution {

    String nume,TEST2,status,ID,materie;

    public ProfesorTestEvalution(String nume, String TEST2, String status,String ID,String materie) {
        this.nume = nume;
        this.TEST2 = TEST2;
        this.status = status;
        this.ID = ID;
        this.materie = materie;
    }
    public ProfesorTestEvalution(){

    }

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTEST2() {
        return TEST2;
    }

    public void setTEST2(String TEST2) {
        this.TEST2 = TEST2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
