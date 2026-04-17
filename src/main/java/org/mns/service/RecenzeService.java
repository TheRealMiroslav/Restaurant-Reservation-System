package org.mns.service;

import org.mns.dao.RecenzeDao;
import org.mns.dao.RestauraceDao;
import org.mns.model.Recenze;
import org.mns.model.Zakaznik;

public class RecenzeService {
    private final RecenzeDao recenzeDao;
    private final RestauraceDao restauraceDao;

    public RecenzeService(RecenzeDao recenzeDao, RestauraceDao restauraceDao) {
        this.recenzeDao = recenzeDao;
        this.restauraceDao = restauraceDao;
    }

    public void vytvorRecenzi(Zakaznik zakaznik, int restauraceId, double hodnoceni, String slovniHodnoceni) throws Exception {
        Recenze recenze = new Recenze(zakaznik.getId(), restauraceId, hodnoceni, slovniHodnoceni);
        recenzeDao.vytvoritRecenzi(recenze);
    }

    public void aktualizujHodnoceniRestaurace(int restauraceId) throws Exception {
        // TODO: někdy udělat
    }
}