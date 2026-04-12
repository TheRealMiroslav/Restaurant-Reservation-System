package org.mns.model;

public class Stul {
    protected String kodStolu;
    protected int kapacita;

    public Stul(String kodStolu, int kapacita) {
        this.kodStolu = kodStolu;
        this.kapacita = kapacita;
    }

    public String getKodStolu() {
        return kodStolu;
    }

    public void setKodStolu(String kodStolu) {
        this.kodStolu = kodStolu;
    }

    public int getKapacita() {
        return kapacita;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }
}
