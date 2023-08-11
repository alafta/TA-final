package com.example.projectfrontend.classPrinsipalSolusi;

public class PenjelasanPrinsipalSolusi {
    private int id;
    private String penjelasanIng;
    private String penjelasanInd;
    private int idPs;

    public PenjelasanPrinsipalSolusi(int id, String penjelasanIng, String penjelasanInd, int idPs) {
        this.id = id;
        this.penjelasanIng = penjelasanIng;
        this.penjelasanInd = penjelasanInd;
        this.idPs = idPs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPenjelasanIng() {
        return penjelasanIng;
    }

    public void setPenjelasanIng(String penjelasanIng) {
        this.penjelasanIng = penjelasanIng;
    }

    public String getPenjelasanInd() {
        return penjelasanInd;
    }

    public void setPenjelasanInd(String penjelasanInd) {
        this.penjelasanInd = penjelasanInd;
    }

    public int getIdPs() {
        return idPs;
    }

    public void setIdPs(int idPs) {
        this.idPs = idPs;
    }
}
