package org.mns.model.state;

import org.mns.model.Rezervace;

public class PotvrzenaStav implements RezervaceStav {
    public void potvrdit(Rezervace r) {
        throw new IllegalStateException("Rezervace je již potvrzena.");
    }

    public void zrusit(Rezervace r) {
        r.setStav(new ZrusenaStav());
    }

    public void dokoncit(Rezervace r) {
        r.setStav(new ProbehlaStav());
    }

    public String getNazev() {
        return "POTVRZENA";
    }
}