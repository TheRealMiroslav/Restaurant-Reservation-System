package org.mns.factory;

import org.mns.model.Rezervace;
import org.mns.model.Stul;
import org.mns.model.Zakaznik;

import java.sql.Timestamp;

;

public class RezervaceFactory {
    public static Rezervace vytvorNovou(Zakaznik z, Stul s, Timestamp od, Timestamp doKdy, String poznamka, int pocetOsob) {
        // Validace na jednom místě
        if (pocetOsob > s.getKapacita()) {
            throw new IllegalArgumentException("Počet osob přesahuje kapacitu stolu");
        }

        if (od.after(doKdy)) {
            throw new IllegalArgumentException("Začátek je po konci");
        }

        if (od.before(new Timestamp(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("Nelze rezervovat v minulosti");
        }

        return new Rezervace(z.getId(), s.getId(), od, doKdy, poznamka, pocetOsob);
    }
}