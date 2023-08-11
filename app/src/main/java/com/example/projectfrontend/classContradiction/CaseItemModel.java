package com.example.projectfrontend.classContradiction;

public class CaseItemModel {
    String number;
    int caseStudy;

    public CaseItemModel(String number, int caseStudy) {
        this.number = number;
        this.caseStudy = caseStudy;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCaseStudy() {
        return caseStudy;
    }

    public void setCaseStudy(int caseStudy) {
        this.caseStudy = caseStudy;
    }
}
