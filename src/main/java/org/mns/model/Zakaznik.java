package org.mns.model;

import java.sql.Timestamp;

public class Zakaznik extends Osoba {
    public Zakaznik(int id, String jmeno, String prijmeni, String email, String telefonniCislo, String heslo) {
        super(id, jmeno, prijmeni, email, telefonniCislo, heslo);
    }

    public Zakaznik(int id, String jmeno, String prijmeni, String email, String telefonniCislo) {
        super(id, jmeno, prijmeni, email, telefonniCislo);
    }

    public Zakaznik() {
        super();
    }

    public Rezervace vytvorRezervaci(int stulId, Timestamp casZacatku, Timestamp casKonce, String poznamka) {
        return new Rezervace(this.getId(), stulId, casZacatku, casKonce, poznamka);
    }

    public Recenze vytvorRecenzi(int restauraceId, double hodnoceni, String slovniHodnoceni) {
        return new Recenze(this.getId(), restauraceId, hodnoceni, slovniHodnoceni);
    }
}
