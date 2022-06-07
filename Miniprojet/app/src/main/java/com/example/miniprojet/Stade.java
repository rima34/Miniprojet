package com.example.miniprojet;

public class Stade {
    private String uid;
    private String stade;

    public Stade(String uid, String stade) {
        this.uid = uid;
        this.stade = stade;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }
}
