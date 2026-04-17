package org.mns.model;

import java.util.List;

public class Restaurace {
    protected int id;
    protected String nazev;
    protected String adresa;
    protected String telefonniCislo;
    protected String email;
    protected double prumerneHodnoceni;

    protected List<Stul> stoly;
    protected List<Recenze> recenze;

    public Restaurace(int id, String nazev, String adresa, String telefonniCislo, String email) {
        this.id = id;
        this.nazev = nazev;
        this.adresa = adresa;
        this.telefonniCislo = telefonniCislo;
        this.email = email;
    }

    public Restaurace() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefonniCislo() {
        return telefonniCislo;
    }

    public void setTelefonniCislo(String telefonniCislo) {
        this.telefonniCislo = telefonniCislo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPrumerneHodnoceni() {
        return prumerneHodnoceni;
    }

    public void setPrumerneHodnoceni(double prumerneHodnoceni) {
        this.prumerneHodnoceni = prumerneHodnoceni;
    }

    public List<Stul> getStoly() {
        return stoly;
    }

    public void setStoly(List<Stul> stoly) {
        this.stoly = stoly;
    }

    public List<Recenze> getRecenze() {
        return recenze;
    }

    public void setRecenze(List<Recenze> recenze) {
        this.recenze = recenze;
    }
}
