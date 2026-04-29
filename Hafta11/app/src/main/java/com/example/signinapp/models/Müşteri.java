package com.example.signinapp.models;

import java.io.Serializable;

public class Müşteri implements Serializable {


    private String adsoyad, mail,telefon,uid;

    public Müşteri(String adsoyad, String mail, String telefon) {
        this.adsoyad = adsoyad;
        this.mail = mail;
        this.telefon = telefon;
    }

    public String getAdsoyad() {
        return adsoyad;
    }

    public void setAdsoyad(String adsoyad) {
        this.adsoyad = adsoyad;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
