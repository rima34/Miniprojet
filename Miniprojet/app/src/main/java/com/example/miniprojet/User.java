package com.example.miniprojet;

public class User {
    private String nom;
    private String prenom;
    private String email;
    private String pass;
    private String cpass;

    public User(String nom, String prenom, String email, String pass, String cpass) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pass = pass;
        this.cpass = cpass;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }
}
