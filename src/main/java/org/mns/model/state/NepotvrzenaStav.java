package org.mns.model.state;

import org.mns.model.Rezervace;

public class NepotvrzenaStav implements RezervaceStav {
    public void potvrdit(Rezervace r) {
        r.setStav(new PotvrzenaStav());
    }

    public void zrusit(Rezervace r) {
        r.setStav(new ZrusenaStav());
    }

    public void dokoncit(Rezervace r) {
        throw new IllegalStateException("Nelze dokončit nepotvrzenou rezervaci.");
    }

    public String getNazev() {
        return "NEPOTVRZENA";
    }
}
