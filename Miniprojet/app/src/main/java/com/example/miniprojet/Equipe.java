package com.example.miniprojet;

public class Equipe {
    private String uid;
    private String equipe;

    public Equipe(String uid, String equipe) {
        this.uid = uid;
        this.equipe = equipe;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }
}
