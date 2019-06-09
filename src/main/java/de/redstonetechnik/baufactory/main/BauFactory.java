package de.redstonetechnik.baufactory.main;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import de.redstonetechnik.baufactory.content.SystemManager;
import de.redstonetechnik.baufactory.commands.*;
import de.redstonetechnik.baufactory.content.CommandRemover;
import de.pro_crafting.commandframework.CommandArgs;
import de.pro_crafting.commandframework.CommandFramework;
import de.pro_crafting.commandframework.Completer;
import de.pro_crafting.sawe.RegionListener;
import de.redstonetechnik.baufactory.config.Config;
import de.redstonetechnik.baufactory.config.ConfigMessage;
import de.redstonetechnik.baufactory.metrics.Updater;
import de.redstonetechnik.baufactory.content.FactoryWorld;
import de.redstonetechnik.baufactory.listener.*;
import de.redstonetechnik.baufactory.metrics.Metrics;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import net.thecobix.tag.Tag;
import net.thecobix.tag.TagCompound;
import net.thecobix.tag.TagInputStream;
import net.thecobix.tag.TagOutputStream;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BauFactory extends JavaPlugin implements Listener {

    public static final String S_PREFIX = "§3BauSystem§8» §7";

    public Config config;
    public ConfigMessage configMessage;
    private CommandFramework cmdFramework;
    public Registry<FactoryWorld> factoryWorlds = new Registry<>(FactoryWorld.class, "worlds");
    private BungeeUtil bungeeUtil;
    public File regions = new File("plugins/BauSystem", "regions.data");
    public FileConfiguration getRegionData = YamlConfiguration.loadConfiguration(regions);

    private static BauFactory plugin;

    public void saveRegionData() {
        try { getRegionData.save(regions); } catch (Exception ex) { }
    }

    @Override
    public void onEnable() {
        SystemManager.setup();
        PluginManager pm = Bukkit.getPluginManager();
        //Register events depend on user preferences in config.yml file
        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new CommandGui(), this);
        File checkFile = new File("plugins/BauSystem", "regions.data");
        if (!checkFile.exists()) {

            getRegionData.options().header("Config auf dieser Basis unendlich erweiterbar");

            getRegionData.set("schematic.resetall.position.X", " ");
            getRegionData.set("schematic.resetall.position.Y", " ");
            getRegionData.set("schematic.resetall.position.Z", " ");
            getRegionData.set("schematic.resetall.schematic", " ");

            getRegionData.set("regions.wargear1mini.position.X", " ");
            getRegionData.set("regions.wargear1mini.position.Y", " ");
            getRegionData.set("regions.wargear1mini.position.Z", " ");
            getRegionData.set("regions.wargear1mini.schematic.testblock", " ");
            getRegionData.set("regions.wargear1mini.schematic.reset", " ");

            getRegionData.set("regions.wargear1standart.position.X", " ");
            getRegionData.set("regions.wargear1standart.position.Y", " ");
            getRegionData.set("regions.wargear1standart.position.Z", " ");
            getRegionData.set("regions.wargear1standart.schematic.testblock", " ");
            getRegionData.set("regions.wargear1standart.schematic.reset", " ");

            getRegionData.set("regions.airship1.position.X", " ");
            getRegionData.set("regions.airship1.position.Y", " ");
            getRegionData.set("regions.airship1.position.Z", " ");
            getRegionData.set("regions.airship1.schematic.testblock", " ");
            getRegionData.set("regions.airship1.schematic.reset", " ");


            saveRegionData();
        }

        log("Bootstrap", "§aBauFactory §ev" + getDescription().getVersion() + " §aby RedstoneTechnik.");

        // Time to replace the vanillacommands with WarShipFactory's own versions
        try {
            CommandRemover.removeCommand("tp");
            CommandInjector.injectCommand(new CommandTeleport());

            CommandRemover.removeCommand("gamemode");
            CommandInjector.injectCommand(new CommandGamemode());

        } catch (Exception e1) {
            log("Bootstrap", "§cError while replacing some bukkit internals with warking code:");
            e1.printStackTrace();
        }
        new File("plugins/BauSystem/worlds/").mkdirs();
        config = new Config();
        config.initConfig();
        log("Bootstrap", "§aConfig loaded.");
        configMessage = new ConfigMessage();
        configMessage.initConfig();
        try {
            TagInputStream tis = new TagInputStream(new FileInputStream(new File("plugins/BauSystem/backend.ctf")), TagInputStream.GZIP);
            List<Tag> tags = tis.readTags();
            tis.close();
            Tag t = (TagCompound) tags.get(0);
            factoryWorlds.load((TagCompound) t);
            log("Bootstrap", "§aBackend loaded.");
        } catch (IOException e) {
            log("Bootstrap", "§cLoading backend failed:");
            e.printStackTrace();
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                try {
                    TagOutputStream tos = new TagOutputStream(
                            new FileOutputStream(new File("plugins/BauSystem/backend.ctf")), TagOutputStream.GZIP);
                    tos.write(factoryWorlds.save());
                    tos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 60, 60);
        for (FactoryWorld fw : factoryWorlds.getAll().values()) {
            File f = new File(fw.getWorldAddress());
            if (f.exists() && f.isDirectory()) {
                File bck = new File("plugins/BauSystem/worlds/" + fw.getWorldAddress());
                if (bck.exists() && bck.isDirectory()) {
                    try {
                        log("Bootstrap", "§9Removing dead world " + fw.getWorldAddress());
                        FileUtils.deleteDirectory(f);
                    } catch (IOException e) {
                        log("Bootstrap", "§cAn error occured while removing dead world " + fw.getWorldAddress());
                        e.printStackTrace();
                    }
                } else {
                    File lvlDat = new File(fw.getWorldAddress() + "/level.dat");
                    if (lvlDat.exists()) {
                        try {
                            log("Bootstrap", "§9Fixing dead world " + fw.getWorldAddress());
                            FileUtils.moveDirectory(f, bck);
                        } catch (IOException e) {
                            log("Bootstrap", "§cAn error occured while fixing dead world " + fw.getWorldAddress());
                            e.printStackTrace();
                        }
                    } else {
                        log("Bootstrap", "§4Corrupt world " + fw.getWorldAddress() + " :( I'm sorry. I can't do anything to recover it...");
                        try {
                            FileUtils.deleteDirectory(f);
                        } catch (IOException e) {
                            log("Bootstrap", "§cAn error occured while removing dead world " + fw.getWorldAddress());
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        log("Bootstrap", "§aMessages config loaded");
        try {
            Material checked = Material.OBSERVER;
            if (BauFactory.getInstance().config.cfg.getBoolean("Features.Redstoneclock.checkedClock.observer", true)) {
                pm.registerEvents(new ObserverListener(), this);
            }
        } catch (java.lang.NoSuchFieldError ignored) {
        }
        if (BauFactory.getInstance().config.cfg.getBoolean("Features.Redstoneclock.checkedClock.comparator", true)) {
            pm.registerEvents(new ComparatorListener(), this);
        }
        if (BauFactory.getInstance().config.cfg.getBoolean("Features.Redstoneclock.checkedClock.piston", true)) {
            pm.registerEvents(new PistonListener(), this);
        }
        if (BauFactory.getInstance().config.cfg.getBoolean("Features.Redstoneclock.checkedClock.redstoneAndRepeater", true)) {
            pm.registerEvents(new RedstoneListener(), this);
        }
        if (BauFactory.getInstance().config.cfg.getBoolean("Features.Redstoneclock.metrics")) {
            log("Bootstrap", "§aEnabling Metrics");
            try {
                Metrics metrics = new Metrics(this);
                log("Bootstrap", "§aMetrics loaded");
            } catch (Exception e) {
                log("Bootstrap", "§cAn error occured while trying to enable metrics. Skipping...");
            }
        }
        cmdFramework = new CommandFramework(this);
        cmdFramework.setInGameOnlyMessage(S_PREFIX + "Dieser Befehl darf nur Ingame verwendet werden!");
        cmdFramework.setNoPermMessage("Unknown command. Type \"/help\" for help.");
        Bukkit.getPluginManager().registerEvents(this, this);
        Method[] methods = this.getClass().getMethods();
        for (Method m : methods) {
            if (m.getName().equalsIgnoreCase("completeCommands")) {
                this.cmdFramework.registerCompleter("bau", m, this);
            }
        }

        cmdFramework.registerCommands(new CommandBau());
        log("Bootstrap", "§aCommand framework initiated.");
        Bukkit.getPluginManager().registerEvents(new RegionListener(this), this);
        log("Bootstrap", "§aSaWe initiated.");
        Updater updater = new Updater(this, 322520, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, true);
        if (updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE) {
            log("Bootstrap", "§aEs gibt eine neuere Version des Plugins die wird heruntergeladen und beim nächsten Server restart benutzt. Version:" + updater.getLatestName());
        }else{
            log("Bootstrap", "§aEs ist aktuell kein Update vorhanden");
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                Date d = new Date();
                if (df.format(d).equalsIgnoreCase("23:55:00")) {
                    Bukkit.broadcastMessage(S_PREFIX + "Der Server startet in 5 Minuten neu.");
                }
                if (df.format(d).equalsIgnoreCase("23:59:00")) {
                    Bukkit.broadcastMessage(S_PREFIX + "Der Server startet in 1 Minute neu.");
                }
                if (df.format(d).equalsIgnoreCase("23:59:30")) {
                    Bukkit.broadcastMessage(S_PREFIX + "Der Server startet in 30 Sekunden neu.");
                }
                if (df.format(d).equalsIgnoreCase("23:59:50")) {
                    Bukkit.broadcastMessage(S_PREFIX + "Der Server startet in 10 Sekunden neu.");
                }
                if (df.format(d).equalsIgnoreCase("23:59:55")) {
                    Bukkit.broadcastMessage(S_PREFIX + "Der Server startet in 5 Sekunden neu.");
                }
                if (df.format(d).equalsIgnoreCase("23:59:56")) {
                    Bukkit.broadcastMessage(S_PREFIX + "Der Server startet in 4 Sekunden neu.");
                }
                if (df.format(d).equalsIgnoreCase("23:59:57")) {
                    Bukkit.broadcastMessage(S_PREFIX + "Der Server startet in 3 Sekunden neu.");
                }
                if (df.format(d).equalsIgnoreCase("23:59:58")) {
                    Bukkit.broadcastMessage(S_PREFIX + "Der Server startet in 2 Sekunden neu.");
                }
                if (df.format(d).equalsIgnoreCase("23:59:59")) {
                    Bukkit.broadcastMessage(S_PREFIX + "Der Server startet in 1 Sekunden neu.");
                }
                if (df.format(d).equalsIgnoreCase("00:00:00")) {
                    Bukkit.broadcastMessage(S_PREFIX + "Der Server startet neu.");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        bungeeUtil.sendToServer(p, config.cfg.getString("Lobby.name"));
                    }
                }
                if (df.format(d).equalsIgnoreCase("00:00:05")) {
                    Bukkit.spigot().restart();
                }
            }
        }, 20L, 20L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            private boolean restart;

            @Override
            public void run() {
                long usedMem = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory();
                long maxMem = Runtime.getRuntime().maxMemory();
                double percent = ((double) usedMem / maxMem) * 100;
                if (percent > 95) {
                    initRestart();
                }
            }

            private void initRestart() {
                if (restart) return;
                restart = true;
                Bukkit.getScheduler().runTaskTimer(getInstance(), new Runnable() {

                    int time = 61;

                    public void run() {
                        time--;
                        switch (time) {
                            case 60:
                                Bukkit.broadcastMessage(S_PREFIX + "§cDer Server startet in §6einer Minute §cneu!");
                                break;

                            case 30:
                                Bukkit.broadcastMessage(S_PREFIX + "§cDer Server startet in §630 Sekunden§c neu!");
                                break;

                            case 20:
                                Bukkit.broadcastMessage(S_PREFIX + "§cDer Server startet in §620 Sekunden§c neu!");
                                break;

                            case 10:
                                Bukkit.broadcastMessage(S_PREFIX + "§cDer Server startet in §610 Sekunden§c neu!");
                                break;

                            case 5:
                                Bukkit.broadcastMessage(S_PREFIX + "§cDer Server startet in §65 Sekunden§c neu!");
                                break;

                            case 4:
                                Bukkit.broadcastMessage(S_PREFIX + "§cDer Server startet in §64 Sekunden§c neu!");
                                break;

                            case 3:
                                Bukkit.broadcastMessage(S_PREFIX + "§cDer Server startet in §63 Sekunden§c neu!");
                                break;

                            case 2:
                                Bukkit.broadcastMessage(S_PREFIX + "§cDer Server startet in §62 Sekunden§c neu!");
                                break;

                            case 1:
                                Bukkit.broadcastMessage(S_PREFIX + "§cDer Server startet in §6einer Sekunde§c neu!");
                                break;

                            case 0:
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    p.kickPlayer("SRM: Server overloaded. Restarting ASAP!");
                                }
                                Bukkit.spigot().restart();
                                break;

                            default:
                                break;
                        }
                    }
                }, 20L, 20L);
            }
        }, 20, 20 * 10);
        plugin = this;
    }
    public static BauFactory getPlugin() {
        return plugin;
    }
    public static WorldEditPlugin getWorldEdit() {
        return (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
    }

    public static WorldGuardPlugin getWorldGuard() {
        return (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
    }

    @Override
    public void onDisable() {
        for (FactoryWorld fw : factoryWorlds.getAll().values()) {
            if (fw.getWrapped() != null) {
                log("Shutdown", "Unloading world " + fw.getWorldAddress());
                fw.unloadWorld();
            }
        }
    }

    public static void log(String section, String msg) {
        Bukkit.getConsoleSender().sendMessage("[WSF | " + section + "] " + msg);
    }

    public static BauFactory getInstance() {
        return JavaPlugin.getPlugin(BauFactory.class);
    }

    public FactoryWorld getWorldByAddress(String address) {
        for (FactoryWorld fw : factoryWorlds.getAll().values()) {
            if (fw.getWorldAddress().equals(address))
                return fw;
        }
        return null;
    }

    public FactoryWorld getWorldFromOwner(UUID ownaz) {
        for (FactoryWorld fw : factoryWorlds.getAll().values()) {
            if (fw.getOwner().equals(ownaz))
                return fw;
        }
        return null;
    }

    @Completer(name = "bau")
    public List<String> completeCommands(CommandArgs args) {
        List<String> ret = new ArrayList<String>();
        String label = args.getCommand().getLabel();
        for (String arg : args.getArgs()) {
            label += " " + arg;
        }
        for (String currentLabel : this.cmdFramework.getCommandLabels()) {
            String current = currentLabel.replace('.', ' ');
            if (currentLabel.contains("BauFactory"))
                continue;
            if (current.contains(label)) {
                current = current.substring(label.lastIndexOf(' ')).trim();
                current = current.substring(0, current.indexOf(' ') != -1 ? current.indexOf(' ') : current.length())
                        .trim();
                if (!ret.contains(current)) {
                    ret.add(current);
                }
            }
        }
        return ret;
    }
    @Completer(name = "gs")
    public List<String> completeCommands1(CommandArgs args) {
        List<String> ret = new ArrayList<String>();
        String label = args.getCommand().getLabel();
        for (String arg : args.getArgs()) {
            label += " " + arg;
        }
        for (String currentLabel : this.cmdFramework.getCommandLabels()) {
            String current = currentLabel.replace('.', ' ');
            if (currentLabel.contains("BauFactory"))
                continue;
            if (current.contains(label)) {
                current = current.substring(label.lastIndexOf(' ')).trim();
                current = current.substring(0, current.indexOf(' ') != -1 ? current.indexOf(' ') : current.length())
                        .trim();
                if (!ret.contains(current)) {
                    ret.add(current);
                }
            }
        }
        return ret;
    }

    public static class CommandInjector {
        private static String packageName = Bukkit.getServer().getClass().getPackage().getName();
        private static String version;

        static {
            version = packageName.substring(packageName.lastIndexOf(".") + 1);
        }

        public static void injectCommand(Command cmd) throws Exception {
            Class serverClass = Class.forName("org.bukkit.craftbukkit." + version + ".CraftServer");
            Field f1 = serverClass.getDeclaredField("commandMap");
            f1.setAccessible(true);
            SimpleCommandMap commandMap = (SimpleCommandMap) f1.get(Bukkit.getServer());
            commandMap.register("gs", cmd);
        }
    }
    static class CommandInjector1 {
        private static final String packageName = Bukkit.getServer().getClass().getPackage().getName();
        private static final String version;

        CommandInjector1() {
        }

        static void injectCommand(Command cmd) throws Exception {
            Class serverClass = Class.forName("org.bukkit.craftbukkit." + version + ".CraftServer");
            Field f1 = serverClass.getDeclaredField("commandMap");
            f1.setAccessible(true);
            SimpleCommandMap commandMap = (SimpleCommandMap)f1.get(Bukkit.getServer());
            commandMap.register("BauSystem", cmd);
        }

        static {
            version = packageName.substring(packageName.lastIndexOf(".") + 1);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.setDeathMessage(null);
    }
    @EventHandler
    public void MinecartdasderShitnetExplodiert(EntityExplodeEvent event)
    {
        if (BauFactory.getInstance().config.cfg.getBoolean("Features.Redstoneclock.AntiTNTMinecartExplosion", true) && (event.getEntityType() == EntityType.MINECART_TNT) && (BauFactory.getInstance().config.cfg.getBoolean("Features.Redstoneclock.AntiTNTMinecartExplosion", true)) && (event.getEntityType() == EntityType.MINECART_TNT)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void MinecartdasderShitnetExplodier(EntityExplodeEvent event)
    {
        if (BauFactory.getInstance().config.cfg.getBoolean("Features.Redstoneclock.AntiTNTMinecartExplosion", true) && (event.getEntityType() == EntityType.MINECART_TNT) && (!BauFactory.getInstance().config.cfg.getBoolean("Features.Redstoneclock.AntiTNTMinecartExplosion", true)) && (event.getEntityType() == EntityType.MINECART_TNT)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event)
    {
        if (BauFactory.getInstance().config.cfg.getBoolean("Features.Redstoneclock.AntiTNTMinecartExplosion", true) && (event.getDamager().getType().equals(EntityType.MINECART_TNT)) &&  (!BauFactory.getInstance().config.cfg.getBoolean("Features.Redstoneclock.AntiTNTMinecartExplosion", true))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        FactoryWorld fw = getWorldByAddress(p.getWorld().getName());
        if (fw != null) {
            e.setRespawnLocation(fw.getWrapped().getSpawnLocation());
        }
    }
    @EventHandler
    public void onJoin1(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.teleport(this.config.spawn);
        WarkingUser user = WarkingUser.get(p.getUniqueId());
        PermissionAttachment attachment = p.addAttachment(this);
        switch(user.getUserGroup()) {
            case Admin:
            case Moderator:
            case Developer:
            case Supporter:
            case Builder:
                attachment.setPermission("bau.team", true);
            default:
                attachment.setPermission("F3NPerm.use", true);
                attachment.setPermission("fawe.permpack.basic", true);
                attachment.setPermission("worldedit.navigation.jumpto.tool", true);
                attachment.setPermission("worldedit.navigation.thru.tool", true);
        }
    }

    public void onJoin(PlayerJoinEvent e) throws SQLException {
        Player p = e.getPlayer();
        p.teleport(config.spawn);
        PermissionAttachment attachment = p.addAttachment(this);
        attachment.setPermission("F3NPerm.use", true);
        attachment.setPermission("fawe.permpack.basic", true);
        attachment.setPermission("worldedit.navigation.jumpto.tool", true);
        attachment.setPermission("worldedit.navigation.thru.tool", true);
    }

    @EventHandler
    public void onTpGM3(PlayerTeleportEvent e) {
        if (e.getPlayer().hasPermission("bukkit.command.teleport")) {
            return;
        }
        if (e.getCause() == TeleportCause.SPECTATE) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(BauFactory.S_PREFIX + "§cDu darfst die GM3 Teleportfunktion nicht benutzen!");
        }
    }

}
