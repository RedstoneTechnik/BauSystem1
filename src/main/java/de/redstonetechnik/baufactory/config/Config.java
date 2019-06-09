package de.redstonetechnik.baufactory.config;

import java.io.File;
import java.io.IOException;

import de.redstonetechnik.baufactory.main.BauFactory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

    private File file;
    public FileConfiguration cfg;

    public String backupDir;
    public String backupDirTheme1;
    public String backupDirTheme2;
    public String backupDirTheme3;
    public String backupDirTheme4;
    public String backupDirTheme5;
    public String regionDir;
    public String regionDirTheme1;
    public String regionDirTheme2;
    public String regionDirTheme3;
    public String regionDirTheme4;
    public String regionDirTheme5;
    public String DefaultDir;
    public Location spawn;
    public Location pasteloc;

    public Config() {
        this.file = new File("plugins/BauSystem/config.yml");
        this.cfg = YamlConfiguration.loadConfiguration(this.file);
    }

    public void initConfig() {
        this.cfg.options().copyDefaults(true);
        cfg.options().header("Locations are in format: world/x/y/z/yaw/pitch");
        this.cfg.addDefault("Nicht ändern sonst macht das das system kaputt", "dirHere");
        this.cfg.addDefault("WorldSource.dir.default", "dirHere");
        this.cfg.addDefault("WorldSource.dir.Theme1", "dirHere");
        this.cfg.addDefault("WorldSource.dir.Theme2", "dirHere");
        this.cfg.addDefault("WorldSource.dir.Theme3", "dirHere");
        this.cfg.addDefault("WorldSource.dir.Theme4", "dirHere");
        this.cfg.addDefault("WorldSource.dir.Theme5", "dirHere");
        this.cfg.addDefault("RegionSource.dir.default", "dirHere");
        this.cfg.addDefault("RegionSource.dir.Theme1", "dirHere");
        this.cfg.addDefault("RegionSource.dir.Theme2", "dirHere");
        this.cfg.addDefault("RegionSource.dir.Theme3", "dirHere");
        this.cfg.addDefault("RegionSource.dir.Theme4", "dirHere");
        this.cfg.addDefault("RegionSource.dir.Theme5", "dirHere");
        cfg.addDefault("Location.spawn", "world/0/64/0/90/0");

        cfg.addDefault("Commands.TraceSystem.Einstellung", false);
        cfg.addDefault("Commands.TraceSystem.Info", "Diese Funktion beeinträchtigt die Perfomance des Servers je nach Server Hardware würde ich empfehlen dies zu aktivieren oder zu deaktivieren.");
        cfg.getBoolean("Commands.TraceSystem.Einstellung", false);
        cfg.getBoolean("Commands.TraceSystem.Einstellung", true);
        cfg.addDefault("Commands.GUI.Einstellung", false);
        cfg.addDefault("Commands.GUI.Info", "Mit dieser Option kannst du einstellen ob der Befehl /bau gui aktiviert ist oder deaktiviert.");
        cfg.getBoolean("Commands.GUI.Einstellung", false);
        cfg.getBoolean("Commands.GUI.Einstellung", true);
        cfg.addDefault("Features.Redstoneclock", false);
        cfg.addDefault("Features.Redstoneclock.Info", "Zeit (in Sekunden) vor dem Zurücksetzen des (MaxImpulsion) variable");
        cfg.addDefault("Features.Redstoneclock.Tickspeed", 300);
        cfg.addDefault("Features.Redstoneclock.AntiTNTMinecartExplosion", true);
        cfg.addDefault("Features.Redstoneclock.Info1", "Maximale Anzahl von Redstone-Blinksignalen, die während der Delay -Periode zulässig sind");
        cfg.addDefault("Features.Redstoneclock.MaxPulses", 150);
        cfg.addDefault("Features.Redstoneclock.Info2", "Hier kannst du einstellen ob die Items die für die clock benutzt werden gedroppt werden sollen.");
        cfg.addDefault("Features.Redstoneclock.DropItems", true);
        cfg.addDefault("Features.Redstoneclock.Info3", "Hier kannst du einstellen ob die clock automatisch entfernt werden soll.");
        cfg.addDefault("Features.Redstoneclock.AutoRemove", true);
        cfg.addDefault("Features.Redstoneclock.Info4", "Hier kannst du einstellen ob dort wo die lagg maschiene ist ein schild erstellt werden soll.");
        cfg.addDefault("Features.Redstoneclock.CreateSignWhenClockIsBreak", true);
        cfg.addDefault("Features.Redstoneclock.Sign", false);
        cfg.addDefault("Features.Redstoneclock.Sign.Line1", "§bDie Redstoneclock");
        cfg.addDefault("Features.Redstoneclock.Sign.Line2", "§bist zu");
        cfg.addDefault("Features.Redstoneclock.Sign.Line3", "§bschnell");
        cfg.addDefault("Features.Redstoneclock.Sign.Line4", "");
        cfg.addDefault("Features.Redstoneclock.checkedClock", false);
        cfg.addDefault("Features.Redstoneclock.checkedClock.comparator", true);
        cfg.addDefault("Features.Redstoneclock.checkedClock.observer", true);
        cfg.addDefault("Features.Redstoneclock.checkedClock.piston", true);
        cfg.addDefault("Features.Redstoneclock.checkedClock.redstoneAndRepeater", true);
        cfg.addDefault("Features.Redstoneclock.metrics", true);
        cfg.getBoolean("Features.Redstoneclock.metrics", true);
        cfg.getBoolean("Features.Redstoneclock.metrics", false);
        cfg.getBoolean("Features.Redstoneclock.checkedClock.comparator", true);
        cfg.getBoolean("Features.Redstoneclock.checkedClock.comparator", false);
        cfg.getBoolean("Features.Redstoneclock.checkedClock.observer", true);
        cfg.getBoolean("Features.Redstoneclock.checkedClock.observer", false);
        cfg.getBoolean("Features.Redstoneclock.checkedClock.redstoneAndRepeater", true);
        cfg.getBoolean("Features.Redstoneclock.checkedClock.redstoneAndRepeater", false);
        cfg.getBoolean("Features.Redstoneclock.checkedClock.piston", true);
        cfg.getBoolean("Features.Redstoneclock.checkedClock.piston", false);
        cfg.addDefault("Coords.loc.x", 0);
        cfg.addDefault("Coords.loc.y", 0);
        cfg.addDefault("Coords.loc.z", 0);
        cfg.addDefault("MiniWarGear1.loc.x", -907);
        cfg.addDefault("MiniWarGear1.loc.y", 122);
        cfg.addDefault("MiniWarGear1.loc.z", -29);
        cfg.addDefault("MiniWarGear2.loc.x", -841);
        cfg.addDefault("MiniWarGear2.loc.y", 122);
        cfg.addDefault("MiniWarGear2.loc.z", -29);
        cfg.addDefault("MiniWarGear7.loc.x", -907);
        cfg.addDefault("MiniWarGear7.loc.y", 122);
        cfg.addDefault("MiniWarGear7.loc.z", -29);
        cfg.addDefault("MiniWarGear8.loc.x", -841);
        cfg.addDefault("MiniWarGear8.loc.y", 122);
        cfg.addDefault("MiniWarGear8.loc.z", -120);
        cfg.addDefault("WarGear5.loc.x", -1332);
        cfg.addDefault("WarGear5.loc.y", 122);
        cfg.addDefault("WarGear5.loc.z", -53);
        cfg.addDefault("WarGear6.loc.x", -1220);
        cfg.addDefault("WarGear6.loc.y", 122);
        cfg.addDefault("WarGear6.loc.z", -53);
        cfg.addDefault("WarGear7.loc.x", -1108);
        cfg.addDefault("WarGear7.loc.y", 122);
        cfg.addDefault("WarGear7.loc.z", -53);
        cfg.addDefault("WarGear8.loc.x", -996);
        cfg.addDefault("WarGear8.loc.y", 122);
        cfg.addDefault("WarGear8.loc.z", -53);
        cfg.addDefault("WarShip1.loc.x", -943);
        cfg.addDefault("WarShip1.loc.y", 122);
        cfg.addDefault("WarShip1.loc.z", 149);
        cfg.addDefault("WarShip2.loc.x", -1200);
        cfg.addDefault("WarShip2.loc.y", 122);
        cfg.addDefault("WarShip2.loc.z", 149);
        cfg.addDefault("AirShip1.loc.x", -674);
        cfg.addDefault("AirShip1.loc.y", 122);
        cfg.addDefault("AirShip1.loc.z", -45);
        cfg.addDefault("AirShip2.loc.x", -487);
        cfg.addDefault("AirShip2.loc.y", 122);
        cfg.addDefault("AirShip2.loc.z", -45);
        cfg.addDefault("AirShip3.loc.x", -302);
        cfg.addDefault("AirShip3.loc.y", 122);
        cfg.addDefault("AirShip3.loc.z", -45);
        cfg.addDefault("AirShip4.loc.x", -115);
        cfg.addDefault("AirShip4.loc.y", 122);
        cfg.addDefault("AirShip4.loc.z", -45);
        cfg.addDefault("AirShip5.loc.x", -115);
        cfg.addDefault("AirShip5.loc.y", 122);
        cfg.addDefault("AirShip5.loc.z", 201);
        cfg.addDefault("AirShip6.loc.x", -302);
        cfg.addDefault("AirShip6.loc.y", 122);
        cfg.addDefault("AirShip6.loc.z", 201);
        cfg.addDefault("AirShip7.loc.x", -487);
        cfg.addDefault("AirShip7.loc.y", 122);
        cfg.addDefault("AirShip7.loc.z", 201);
        cfg.addDefault("AirShip8.loc.x", -674);
        cfg.addDefault("AirShip8.loc.y", 122);
        cfg.addDefault("AirShip8.loc.z", 201);
        cfg.addDefault("Lobby.name", "Lobby_1");
        try {
            this.cfg.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DefaultDir = cfg.getString("Nicht ändern sonst macht das das system kaputt");
        backupDir = cfg.getString("WorldSource.dir.default");
        backupDirTheme1 = cfg.getString("WorldSource.dir.Theme1");
        backupDirTheme2 = cfg.getString("WorldSource.dir.Theme2");
        backupDirTheme3 = cfg.getString("WorldSource.dir.Theme3");
        backupDirTheme4 = cfg.getString("WorldSource.dir.Theme4");
        backupDirTheme5 = cfg.getString("WorldSource.dir.Theme5");
        regionDir = cfg.getString("RegionSource.dir.default");
        regionDirTheme1 = cfg.getString("RegionSource.dir.Theme1");
        regionDirTheme2 = cfg.getString("RegionSource.dir.Theme2");
        regionDirTheme3 = cfg.getString("RegionSource.dir.Theme3");
        regionDirTheme4 = cfg.getString("RegionSource.dir.Theme4");
        regionDirTheme5 = cfg.getString("RegionSource.dir.Theme5");
        spawn = toLocation(cfg.getString("Location.spawn"));
        pasteloc = new Location(null, cfg.getDouble("Coords.loc.x"), cfg.getDouble("Coords.loc.y"), cfg.getDouble("Coords.loc.z"));
    }

    public Location toLocation(String configString) {
        String[] splitted = configString.split("/");
        String wName = splitted[0];
        double x = Double.parseDouble(splitted[1]);
        double y = Double.parseDouble(splitted[2]);
        double z = Double.parseDouble(splitted[3]);
        float yaw = Float.parseFloat(splitted[4]);
        float pitch = Float.parseFloat(splitted[5]);
        if(Bukkit.getWorld(wName) == null)
            Bukkit.createWorld(new WorldCreator(wName));
        return new Location(Bukkit.getWorld(wName), x, y, z, yaw, pitch);
    }

    public void save() {
        try {
            this.cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
