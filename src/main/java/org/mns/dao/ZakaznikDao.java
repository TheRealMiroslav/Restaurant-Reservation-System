package org.mns.dao;

import org.mns.model.Zakaznik;

import java.util.Optional;

public interface ZakaznikDao {
    public Optional<Zakaznik> najdiPodleEmailu(String email) throws Exception;
    public Optional<Zakaznik> najdiPodleId(int id) throws Exception;
    public void vytvorZakaznika(Zakaznik zakaznik) throws Exception;
}