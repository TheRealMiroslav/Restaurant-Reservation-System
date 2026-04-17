package org.mns.model;

public class Stul {
    protected int id;
    protected String kodStolu;
    protected int kapacita;

    public Stul(int id, String kodStolu, int kapacita) {
        this.id = id;
        this.kodStolu = kodStolu;
        this.kapacita = kapacita;
    }

    public Stul(String kodStolu, int kapacita) {
        this.kodStolu = kodStolu;
        this.kapacita = kapacita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
