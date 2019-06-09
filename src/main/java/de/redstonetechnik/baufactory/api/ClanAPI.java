package de.redstonetechnik.baufactory.api;

import de.redstonetechnik.baufactory.mysql.MySQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ClanAPI {
    public ClanAPI() {
    }

    public static boolean isPlayerInClan(UUID uuid) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT UUID FROM ClanManager WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException var3) {
            var3.printStackTrace();
            return false;
        }
    }

    private static Integer getClanID(UUID uuid) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT CLANID FROM ClanManager WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("CLANID");
            }
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

        return -1;
    }

    public static String getClanName(UUID uuid) {
        int clanID = getClanID(uuid);

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT CLANNAME FROM ClanManager WHERE CLANID = ?");
            ps.setInt(1, clanID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CLANNAME");
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return "null";
    }

    public static String getClanTag(UUID uuid) {
        int clanID = getClanID(uuid);

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT CLANTAG FROM ClanManager WHERE CLANID = ?");
            ps.setInt(1, clanID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CLANTAG");
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return "null";
    }
}

