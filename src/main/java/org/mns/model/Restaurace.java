package org.mns.model;

public class Restaurace {
    protected String nazev;
    protected String adresa;
    protected String telefon;
    protected String email;
    protected double prumerneHodnoceni;

    public Restaurace(String nazev, String adresa, String telefon, String email) {
        this.nazev = nazev;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
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

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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
}
