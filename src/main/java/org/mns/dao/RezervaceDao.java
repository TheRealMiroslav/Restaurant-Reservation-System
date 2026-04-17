package org.mns.dao;

import org.mns.model.Rezervace;

import java.util.List;

public interface RezervaceDao {
    void vytvoritRezervaci(Rezervace rezervace) throws Exception;
    //void upravitRezervaci(Rezervace rezervace);
    void zrusitRezervaci(int id) throws Exception;
    List<Rezervace> najdiRezervaceZakaznika(int zakaznikId) throws Exception;
}