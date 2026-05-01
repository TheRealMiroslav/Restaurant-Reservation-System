package org.mns;

import org.mns.db.DatabaseManager;
import org.mns.db.TestData;
import org.mns.ui.CliApp;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.initialize();
        TestData.insert();
        new CliApp().start();
    }
}