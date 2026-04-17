package org.mns.model.state;

import org.mns.model.Rezervace;

public class ProbehlaStav implements RezervaceStav {
    public void potvrdit(Rezervace r) {
        throw new IllegalStateException("Proběhlou rezervaci nelze potvrdit.");
    }

    public void zrusit(Rezervace r) {
        throw new IllegalStateException("Proběhlou rezervaci nelze zrušit.");
    }

    public void dokoncit(Rezervace r) {
        throw new IllegalStateException("Rezervace již proběhla.");
    }

    public String getNazev() {
        return "PROBEHLA";
    }
}
