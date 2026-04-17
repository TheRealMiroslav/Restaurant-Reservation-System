package org.mns.model.state;

import org.mns.model.Rezervace;

/**
 * Interface návrhového vzoru State pro životní cyklus entity Rezervace.
 * Každý konkrétní stav definuje povolené přechody.
 */
public interface RezervaceStav {
    void potvrdit(Rezervace r);

    void zrusit(Rezervace r);

    void dokoncit(Rezervace r);

    String getNazev();
}