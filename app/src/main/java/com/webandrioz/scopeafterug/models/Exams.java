package com.webandrioz.scopeafterug.models;

/**
 * Created by root on 26/2/17.
 */

public class Exams {
    String examsId, examsName,about;

    public Exams(String examsId, String examsName, String about) {
        this.examsId = examsId;
        this.examsName = examsName;
        this.about = about;
    }

    public String getExamsId() {
        return examsId;
    }

    public void setExamsId(String examsId) {
        this.examsId = examsId;
    }

    public String getExamsName() {
        return examsName;
    }

    public void setExamsName(String examsName) {
        this.examsName = examsName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
