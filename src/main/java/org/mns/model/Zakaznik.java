package org.mns.model;

import java.util.Date;

public class Zakaznik extends Osoba {
    public Zakaznik(String jmeno, String prijmeni, String email, String telefoniCislo) {
        super(jmeno, prijmeni, email, telefoniCislo);
    }

    public void vytvorRezervaci(Stul stul, Date casZacatku, Date casKonce, String poznamka, int pocetOsob) {
        Rezervace novaRezervace = new Rezervace(this, stul, casZacatku, casKonce, poznamka, pocetOsob);

        // ulozeni do DB
    }

    public void vytvorRecenzi() {
        Recenze novaRecenze = new Recenze(this, null, 0.0, "");

        // ulozeni do DB
    }
}
