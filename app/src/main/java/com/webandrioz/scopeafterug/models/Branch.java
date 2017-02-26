package com.webandrioz.scopeafterug.models;

/**
 * Created by root on 26/2/17.
 */

public class Branch {
    String id,namen,examId;

    public Branch(String id, String namen, String examId) {
        this.id = id;
        this.namen = namen;
        this.examId = examId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamen() {
        return namen;
    }

    public void setNamen(String namen) {
        this.namen = namen;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }
}
