package com.example.projectfrontend.classPrinsipalSolusi;

public class PrinsipalSolusi {
    private int id;
    private String namaIndPs;
    private String namaIngPs;

    public PrinsipalSolusi(int id, String namaIndPs, String namaIngPs) {
        this.id = id;
        this.namaIndPs = namaIndPs;
        this.namaIngPs = namaIngPs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaIndPs() {
        return namaIndPs;
    }

    public void setNamaIndPs(String namaIndPs) {
        this.namaIndPs = namaIndPs;
    }

    public String getNamaIngPs() {
        return namaIngPs;
    }

    public void setNamaIngPs(String namaIngPs) {
        this.namaIngPs = namaIngPs;
    }
}
