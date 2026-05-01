package org.mns;

import org.mns.db.DatabaseManager;
import org.mns.db.TestData;
import org.mns.ui.CliApp;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.initialize();
        TestData.insert();

        System.out.println("Testovací účty: jan@test.cz / 1234 | eva@test.cz / 1234");

        new CliApp().start();
    }
}