package org.mns;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
    public static Connection getConnection() throws Exception {
        // "jdbc:h2:./data/restaurace" vytvoří soubor ve složce projektu
        String url = "jdbc:h2:./data/restaurace;AUTO_SERVER=TRUE";
        String user = "sa";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
}