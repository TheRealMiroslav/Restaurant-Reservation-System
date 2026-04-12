package org.mns.model;

public class Recenze {
    protected Zakaznik zakaznik;
    protected Restaurace restaurace;
    protected double hodnoceni;
    protected String slovniHodnoceni;

    public Recenze(Zakaznik zakaznik, Restaurace restaurace, double hodnoceni, String slovniHodnoceni) {
        this.zakaznik = zakaznik;
        this.restaurace = restaurace;
        this.hodnoceni = hodnoceni;
        this.slovniHodnoceni = slovniHodnoceni;
    }

    public Zakaznik getZakaznik() {
        return zakaznik;
    }

    public void setZakaznik(Zakaznik zakaznik) {
        this.zakaznik = zakaznik;
    }

    public Restaurace getRestaurace() {
        return restaurace;
    }

    public void setRestaurace(Restaurace restaurace) {
        this.restaurace = restaurace;
    }

    public double getHodnoceni() {
        return hodnoceni;
    }

    public void setHodnoceni(double hodnoceni) {
        this.hodnoceni = hodnoceni;
    }

    public String getSlovniHodnoceni() {
        return slovniHodnoceni;
    }

    public void setSlovniHodnoceni(String slovniHodnoceni) {
        this.slovniHodnoceni = slovniHodnoceni;
    }
}
