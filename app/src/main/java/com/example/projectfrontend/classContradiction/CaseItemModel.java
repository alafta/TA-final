package com.example.projectfrontend.classContradiction;

import com.example.projectfrontend.classUnderstanding.ItemModel;

public class CaseItemModel {
    String number;
    int caseStudy;
    Type type;

    public enum Type {
        CASE1, CASE2, CASE3, CASE4,CASE5
    }

    public CaseItemModel(String number, int caseStudy, Type type) {
        this.number = number;
        this.caseStudy = caseStudy;
        this.type = type;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
