package org.mns.ui;

import org.mns.model.Zakaznik;

public class SessionContext {
    private Zakaznik prihlasenyZakaznik;

    public boolean isPrihlasen() {
        return prihlasenyZakaznik != null;
    }

    public Zakaznik getZakaznik() {
        return prihlasenyZakaznik;
    }

    public void setPrihlasenyZakaznik(Zakaznik zakaznik) {
        this.prihlasenyZakaznik = zakaznik;
    }

    public void odhlasit() {
        this.prihlasenyZakaznik = null;
    }
}
