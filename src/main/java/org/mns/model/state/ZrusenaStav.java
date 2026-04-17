package org.mns.model.state;

import org.mns.model.Rezervace;

public class ZrusenaStav implements RezervaceStav {
    public void potvrdit(Rezervace r) {
        throw new IllegalStateException("Nelze potvrdit zrušenou rezervaci.");
    }

    public void zrusit(Rezervace r) {
        throw new IllegalStateException("Rezervace je již zrušena.");
    }

    public void dokoncit(Rezervace r) {
        throw new IllegalStateException("Nelze dokončit zrušenou rezervaci.");
    }

    public String getNazev() {
        return "ZRUSENA";
    }
}
