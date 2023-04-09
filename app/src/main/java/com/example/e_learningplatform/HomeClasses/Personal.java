package com.example.e_learningplatform.HomeClasses;

public class Personal {

    String title,percent_text;
      int  percent;

    public Personal(String title,String percent_text, int percent) {
        this.title = title;
        this.percent = percent;
        this.percent_text = percent_text;
    }

    public String getPercent_text() {
        return percent_text;
    }

    public void setPercent_text(String percent_text) {
        this.percent_text = percent_text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
