package org.mns.ui;

import org.mns.dao.*;
import org.mns.service.*;

import java.util.Scanner;

public class CliApp {
    private final Scanner sc = new Scanner(System.in);
    private final SessionContext session = new SessionContext();

    private final ZakaznikService zakaznikService;
    private final RezervaceService rezervaceService;
    private final RecenzeService recenzeService;
    private final RestauraceDao restauraceDao;

    public CliApp() {
        ZakaznikDao zakaznikDao = new ZakaznikDaoImpl();
        StulDao stulDao = new StulDaoImpl();
        RezervaceDao rezervaceDao = new RezervaceDaoImpl();
        RecenzeDao recenzeDao = new RecenzeDaoImpl();
        RestauraceDaoImpl restauraceDao = new RestauraceDaoImpl();

        this.zakaznikService = new ZakaznikService(zakaznikDao);
        this.rezervaceService = new RezervaceService(rezervaceDao, stulDao);
        this.recenzeService = new RecenzeService(recenzeDao, restauraceDao);
        this.restauraceDao = restauraceDao;
    }

    public void start() {
        System.out.println("=== Rezervační systém restaurací ===");
        while (true) {
            if (session.isPrihlasen()) {
                zobrazMenuZakaznika();
            } else {
                zobrazUvodniMenu();
            }
        }
    }

    private void zobrazUvodniMenu() { /* TODO */ }
    private void zobrazMenuZakaznika() { /* TODO */ }
}