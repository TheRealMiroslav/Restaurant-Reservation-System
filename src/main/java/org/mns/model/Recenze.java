package org.mns.model;

public class Recenze {
    protected int zakaznikId;
    protected int restauraceId;
    protected double hodnoceni;
    protected String slovniHodnoceni;

    public Recenze(int zakaznikId, int restauraceId, double hodnoceni, String slovniHodnoceni) {
        this.zakaznikId = zakaznikId;
        this.restauraceId = restauraceId;
        this.hodnoceni = hodnoceni;
        this.slovniHodnoceni = slovniHodnoceni;
    }

    public int getZakaznikId() {
        return zakaznikId;
    }

    public void setZakaznikId(int zakaznikId) {
        this.zakaznikId = zakaznikId;
    }

    public int getRestauraceId() {
        return restauraceId;
    }

    public void setRestauraceId(int restauraceId) {
        this.restauraceId = restauraceId;
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
