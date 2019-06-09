package de.redstonetechnik.baufactory.mysql;

import de.redstonetechnik.baufactory.content.SystemManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private static Connection con;

    public MySQL() {
    }

    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + SystemManager.getHOST() + ":3306/" + SystemManager.getDATABASE() + "?autoReconnect=true", SystemManager.getUSER(), SystemManager.getPASSWORD());
                System.out.println("[ClanMySQL] Verbindung wurde aufgebaut");
            } catch (SQLException var1) {
                var1.printStackTrace();
            }
        }

    }

    public static void disconnect() {
        if (isConnected()) {
            try {
                con.close();
                System.out.println("[ClanMySQL] Verbindung wurde geschlossen");
            } catch (SQLException var1) {
                var1.printStackTrace();
            }
        }

    }

    public static boolean isConnected() {
        return con != null;
    }

    public static Connection getConnection() {
        return con;
    }
}
