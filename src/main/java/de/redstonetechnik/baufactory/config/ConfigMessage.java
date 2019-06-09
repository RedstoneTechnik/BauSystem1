package de.redstonetechnik.baufactory.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigMessage {
    private File file;
    public FileConfiguration cfg;

    public ConfigMessage() {
        this.file = new File("plugins/BauSystem/messages.yml");
        this.cfg = YamlConfiguration.loadConfiguration(this.file);
    }

    public void initConfig() {
        cfg.addDefault("Commands.TraceSystem.Message", "Diese Funktion wurde vom Besitzer des Servers deaktiviert.");
        cfg.addDefault("Commands.GUI.Message", "Diese Funktion wurde vom Besitzer des Servers deaktiviert.");
        cfg.addDefault("GUI.BackButton.Titel", "§bZurück");
        cfg.addDefault("GUI.mainmenu.Item1.Titel", "§bBau Abteilungen");
        cfg.addDefault("GUI.mainmenu.Item2.Titel", "§bWelten Optionen");
        cfg.addDefault("GUI.mainmenu.Item3.Titel", "§bSpieler Optionen");
        cfg.addDefault("GUI.world.Item1.Titel", "§eMittelalter Welt");
        cfg.addDefault("GUI.world.Item2.Titel", "§eSteampunk Welt");
        cfg.addDefault("GUI.world.Item3.Titel", "§eFantasie Welt");
        cfg.addDefault("GUI.world.Item4.Titel", "§eModern Welt");
        cfg.addDefault("GUI.world.Item5.Titel", "§eIsland Welt");
        cfg.addDefault("GUI.world.Titel", "§aWähle deine Welt");
        cfg.addDefault("GUI.worldoption.watch.Titel", "§aZeit Einstellung");
        cfg.addDefault("GUI.worldoption.watch.Item1.Titel", "§aZeit ändern auf Tag");
        cfg.addDefault("GUI.worldoption.watch.Item1.Message", "Du hast die Zeit in deiner Welt auf §6Tag §7gesetzt.");
        cfg.addDefault("GUI.worldoption.watch.Item1.Item", "WATCH");
        cfg.addDefault("GUI.worldoption.watch.Item2.Titel", "§aZeit ändern auf 10000");
        cfg.addDefault("GUI.worldoption.watch.Item2.Message", "Du hast die Zeit in deiner Welt auf §610000 §7gesetzt.");
        cfg.addDefault("GUI.worldoption.watch.Item2.Item", "WATCH");
        cfg.addDefault("GUI.worldoption.watch.Item3.Titel", "§aZeit ändern auf 18000");
        cfg.addDefault("GUI.worldoption.watch.Item3.Message", "Du hast die Zeit in deiner Welt auf §618000 §7gesetzt.");
        cfg.addDefault("GUI.worldoption.watch.Item3.Item", "WATCH");
        cfg.addDefault("GUI.worldoption.watch.Item4.Titel", "§aZeit ändern auf Nacht");
        cfg.addDefault("GUI.worldoption.watch.Item4.Message", "Du hast die Zeit in deiner Welt auf §6Nacht §7gesetzt.");
        cfg.addDefault("GUI.worldoption.watch.Item4.Item", "WATCH");
        cfg.addDefault("GUI.worldoption.watch.DenyMessage", "§cDu darfst hier nicht die Zeit ändern");
        cfg.addDefault("MiniWarGear1.Message", "§7Du wurdest zu MiniWarGear TestGelände 1 Teleportiert");
        cfg.addDefault("MiniWarGear1.Titel", "§7Name des Items in der GUI1");
        cfg.addDefault("MiniWarGear1.Position", 18);
        cfg.addDefault("MiniWarGear1.Item", "REDSTONE");
        cfg.addDefault("MiniWarGear2.Message", "§7Du wurdest zu MiniWarGear TestGelände 2 Teleportiert");
        cfg.addDefault("MiniWarGear2.Titel", "§7Name des Items in der GUI2");
        cfg.addDefault("MiniWarGear2.Position", 19);
        cfg.addDefault("MiniWarGear2.Item", "REDSTONE");
        cfg.addDefault("MiniWarGear7.Message", "§7Du wurdest zu MiniWarGear TestGelände 3 Teleportiert");
        cfg.addDefault("MiniWarGear7.Titel", "§7Name des Items in der GUI3");
        cfg.addDefault("MiniWarGear7.Position", 20);
        cfg.addDefault("MiniWarGear7.Item", "REDSTONE");
        cfg.addDefault("MiniWarGear8.Message", "§7Du wurdest zu MiniWarGear TestGelände 4 Teleportiert");
        cfg.addDefault("MiniWarGear8.Titel", "§7Name des Items in der GUI4");
        cfg.addDefault("MiniWarGear8.Position", 21);
        cfg.addDefault("MiniWarGear8.Item", "REDSTONE");
        cfg.addDefault("WarGear5.Message", "§7Du wurdest zu WarGear TestGelände 5 Teleportiert");
        cfg.addDefault("WarGear5.Titel", "§7Name des Items in der GUI5");
        cfg.addDefault("WarGear5.Position", 9);
        cfg.addDefault("WarGear5.Item", "REDSTONE_BLOCK");
        cfg.addDefault("WarGear6.Message", "§7Du wurdest zu WarGear TestGelände 6 Teleportiert");
        cfg.addDefault("WarGear6.Titel", "§7Name des Items in der GUI6");
        cfg.addDefault("WarGear6.Position", 10);
        cfg.addDefault("WarGear6.Item", "REDSTONE_BLOCK");
        cfg.addDefault("WarGear7.Message", "§7Du wurdest zu WarGear TestGelände 7 Teleportiert");
        cfg.addDefault("WarGear7.Titel", "§7Name des Items in der GUI7");
        cfg.addDefault("WarGear7.Position", 11);
        cfg.addDefault("WarGear7.Item", "REDSTONE_BLOCK");
        cfg.addDefault("WarGear8.Message", "§7Du wurdest zu WarGear TestGelände 8 Teleportiert");
        cfg.addDefault("WarGear8.Titel", "§7Name des Items in der GUI8");
        cfg.addDefault("WarGear8.Position", 12);
        cfg.addDefault("WarGear8.Item", "REDSTONE_BLOCK");
        cfg.addDefault("WarShip1.Message", "§7Du wurdest zu WarShip TestGelände 1 Teleportiert");
        cfg.addDefault("WarShip1.Titel", "§7Name des Items in der GUI9");
        cfg.addDefault("WarShip1.Position", 0);
        cfg.addDefault("WarShip1.Item", "WATER_BUCKET");
        cfg.addDefault("WarShip2.Message", "§7Du wurdest zu WarShip TestGelände 2 Teleportiert");
        cfg.addDefault("WarShip2.Titel", "§7Name des Items in der GUI10");
        cfg.addDefault("WarShip2.Position", 1);
        cfg.addDefault("WarShip2.Item", "WATER_BUCKET");
        cfg.addDefault("AirShip1.Message", "§7Du wurdest zu AirShip TestGelände 1 Teleportiert");
        cfg.addDefault("AirShip1.Titel", "§7Name des Items in der GUI11");
        cfg.addDefault("AirShip1.Position", 27);
        cfg.addDefault("AirShip1.Item", "ELYTRA");
        cfg.addDefault("AirShip2.Message", "§7Du wurdest zu AirShip TestGelände 2 Teleportiert");
        cfg.addDefault("AirShip2.Titel", "§7Name des Items in der GUI12");
        cfg.addDefault("AirShip2.Position", 28);
        cfg.addDefault("AirShip2.Item", "ELYTRA");
        cfg.addDefault("AirShip3.Message", "§7Du wurdest zu AirShip TestGelände 3 Teleportiert");
        cfg.addDefault("AirShip3.Titel", "§7Name des Items in der GUI13");
        cfg.addDefault("AirShip3.Position", 29);
        cfg.addDefault("AirShip3.Item", "ELYTRA");
        cfg.addDefault("AirShip4.Message", "§7Du wurdest zu AirShip TestGelände 4 Teleportiert");
        cfg.addDefault("AirShip4.Titel", "§7Name des Items in der GUI14");
        cfg.addDefault("AirShip4.Position", 30);
        cfg.addDefault("AirShip4.Item", "ELYTRA");
        cfg.addDefault("AirShip5.Message", "§7Du wurdest zu AirShip TestGelände 5 Teleportiert");
        cfg.addDefault("AirShip5.Titel", "§7Name des Items in der GUI15");
        cfg.addDefault("AirShip5.Position", 31);
        cfg.addDefault("AirShip5.Item", "ELYTRA");
        cfg.addDefault("AirShip6.Message", "§7Du wurdest zu AirShip TestGelände 6 Teleportiert");
        cfg.addDefault("AirShip6.Titel", "§7Name des Items in der GUI16");
        cfg.addDefault("AirShip6.Position", 32);
        cfg.addDefault("AirShip6.Item", "ELYTRA");
        cfg.addDefault("AirShip7.Message", "§7Du wurdest zu AirShip TestGelände 7 Teleportiert");
        cfg.addDefault("AirShip7.Titel", "§7Name des Items in der GUI17");
        cfg.addDefault("AirShip7.Position", 33);
        cfg.addDefault("AirShip7.Item", "ELYTRA");
        cfg.addDefault("AirShip8.Message", "§7Du wurdest zu AirShip TestGelände 8 Teleportiert");
        cfg.addDefault("AirShip8.Titel", "§7Name des Items in der GUI18");
        cfg.addDefault("AirShip8.Position", 34);
        cfg.addDefault("AirShip8.Item", "ELYTRA");

        this.cfg.options().copyDefaults(true);
        try {
            this.cfg.save(this.file);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
