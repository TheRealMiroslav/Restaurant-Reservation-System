package org.mns.service;

import org.mns.dao.ZakaznikDao;
import org.mns.dto.PrihlaseniUdaje;
import org.mns.model.Zakaznik;

import java.util.Optional;

public class ZakaznikService {

    private final ZakaznikDao zakaznikDao;

    public ZakaznikService(ZakaznikDao zakaznikDao) {
        this.zakaznikDao = zakaznikDao;
    }

    public Optional<Zakaznik> prihlaseni(PrihlaseniUdaje udaje) throws Exception {
        Optional<Zakaznik> zakaznik = zakaznikDao.najdiPodleEmailu(udaje.getEmail());

        if (zakaznik.isEmpty()) return Optional.empty();
        if (!zakaznik.get().getHeslo().equals(udaje.getHeslo())) return Optional.empty();

        return zakaznik;
    }

    public void registrace(Zakaznik zakaznik) throws Exception {
        zakaznikDao.vytvorZakaznika(zakaznik);
    }
}