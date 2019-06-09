package de.redstonetechnik.baufactory.content;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import de.redstonetechnik.baufactory.main.BauFactory;
import de.redstonetechnik.baufactory.mysql.MySQL;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class SystemManager {
    private static String HOST;
    private static String DATABASE;
    private static String USER;
    private static String PASSWORD;

    public SystemManager() {
    }

    public static void setup() {
        registerListener();
        loadConfig();
        readMySQL();
        MySQL.connect();

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS ClanManager (PLAYERID int, CLANID int, UUID VARCHAR(64), NAME VARCHAR(64), CLANNAME VARCHAR(64), CLANTAG VARCHAR(64), RANK VARCHAR(64))");
            ps.executeUpdate();
        } catch (SQLException var1) {
            var1.printStackTrace();
        }

    }

    private static void registerListener() {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();

        try {
            Iterator var2 = ClassPath.from(BauFactory.class.getClassLoader()).getTopLevelClasses("de.redstonetechnik.baufactory.JoinListener").iterator();

            while(var2.hasNext()) {
                ClassInfo classInfo = (ClassInfo)var2.next();
                Class<?> clazz = Class.forName(classInfo.getName());
                if (Listener.class.isAssignableFrom(clazz)) {
                    pluginManager.registerEvents((Listener)clazz.newInstance(), BauFactory.getInstance());
                }
            }
        } catch (Exception var4) {
            ;
        }

    }

    private static File getFile() {
        return new File("plugins/BauSystem", "clanconfig.yml");
    }

    private static FileConfiguration getConfiguration() {
        return YamlConfiguration.loadConfiguration(getFile());
    }

    private static void loadConfig() {
        if (!getFile().exists()) {
            FileConfiguration cfg = getConfiguration();
            cfg.set("admin", "&4Admin &7| &4");
            cfg.set("dev", "&bDev &7| &b");
            cfg.set("mod", "&cMod &7| &c");
            cfg.set("sup", "&9Sup &7| &9");
            cfg.set("yt", "&5YT &7| &5");
            cfg.set("premium", "&6Premium &7| &6");
            cfg.set("spieler", "&aSpieler &7| &a");
            cfg.set("mysql.host", "< Deinen MySQL Hoster eintragen >");
            cfg.set("mysql.user", "< Deinen MySQL User eintragen >");
            cfg.set("mysql.datenbase", "< Deine MySQL Datenbase eintragen >");
            cfg.set("mysql.passwort", "< Dein MySQL Passwort eintragen >");

            try {
                cfg.save(getFile());
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }

    }

    public static String getPlayerTag(String group) {
        FileConfiguration cfg = getConfiguration();
        String message = cfg.getString(group);
        return message;
    }

    public static void readMySQL() {
        FileConfiguration cfg = getConfiguration();
        setHOST(cfg.getString("mysql.host"));
        setUSER(cfg.getString("mysql.user"));
        setDATABASE(cfg.getString("mysql.datenbase"));
        setPASSWORD(cfg.getString("mysql.passwort"));
    }

    public static String getHOST() {
        return HOST;
    }

    public static void setHOST(String hOST) {
        HOST = hOST;
    }

    public static String getDATABASE() {
        return DATABASE;
    }

    public static void setDATABASE(String dATABASE) {
        DATABASE = dATABASE;
    }

    public static String getUSER() {
        return USER;
    }

    public static void setUSER(String uSER) {
        USER = uSER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static void setPASSWORD(String pASSWORD) {
        PASSWORD = pASSWORD;
    }
}

