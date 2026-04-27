package org.mns.service;

import org.mns.dao.RezervaceDao;
import org.mns.dao.StulDao;
import org.mns.factory.RezervaceFactory;
import org.mns.model.Rezervace;
import org.mns.model.Stul;
import org.mns.model.Zakaznik;

import java.sql.Timestamp;
import java.util.List;

public class RezervaceService {
    private final RezervaceDao rezervaceDao;
    private final StulDao stulDao;

    public RezervaceService(RezervaceDao rezervaceDao, StulDao stulDao) {
        this.rezervaceDao = rezervaceDao;
        this.stulDao = stulDao;
    }

    // UC-03
    public Rezervace vytvorRezervaci(Zakaznik zakaznik, Stul stul, Timestamp od, Timestamp do_, String poznamka, int pocetOsob) throws Exception {
        // nebo mohu sem jen predavat ID stolu nebo zakaznika a udělat to pomocí DAO, ale to by bylo zbytečné, když už mám objekty./mo

        // UC-03 krok 7 - kontrola kolize
        boolean dostupny = stulDao.jeStulDostupny(stul.getId(), od.toString(), do_.toString());

        if (!dostupny) {
            throw new IllegalArgumentException("Stůl není dostupný v daném časovém období");
        }

        Rezervace rezervace = RezervaceFactory.vytvorNovou(zakaznik, stul, od, do_, poznamka, pocetOsob);

        rezervaceDao.vytvoritRezervaci(rezervace);

        return rezervace;
    }

    // UC-04
    public void zrusRezervaci(Rezervace rezervace) throws Exception {
        rezervace.zrusit();

        rezervaceDao.zrusitRezervaci(rezervace.getId());
    }

    public List<Rezervace> getRezervaceZakaznika(int zakaznikId) throws Exception {
        return rezervaceDao.najdiRezervaceZakaznika(zakaznikId);
    }
}