package org.mns.model;

import org.mns.model.state.NepotvrzenaStav;
import org.mns.model.state.RezervaceStav;

import java.sql.Timestamp;

public class Rezervace {
    protected int id;
    protected int zakaznikId;
    protected int stulId;

    protected Timestamp casZacatek;
    protected Timestamp casKonec;

    protected String poznamka;
    protected int pocetOsob;

    protected RezervaceStav stav;

    public Rezervace(int id, int zakaznikId, int stulId, Timestamp casZacatek, Timestamp casKonec, String poznamka, int pocetOsob) {
        this.id = id;
        this.zakaznikId = zakaznikId;
        this.stulId = stulId;
        this.casZacatek = casZacatek;
        this.casKonec = casKonec;
        this.poznamka = poznamka;
        this.pocetOsob = pocetOsob;
        this.stav = new NepotvrzenaStav();
    }

    public Rezervace(int zakaznikId, int stulId, Timestamp casZacatek, Timestamp casKonec, String poznamka, int pocetOsob) {
        this.zakaznikId = zakaznikId;
        this.stulId = stulId;
        this.casZacatek = casZacatek;
        this.casKonec = casKonec;
        this.poznamka = poznamka;
        this.pocetOsob = pocetOsob;
        this.stav = new NepotvrzenaStav();
    }

    public Rezervace() {

    }

    public void potvrdit() {
        stav.potvrdit(this);
    }

    public void zrusit() {
        stav.zrusit(this);
    }

    public void dokoncit() {
        stav.dokoncit(this);
    }

    public String getStavNazev() {
        return stav.getNazev();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZakaznikId() {
        return zakaznikId;
    }

    public void setZakaznikId(int zakaznikId) {
        this.zakaznikId = zakaznikId;
    }

    public int getStulId() {
        return stulId;
    }

    public void setStulId(int stulId) {
        this.stulId = stulId;
    }

    public Timestamp getCasZacatek() {
        return casZacatek;
    }

    public void setCasZacatek(Timestamp casZacatek) {
        this.casZacatek = casZacatek;
    }

    public Timestamp getCasKonec() {
        return casKonec;
    }

    public void setCasKonec(Timestamp casKonec) {
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

    public RezervaceStav getStav() {
        return stav;
    }

    public void setStav(RezervaceStav stav) {
        this.stav = stav;
    }
}
