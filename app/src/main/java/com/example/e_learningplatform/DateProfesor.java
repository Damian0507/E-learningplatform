package com.example.e_learningplatform;

public class DateProfesor {

    String student,materie,profesor;

    public DateProfesor(String student, String materie, String profesor) {
        this.student = student;
        this.materie = materie;
        this.profesor = profesor;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}
