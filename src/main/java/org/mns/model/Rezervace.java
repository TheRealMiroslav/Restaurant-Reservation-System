package org.mns.model;

import java.util.Date;

public class Rezervace {
    protected Zakaznik zakaznik;
    protected Stul stul;
    protected Date casZacatek;
    protected Date casKonec;
    protected String poznamka;
    protected int pocetOsob;

    protected boolean aktivni;

    public Rezervace(Zakaznik zakaznik, Stul stul, Date casZacatek, Date casKonec, String poznamka, int pocetOsob) {
        this.zakaznik = zakaznik;
        this.stul = stul;
        this.casZacatek = casZacatek;
        this.casKonec = casKonec;
        this.poznamka = poznamka;
        this.pocetOsob = pocetOsob;
        this.aktivni = false;
    }

    public void potvrditRezervaci() {
        this.aktivni = true;
    }

    public void zrusitRezervaci() {
        this.aktivni = false;
    }

    public Zakaznik getZakaznik() {
        return zakaznik;
    }

    public void setZakaznik(Zakaznik zakaznik) {
        this.zakaznik = zakaznik;
    }

    public Stul getStul() {
        return stul;
    }

    public void setStul(Stul stul) {
        this.stul = stul;
    }

    public Date getCasZacatek() {
        return casZacatek;
    }

    public void setCasZacatek(Date casZacatek) {
        this.casZacatek = casZacatek;
    }

    public Date getCasKonec() {
        return casKonec;
    }

    public void setCasKonec(Date casKonec) {
        this.casKonec = casKonec;
    }

    public String getPoznamka() {
        return poznamka;
    }

    public void setPoznamka(String poznamka) {
        this.poznamka = poznamka;
    }

    public int getPocetOsob() {
        return pocetOsob;
    }

    public void setPocetOsob(int pocetOsob) {
        this.pocetOsob = pocetOsob;
    }
}
