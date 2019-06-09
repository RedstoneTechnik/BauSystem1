package de.redstonetechnik.baufactory.commands;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import com.mojang.authlib.BaseAuthenticationService;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import de.redstonetechnik.baufactory.content.TNTAddon;
import de.redstonetechnik.baufactory.content.UUIDFetcher;
import de.redstonetechnik.baufactory.content.FactoryWorld;
import de.redstonetechnik.baufactory.content.WorldPlayer;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.pro_crafting.commandframework.Command;
import de.pro_crafting.commandframework.CommandArgs;
import de.redstonetechnik.baufactory.main.BauFactory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.omg.CORBA.BAD_CONTEXT;

public class CommandBau {
    @Getter
    private World wrapped;
    @Getter
    private boolean loading;

    @Command(name = "bau", aliases = "gs")
    public void onBau(CommandArgs args) {
        CommandSender s = args.getSender();
        s.sendMessage(BauFactory.S_PREFIX + "BauFactory by RedstoneTechnik v" + "1.9.5");
        s.sendMessage(BauFactory.S_PREFIX + "Um eine Liste mit Befehlen zu erhalten, gib §t/bau help §7ein.");
    }

    @Command(name = "bau.help", aliases = "gs.help", inGameOnly = true)
    public void onHelp(CommandArgs args) {
        Player p = args.getPlayer();
        p.sendMessage(BauFactory.S_PREFIX + "Hilfe Seite 1 von 3:");
        p.sendMessage("§8/bau - §6Zeigt dir Infos über das Plugin");
        p.sendMessage("§8/bau help - §6Zeigt dir diese Liste / Basic commands.");
        p.sendMessage("§8/bau help 2 - §6Zeigt dir alle toggle commands.");
        p.sendMessage("§8/bau help 3 - §6Zeigt alle Extra Commands für deine Welt und für dich selbst. ");
        p.sendMessage("§8/bau home - §6Teleportiert dich auf deine Welt");
        p.sendMessage("§8/bau start - §6Wähle eine deiner Welten aus");
        p.sendMessage("§8/bau tp - §6Teleportiert dich auf eine Welt");
        p.sendMessage("§8/bau info - §6Zeigt dir Infos über die aktuelle Welt");
        p.sendMessage("§8/bau gui - §6Öffnet eine GUI");
        p.sendMessage("§8/bau addmember - §6Fügt einen Spieler zu deiner Welt hinzu");
        p.sendMessage("§8/bau delmember - §6Entfernt einen Spieler von deiner Welt");
    }

    @Command(name = "bau.help.2", aliases = "gs.help.2", inGameOnly = true)
    public void onHelp2(CommandArgs args) {
        Player p = args.getPlayer();
        p.sendMessage(BauFactory.S_PREFIX + "Hilfe Seite 2 von 3:");
        p.sendMessage("§8/bau togglewe - §6Ändert den Zustand für: Darf ein Spieler WorldEdit verwenden?");
        p.sendMessage("§8/bau togglebuild - §6Ändert den Zustand für: Darf ein Spieler bauen?");
        p.sendMessage("§8/bau toggletp - §6Ändert den Zustand für: Darf ein Spieler sich teleportieren?");
        p.sendMessage("§8/bau toggletestblock - §6Ändert den Zustand für : Darf ein Spieler einen TestBlock erneuern");
        p.sendMessage("§8/bau togglereset - §6Ändert den Zustand für : Darf ein Spieler eine Region erneuern");
        p.sendMessage("§8/bau toggleresetall - §6Ändert den Zustand für : Darf ein Spieler eine ganze Welt erneuern");
        p.sendMessage("§8/bau togglereplace - §6Ändert den Zustand für : Darf ein Spieler in einer Region replacen");
        p.sendMessage("§8/bau togglechgm - §6Ändert den Zustand für: Darf ein Spieler seinen Spielmodus ändern?");
        p.sendMessage("§8/bau toggletime - §6Ändert den Zustand für: Darf ein Spieler in deiner Welt die Zeit ändern");
    }
    @Command(name = "bau.help.3", aliases = "gs.help.3", inGameOnly = true)
    public void onHelp3(CommandArgs args) {
        Player p = args.getPlayer();
        p.sendMessage(BauFactory.S_PREFIX + "Hilfe Seite 3 von 3:");
        p.sendMessage("§8/bau testblock - §6Erneuert einen Testblock in der umgebung");
        p.sendMessage("§8/bau reset - §6Setzt eine region zurück wo man sich befindet");
        p.sendMessage("§8/bau resetall - §6Resetet deine komplette Welt");
        p.sendMessage("§8/bau replace - §6Ersetzt in einer Region Obsidian zu TNT und Bedrock zu Schleim");
        p.sendMessage("§8/bau tntdmg - §6Erlaubt / verbietet TNT Explosionen");
        p.sendMessage("§8/bau firedmg - §6Erlaubt / verbietet Feuer Schaden");
        p.sendMessage("§8/bau flyspeed - §6Um dein FlySpeed zu ändern");
        p.sendMessage("§8/bau nv - §6aktiviert oder deaktiviert Nachtsicht");
        p.sendMessage("§8/bau trace - §6Gibt einen Überblick über den TNT-Tracer");
        p.sendMessage("§8/bau time - §6Ändert die Zeit auf deinem Grundstück.");
    }

    @Command(name = "bau.home", inGameOnly = true)
    public boolean onHome(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getUniqueId().toString());
        if (fw == null) {
            fw = new FactoryWorld(p.getUniqueId());
            BauFactory.getInstance().factoryWorlds.register(fw);
        }
        if (fw.isLoading()) {
            p.sendMessage(BauFactory.S_PREFIX + "§aEinen Moment bitte... Deine Welt wird vorbereitet.");
            return true;
        }
        try {
            loading = true;
            p.sendMessage(BauFactory.S_PREFIX + "§aEinen Moment bitte... Deine Welt wird vorbereitet.");
            File world = new File("plugins/BauSystem/worlds/" + p.getUniqueId());
            File region = new File("plugins/WorldGuard/worlds/" + p.getUniqueId());
            if (region.exists() && region.isDirectory()) {
            } else {
                FileUtils.copyDirectory(new File(BauFactory.getInstance().config.regionDir), new File("plugins/WorldGuard/worlds/" + p.getUniqueId()));
            }
            if (world.exists() && world.isDirectory()) {
                FileUtils.moveDirectory(world, new File(p.getUniqueId().toString()));
                wrapped = Bukkit.createWorld(new WorldCreator(p.getUniqueId().toString()));
            } else {
                FileUtils.copyDirectory(new File(BauFactory.getInstance().config.backupDir), new File(p.getUniqueId().toString()));
                wrapped = Bukkit.createWorld(new WorldCreator(p.getUniqueId().toString()));
            }
            loading = false;
        } catch (Exception e) {
            p.sendMessage(BauFactory.S_PREFIX + "§cBeim Laden der Welt ist ein Fehler aufgetreten.");
            e.printStackTrace();
        }
        p.getInventory().clear();
        Bukkit.getScheduler().scheduleSyncDelayedTask(BauFactory.getInstance(), new Runnable() {
            public void run() {
                p.teleport(Bukkit.getWorld(p.getUniqueId().toString()).getSpawnLocation());
                p.setGameMode(GameMode.CREATIVE);
            }
        }, 20L);return true;

    }

    @Command(name = "bau.start", inGameOnly = true)
    public boolean onstart(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getUniqueId().toString());
        if (fw == null) {
            fw = new FactoryWorld(p.getUniqueId());
            BauFactory.getInstance().factoryWorlds.register(fw);
        }
        if (Bukkit.getWorld(p.getUniqueId()) == Bukkit.getWorld(p.getUniqueId())) {
            Inventory inv = Bukkit.createInventory(null, 9, BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Titel"));
            ItemStack mittelalter = new ItemStack(Material.COBBLESTONE);
            ItemMeta meta = mittelalter.getItemMeta();
            meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item1.Titel"));
            mittelalter.setItemMeta(meta);
            inv.addItem(mittelalter);
            ItemStack steampunk = new ItemStack(Material.IRON_BLOCK);
            meta = steampunk.getItemMeta();
            meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item2.Titel"));
            steampunk.setItemMeta(meta);
            inv.setItem(2, steampunk);
            ItemStack fantasie = new ItemStack(Material.PURPUR_BLOCK);
            meta = fantasie.getItemMeta();
            meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item3.Titel"));
            fantasie.setItemMeta(meta);
            inv.setItem(4, fantasie);
            ItemStack modern = new ItemStack(Material.EMERALD_BLOCK);
            meta = modern.getItemMeta();
            meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item4.Titel"));
            modern.setItemMeta(meta);
            inv.setItem(6, modern);
            ItemStack island = new ItemStack(Material.SAND);
            meta = island.getItemMeta();
            meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item5.Titel"));
            island.setItemMeta(meta);
            inv.setItem(8, island);
            p.openInventory(inv);
        }return true;
    }

    @Command(name = "bau.addmember", inGameOnly = true)
    public void onAdd(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu hast noch keine Welt.");
            return;
        }
        if (args.length() == 0) {
            p.sendMessage(BauFactory.S_PREFIX + "§bBenutzung: /bau addmember <Spieler>");
            return;
        }
        UUID id = UUIDFetcher.getUUID(args.getArgs(0));
        if (id == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cUnbekannter Spieler");
            return;
        }
        if (fw.getMember(id) != null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDieser Spieler ist bereits Mitglied auf deiner Welt");
            return;
        }
        WorldPlayer wp = new WorldPlayer(id, fw);
        wp.canBuild = false;
        fw.getMembers().register(wp);
        p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler wurde zu deiner Welt hinzugefügt.");
    }

    @Command(name = "bau.delmember", inGameOnly = true)
    public void onDel(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu hast noch keine Welt.");
            return;
        }
        if (args.length() == 0) {
            p.sendMessage(BauFactory.S_PREFIX + "§bBenutzung: /bau delmember <Spieler>");
            return;
        }
        UUID id = UUIDFetcher.getUUID(args.getArgs(0));
        if (id == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cUnbekannter Spieler");
            return;
        }
        if (fw.getMember(id) == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDieser Spieler ist kein Mitglied auf deiner Welt");
            return;
        }
        fw.getMembers().unregister(fw.getMembers().getID(fw.getMember(id)));
        p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler wurde entfernt.");
        Player z = Bukkit.getPlayer(id);
        if (z != null) {
            if (z.getWorld().getUID().equals(fw.getWrapped().getUID())) {
                z.teleport(BauFactory.getInstance().config.spawn);
                z.getInventory().clear();
            }
        }
    }

    @Command(name = "bau.tp", inGameOnly = true)
    public void onTp(CommandArgs args) {
        Player p = args.getPlayer();
        if (args.length() == 0) {
            p.sendMessage(BauFactory.S_PREFIX + "§bBenutzung: /bau tp <Spieler>");
            return;
        }
        UUID id = UUIDFetcher.getUUID(args.getArgs(0));
        if (id == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cUnbekannter Spieler");
            return;
        }
        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(id);
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler hat keine Welt");
            return;
        }
        try {
            if (!p.hasPermission("bau.team")) {
                if (fw.getMember(p.getUniqueId()) == null) {
                    p.sendMessage(BauFactory.S_PREFIX + "§cDu bist kein Mitglied dieser Welt");
                    return;
                }
            }
            if (fw.getWrapped() == null) {
                if (Bukkit.getWorld(id) == null)
                    try {
                        p.sendMessage(BauFactory.S_PREFIX + "§aEinen Moment bitte... Deine Welt wird vorbereitet.");
                        loading = true;
                        File world = new File("plugins/BauSystem/worlds/" + id);
                        File region = new File("plugins/WorldGuard/worlds/" + id);
                        if (region.exists() && region.isDirectory()) {
                        } else {
                            FileUtils.copyDirectory(new File(BauFactory.getInstance().config.regionDir), new File("plugins/WorldGuard/worlds/" + id));
                        }
                        if (world.exists() && world.isDirectory()) {
                            FileUtils.moveDirectory(world, new File("" + id));
                            wrapped = Bukkit.createWorld(new WorldCreator("" + id));
                        } else {
                            FileUtils.copyDirectory(new File(BauFactory.getInstance().config.backupDir), new File("" + id));
                            wrapped = Bukkit.createWorld(new WorldCreator("" + id));
                        }
                        loading = false;
                    } catch (Exception e) {
                        p.sendMessage(BauFactory.S_PREFIX + "§cBeim Laden der Welt ist ein Fehler aufgetreten.");
                        e.printStackTrace();
                    }
            }
            p.getInventory().clear();
            p.teleport(Bukkit.getWorld(id.toString()).getSpawnLocation());
            Bukkit.getScheduler().scheduleSyncDelayedTask(BauFactory.getInstance(), new Runnable() {
                public void run() {
                    p.setGameMode(GameMode.CREATIVE);
                }
            }, 20);
            p.sendMessage(BauFactory.S_PREFIX + "§aDu wurdest teleportiert.");
        } catch (Exception e) {
            p.sendMessage(BauFactory.S_PREFIX + "§cFehler.");
            e.printStackTrace();
        }
    }
    @Command(name = "bau.time", inGameOnly = true)
    public boolean onTime(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
        FactoryWorld fw1 = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
        if (fw1 == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu befindest dich auf keiner Bauwelt!");
            return false;
        } else {
            WorldPlayer wp = fw.getMember(p.getUniqueId());
            if (!fw.getOwner().toString().equalsIgnoreCase(p.getUniqueId().toString()) && wp.canTime == false) {
                p.sendMessage("§7Du darfst in dieser Welt nicht die Zeit ändern!");
                return false;
            }
            if (args.length() == 0) {
                p.sendMessage(BauFactory.S_PREFIX + "/bau time <Zeit 0=Morgen, 6000=Mittag, 18000=Mitternacht>");
                p.sendMessage("");
                p.sendMessage(BauFactory.S_PREFIX + "Deine Aktuelle Zeit in der Welt wo du dich befindest beträgt §6" + p.getWorld().getTime());
            } else {
                if (fw1.getOwner().toString() == p.getUniqueId().toString()) {
                    p.sendMessage(BauFactory.S_PREFIX + "§cDu darfst hier nicht die Zeit ändern");
                } else {
                    int time;
                    try {
                        time = Integer.valueOf(args.getArgs(0));
                    } catch (NumberFormatException var6) {
                        p.sendMessage(BauFactory.S_PREFIX + "§cBitte gib eine Zahl zwischen 0 und 24000 an");
                        return false;
                    }

                    if (time >= 0 && time <= 24000) {
                        p.getWorld().setTime((long) time);
                        p.sendMessage(BauFactory.S_PREFIX + "Du hast die Zeit in deiner Welt auf §6" + (args.getArgs(0)) + " §7gesetzt.");
                    } else {
                        p.sendMessage(BauFactory.S_PREFIX + "§cBitte gib eine Zahl zwischen 0 und 24000 an");
                    }
                }
            }
            return false;
        }
    }

    @Command(name = "bau.toggletime", aliases = "gs.toggletime", inGameOnly = true)
    public void onToggleTime(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu hast keine Welt");
            return;
        }
        if (args.length() == 0) {
            p.sendMessage(BauFactory.S_PREFIX + "§bBenutzung: /bau toggletime <Spieler>");
            return;
        }
        UUID id = UUIDFetcher.getUUID(args.getArgs(0));
        if (id == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cUnbekannter Spieler");
            return;
        }
        WorldPlayer wp = fw.getMember(id);
        if (wp == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist kein Mitglied deiner Welt");
            return;
        }
        wp.canTime = !wp.canTime;
        Player z = Bukkit.getPlayer(id);
        if (z != null) {
            if (wp.canTime) {
                z.sendMessage(BauFactory.S_PREFIX + "§aDu kannst nun auf der Welt von §6" + p.getName() + "§a die Zeit ändern.");
            } else {
                z.sendMessage(BauFactory.S_PREFIX + "§cDu kannst nun nicht mehr auf der Welt von §6" + p.getName() + "§c die Zeit ändern.");
            }
        }
        if (wp.canTime) {
            p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf nun deine Zeit auf deiner Welt ändern.");
        } else {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler darf nun auf deiner Welt die Zeit nicht mehr ändern.");
        }
    }

    @Command(name = "bau.toggletestblock", aliases = "gs.toggletestblock", inGameOnly = true)
    public void onToggleTestblock(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu hast keine Welt");
            return;
        }
        if (args.length() == 0) {
            p.sendMessage(BauFactory.S_PREFIX + "§bBenutzung: /bau toggletestblock <Spieler>");
            return;
        }
        UUID id = UUIDFetcher.getUUID(args.getArgs(0));
        if (id == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cUnbekannter Spieler");
            return;
        }
        WorldPlayer wp = fw.getMember(id);
        if (wp == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist kein Mitglied deiner Welt");
            return;
        }
        wp.canTestblock = !wp.canTestblock;
        Player z = Bukkit.getPlayer(id);
        if (z != null) {
            if (wp.canTestblock) {
                z.sendMessage(BauFactory.S_PREFIX + "§aDu kannst nun auf der Welt von §6" + p.getName() + "§a die TestBlöcke erneuern.");
            } else {
                z.sendMessage(BauFactory.S_PREFIX + "§cDu kannst nun nicht mehr auf der Welt von §6" + p.getName() + "§c die TestBlöcke erneuern.");
            }
        }
        if (wp.canTestblock) {
            p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf nun deine TestBlöcke erneuern.");
        } else {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler darf keine TestBlöcke mehr reseten.");
        }
    }

    @Command(name = "bau.togglereset", aliases = "gs.togglereset", inGameOnly = true)
    public void onTogglereset(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu hast keine Welt");
            return;
        }
        if (args.length() == 0) {
            p.sendMessage(BauFactory.S_PREFIX + "§bBenutzung: /bau togglereset <Spieler>");
            return;
        }
        UUID id = UUIDFetcher.getUUID(args.getArgs(0));
        if (id == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cUnbekannter Spieler");
            return;
        }
        WorldPlayer wp = fw.getMember(id);
        if (wp == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist kein Mitglied deiner Welt");
            return;
        }
        wp.canReset = !wp.canReset;
        Player z = Bukkit.getPlayer(id);
        if (z != null) {
            if (wp.canReset) {
                z.sendMessage(BauFactory.S_PREFIX + "§aDu kannst nun auf der Welt von §6" + p.getName() + "§a die Regionen erneuern.");
            } else {
                z.sendMessage(BauFactory.S_PREFIX + "§cDu kannst nun nicht mehr auf der Welt von §6" + p.getName() + "§c die Regionen erneuern.");
            }
        }
        if (wp.canReset) {
            p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf nun deine Regionen erneuern.");
        } else {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler darf deine Regionen nicht mehr erneuern.");
        }
    }

    @Command(name = "bau.togglereplace", aliases = "gs.togglereplace", inGameOnly = true)
    public void onTogglereplace(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu hast keine Welt");
            return;
        }
        if (args.length() == 0) {
            p.sendMessage(BauFactory.S_PREFIX + "§bBenutzung: /bau togglereplace <Spieler>");
            return;
        }
        UUID id = UUIDFetcher.getUUID(args.getArgs(0));
        if (id == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cUnbekannter Spieler");
            return;
        }
        WorldPlayer wp = fw.getMember(id);
        if (wp == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist kein Mitglied deiner Welt");
            return;
        }
        wp.canReplace = !wp.canReplace;
        Player z = Bukkit.getPlayer(id);
        if (z != null) {
            if (wp.canReplace) {
                z.sendMessage(BauFactory.S_PREFIX + "§aDu kannst nun auf der Welt von §6" + p.getName() + "§a TNT und Schleim replacen.");
            } else {
                z.sendMessage(BauFactory.S_PREFIX + "§cDu kannst nun nicht mehr auf der Welt von §6" + p.getName() + "§c TNT und Schleim replacen.");
            }
        }
        if (wp.canReplace) {
            p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf nun in diener Welt TNT und Schleim replacen.");
        } else {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler darf nun in deiner Welt kein TNT und Schleim mehr replacen.");
        }
    }

    @Command(name = "bau.togglewe", inGameOnly = true)
    public void onToggleWE(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu hast keine Welt");
            return;
        }
        if (args.length() == 0) {
            p.sendMessage(BauFactory.S_PREFIX + "§bBenutzung: /bau togglewe <Spieler>");
            return;
        }
        UUID id = UUIDFetcher.getUUID(args.getArgs(0));
        if (id == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cUnbekannter Spieler");
            return;
        }
        WorldPlayer wp = fw.getMember(id);
        if (wp == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist kein Mitglied deiner Welt");
            return;
        }
        wp.canWorldEdit = !wp.canWorldEdit;
        Player z = Bukkit.getPlayer(id);
        if (z != null) {
            if (wp.canWorldEdit) {
                z.sendMessage(BauFactory.S_PREFIX + "§aDu kannst nun auf der Welt von §6" + p.getName() + "§a WorldEdit verwenden.");
            } else {
                z.sendMessage(BauFactory.S_PREFIX + "§cDu kannst nun nicht mehr auf der Welt von §6" + p.getName() + "§c WorldEdit verwenden.");
            }
        }
        if (wp.canWorldEdit) {
            p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf WorldEdit verwenden.");
        } else {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler darf kein WorldEdit verwenden.");
        }
    }


    @Command(name = "bau.toggletp", inGameOnly = true)
    public void onToggleTP(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu hast keine Welt");
            return;
        }
        if (args.length() == 0) {
            p.sendMessage(BauFactory.S_PREFIX + "§bBenutzung: /bau toggletp <Spieler>");
            return;
        }
        UUID id = UUIDFetcher.getUUID(args.getArgs(0));
        if (id == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cUnbekannter Spieler");
            return;
        }
        WorldPlayer wp = fw.getMember(id);
        if (wp == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist kein Mitglied deiner Welt");
            return;
        }
        wp.canTeleport = !wp.canTeleport;
        Player z = Bukkit.getPlayer(id);
        if (z != null) {
            if (wp.canTeleport) {
                z.sendMessage(BauFactory.S_PREFIX + "§aDu kannst nun auf der Welt von §6" + p.getName() + "§a /tp verwenden.");
            } else {
                z.sendMessage(BauFactory.S_PREFIX + "§cDu kannst nun nicht mehr auf der Welt von §6" + p.getName() + "§c /tp verwenden.");
            }
        }
        if (wp.canTeleport) {
            p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf sich teleportieren.");
        } else {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler darf sich nicht teleportieren.");
        }
    }

    @Command(name = "bau.togglebuild", inGameOnly = true)
    public void onToggleBD(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu hast keine Welt");
            return;
        }
        if (args.length() == 0) {
            p.sendMessage(BauFactory.S_PREFIX + "§bBenutzung: /bau togglebuild <Spieler>");
            return;
        }
        UUID id = UUIDFetcher.getUUID(args.getArgs(0));
        if (id == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cUnbekannter Spieler");
            return;
        }
        WorldPlayer wp = fw.getMember(id);
        if (wp == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist kein Mitglied deiner Welt");
            return;
        }
        wp.canBuild = !wp.canBuild;
        Player z = Bukkit.getPlayer(id);
        if (z != null) {
            if (wp.canBuild) {
                z.sendMessage(BauFactory.S_PREFIX + "§aDu kannst nun auf der Welt von §6" + p.getName() + "§a bauen.");
            } else {
                z.sendMessage(BauFactory.S_PREFIX + "§cDu kannst nun nicht mehr auf der Welt von §6" + p.getName() + "§c bauen.");
            }
        }
        if (wp.canBuild) {
            p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf bauen.");
        } else {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler darf nicht bauen.");
        }
    }

    @Command(name = "bau.togglechgm", inGameOnly = true)
    public void onToggleCHG(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu hast keine Welt");
            return;
        }
        if (args.length() == 0) {
            p.sendMessage(BauFactory.S_PREFIX + "§bBenutzung: /bau togglechgm <Spieler>");
            return;
        }
        UUID id = UUIDFetcher.getUUID(args.getArgs(0));
        if (id == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cUnbekannter Spieler");
            return;
        }
        WorldPlayer wp = fw.getMember(id);
        if (wp == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist kein Mitglied deiner Welt");
            return;
        }
        wp.canChangeGamemode = !wp.canChangeGamemode;
        Player z = Bukkit.getPlayer(id);
        if (z != null) {
            if (wp.canChangeGamemode) {
                z.sendMessage(BauFactory.S_PREFIX + "§aDu kannst nun auf der Welt von §6" + p.getName() + "§a deinen Spielmodus ändern.");
            } else {
                z.sendMessage(BauFactory.S_PREFIX + "§cDu kannst nun nicht mehr auf der Welt von §6" + p.getName() + "§c deinen Spielmodus ändern.");
            }
        }
        if (wp.canChangeGamemode) {
            p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf seinen Spielmodus ändern.");
        } else {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler darf nicht seinen Spielmodus ändern.");
        }
    }

    @Command(name = "bau.info", aliases = "gs.info", inGameOnly = true)
    public boolean onInfo(CommandArgs args) {
        final Player p = args.getPlayer();
        final FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu befindest dich auf keiner Bauwelt!");
            return false;
        }
            Bukkit.getScheduler().runTaskAsynchronously(BauFactory.getInstance(), new Runnable() {
                public void run() {
                    StringBuilder members = new StringBuilder();
                    String ownaz = fw.getWorldAddress();
                    for (WorldPlayer wp : fw.getMembers().getAll().values()) {
                        members.append(UUIDFetcher.getName(wp.getUuid()) + ", ");
                    }
                    String membas = "";
                    if (!members.toString().isEmpty()) {
                        membas = members.toString().trim().substring(0, members.toString().trim().length() - 1);
                    }
                    membas = membas.isEmpty() ? "Keine" : membas;
                    p.sendMessage(BauFactory.S_PREFIX + "§3--- §6Informationen §3---");
                    p.sendMessage("");
                    p.sendMessage("§8Registrierungsnummer: §6" + BauFactory.getInstance().factoryWorlds.getID(fw));
                    p.sendMessage("§8Besitzer: §6" + ownaz);
                    p.sendMessage("§8Mitglieder: §6" + membas);
                    p.sendMessage("");
                    if (fw.isTntDamage()) {
                        p.sendMessage("§8TNT Schaden: §6" + "§aTNT Schaden erlaubt.");
                    } else {
                        p.sendMessage("§8TNT Schaden: §6" + "§cTNT Schaden verboten.");
                    }
                    if (fw.isFireDamage()) {
                        p.sendMessage("§8Feuer Schaden: §6" + "§aFeuer Schaden erlaubt.");
                    } else {
                        p.sendMessage("§8Feuer Schaden: §6" + "§cFeuer Schaden verboten.");
                    }
                }
            });return false;
    }

    @Command(name = "bau.tntdmg", aliases = "gs.tntdmg", inGameOnly = true)
    public void onTntdmg(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu hast keine Welt");
            return;
        }
        fw.setTntDamage(!fw.isTntDamage());
        if (fw.isTntDamage()) {
            p.sendMessage(BauFactory.S_PREFIX + "§aTNT Schaden erlaubt.");
        } else {
            p.sendMessage(BauFactory.S_PREFIX + "§cTNT Schaden verboten.");
        }
    }

    @Command(name = "bau.firedmg", aliases = "gs.firedmg", inGameOnly = true)
    public void onFiredmg(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu hast keine Welt");
            return;
        }
        fw.setFireDamage(!fw.isFireDamage());
        if (fw.isFireDamage()) {
            p.sendMessage(BauFactory.S_PREFIX + "§aFeuer Schaden erlaubt.");
        } else {
            p.sendMessage(BauFactory.S_PREFIX + "§cFeuer Schaden verboten.");
        }
    }

    @Command(name = "bau.flyspeed", aliases = "gs.flyspeed", inGameOnly = true)
    public void onflyspeed(CommandArgs args) {
        Player player = args.getPlayer();
        if (args.length() == 0) {
            player.sendMessage(BauFactory.S_PREFIX + "§7/bau flyspeed [Geschwindigkeit]");
        } else {
            float speed;
            try {
                speed = Float.valueOf(args.getArgs(0));
            } catch (NumberFormatException var5) {
                player.sendMessage(BauFactory.S_PREFIX + "§cBitte gib eine Zahl zwischen 0 und 10 an");
                return;
            }

            if (speed >= 0.0F && speed <= 10.0F) {
                player.sendMessage(BauFactory.S_PREFIX + "§aGeschwindigkeit wurde auf §6" + speed + " §agesetzt");
                if (speed == 1.0F) {
                    speed = 1.25F;
                }

                player.setFlySpeed(speed / 10.0F);
            } else {
                player.sendMessage(BauFactory.S_PREFIX + "§cBitte gib eine Zahl zwischen 0 und 10 an");
            }
        }
    }

    @Command(name = "bau.gui", aliases = "gs.gui", inGameOnly = true)
    public boolean ongui(CommandArgs args) {
        final Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
        if (BauFactory.getInstance().config.cfg.getBoolean("Commands.GUI.Einstellung", false)) {
            if (fw == null) {
                p.sendMessage(BauFactory.S_PREFIX + "§cDu befindest dich auf keiner Bauwelt!");
                return false;
            } else {
                Inventory inv = Bukkit.createInventory(null, 9, "§aBau Optionen");
                ItemStack bau = new ItemStack(Material.COMPASS);
                ItemMeta meta = bau.getItemMeta();
                meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.mainmenu.Item1.Titel"));
                bau.setItemMeta(meta);
                inv.addItem(bau);
                ItemStack optionen = new ItemStack(Material.ENCHANTED_BOOK);
                meta = optionen.getItemMeta();
                meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.mainmenu.Item2.Titel"));
                optionen.setItemMeta(meta);
                inv.setItem(8, optionen);
                ItemStack spieler = new ItemStack(Material.REDSTONE_BLOCK);
                meta = spieler.getItemMeta();
                meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.mainmenu.Item3.Titel"));
                spieler.setItemMeta(meta);
                inv.setItem(4, spieler);
                p.openInventory(inv);
                return true;
        }
        }else {
                 p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("Commands.GUI.Message"));
        }return true;
    }

    @Command(name = "bau.testblock", aliases = "gs.testblock", inGameOnly = true)
    public boolean ontestblock(CommandArgs args) {
        final Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu befindest dich auf keiner Bauwelt!");
            return false;
        } else {
            WorldPlayer wp = fw.getMember(p.getUniqueId());
                if (!fw.getOwner().toString().equalsIgnoreCase(p.getUniqueId().toString()) && wp.canTestblock == false) {
                    p.sendMessage("§7Du darfst den TestBlock nicht erneuern!");
                    return false;
                }
                Location playerLocation = p.getLocation();
                World playerWorld = p.getWorld();

                @SuppressWarnings("unused")
                int count = 0;
                for (ProtectedRegion rg : BauFactory.getPlugin().getWorldGuard().getRegionManager(playerWorld).getApplicableRegions(playerLocation)) {
                    count++;

                    String rgID = rg.getId().toString();

                    if (rgID.equalsIgnoreCase("resetall")) {
                        return true;
                    }
                    Vector position = new Vector(Integer.parseInt(BauFactory.getPlugin().getRegionData.getString("regions." + rgID + ".position.X")), Integer.parseInt(BauFactory.getPlugin().getRegionData.getString("regions." + rgID + ".position.Y")), Integer.parseInt(BauFactory.getPlugin().getRegionData.getString("regions." + rgID + ".position.Z")));
                    File file = new File(BauFactory.getPlugin().getRegionData.getString("regions." + rgID + ".schematic.testblock"));

                    pasteSchematic(p, file, position);
                    p.sendMessage(BauFactory.S_PREFIX + "Testblock erneuert!");

                }

            }
            return false;
    }
    @Command(name = "bau.testblock.changeschem", aliases = "gs.testblock.changeschem", inGameOnly = true)
    public boolean onchangeschem(CommandArgs args) {
        final Player p = args.getPlayer();
        if (args.length() == 0) {
            p.sendMessage(BauFactory.S_PREFIX + "§bBenutzung: /bau testblock changeschem <Schematic>");
            return false;
        }
        if (p.getWorld().getName().toString().equals(p.getUniqueId().toString())) {
            p.sendMessage(BauFactory.S_PREFIX + "Du befindest dich nicht auf deiner eigenen Bauwelt dieser Befehl geht nur bei deiner Welt.");
            return false;
        } else {

        }return true;
    }
    @Command(name = "bau.reset", aliases = "gs.reset", inGameOnly = true)
    public boolean onReset(CommandArgs args) {
        final Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu befindest dich auf keiner Bauwelt!");
            return false;
        } else {
            WorldPlayer wp = fw.getMember(p.getUniqueId());
            if (!fw.getOwner().toString().equalsIgnoreCase(p.getUniqueId().toString()) && wp.canReset == false) {
                p.sendMessage(BauFactory.S_PREFIX  + "§7Du darfst keine Regionen erneuern");
                return false;
                }
            }
            Location playerLocation = p.getLocation();
            World playerWorld = p.getWorld();

            @SuppressWarnings("unused")
            int count = 0;
            for (ProtectedRegion rg : BauFactory.getPlugin().getWorldGuard().getRegionManager(playerWorld).getApplicableRegions(playerLocation)) {
                count++;

                String rgID = rg.getId().toString();

                if(rgID.equalsIgnoreCase("resetall")) {
                    return true;
                }
                Vector position = new Vector(Integer.parseInt(BauFactory.getPlugin().getRegionData.getString("regions." + rgID + ".position.X")), Integer.parseInt(BauFactory.getPlugin().getRegionData.getString("regions." + rgID + ".position.Y")), Integer.parseInt(BauFactory.getPlugin().getRegionData.getString("regions." + rgID + ".position.Z")));
                File file = new File(BauFactory.getPlugin().getRegionData.getString("regions." + rgID + ".schematic.reset"));

                pasteSchematic(p, file, position);
                p.sendMessage(BauFactory.S_PREFIX + "Region " + rgID + " wurde resettet!");

            }
        return false;
    }
    @Command(name = "bau.resetall", aliases = "gs.resetall", inGameOnly = true)
    public boolean onresetall(CommandArgs args) {
        final Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
        if (fw.getOwner().toString() == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu stehst auf keiner Bauwelt");
            return false;
        } else {
            for (Player ap : Bukkit.getOnlinePlayers()) {
                if (ap.getWorld().getName() == p.getName()) {

                    Location location = new Location(Bukkit.getWorld("Bauspawn"), 1442, 113, 228, -151.9F, -4.2F);
                    ap.teleport(Bukkit.getWorld(p.getUniqueId().toString()).getSpawnLocation());
                    ap.sendMessage(BauFactory.S_PREFIX + "Die Welt wird erneuert!");
                }
            }
            if (!unloadWorld(p.getUniqueId().toString())) {
                BauFactory.log("FactoryWorld", "Unable to unload ");
            }

            File directory = new File("" + p.getUniqueId());
            del(directory);
            File directory1 = new File("plugins/WorldGuard/worlds/" + p.getUniqueId());
            del(directory1);
            if (Bukkit.getWorld(p.getUniqueId()) == null)
                try {
                    p.sendMessage(BauFactory.S_PREFIX + "§aEinen Moment bitte... Deine Welt wird vorbereitet.");
                    loading = true;
                    File world = new File("plugins/BauSystem/worlds/" + p.getUniqueId());
                    File region = new File("plugins/WorldGuard/worlds/" + p.getUniqueId());
                    if (region.exists() && region.isDirectory()) {
                    } else {
                        FileUtils.copyDirectory(new File(BauFactory.getInstance().config.regionDir), new File("plugins/WorldGuard/worlds/" + p.getUniqueId()));
                    }
                    if (world.exists() && world.isDirectory()) {
                        FileUtils.moveDirectory(world, new File("" + p.getUniqueId()));
                        wrapped = Bukkit.createWorld(new WorldCreator("" + p.getUniqueId()));
                    } else {
                        FileUtils.copyDirectory(new File(BauFactory.getInstance().config.backupDir), new File("" + p.getUniqueId()));
                        wrapped = Bukkit.createWorld(new WorldCreator("" + p.getUniqueId()));
                    }
                    loading = false;
                } catch (Exception e) {
                    p.sendMessage(BauFactory.S_PREFIX + "§cBeim Laden der Welt ist ein Fehler aufgetreten.");
                    e.printStackTrace();
                }
            p.teleport(Bukkit.getWorld(p.getUniqueId().toString()).getSpawnLocation());
            Bukkit.getScheduler().scheduleSyncDelayedTask(BauFactory.getInstance(), new Runnable() {
                public void run () {
                    p.setGameMode(GameMode.CREATIVE);
                }
            },20L);
        }
        return false;
    }

    @Command(name = "bau.trace", inGameOnly = true)
    public void onTrace(CommandArgs args) {
        Player p = args.getPlayer();
            if (BauFactory.getInstance().config.cfg.getBoolean("Commands.TraceSystem.Einstellung", false)) {

                p.sendMessage(BauFactory.S_PREFIX + "§8/bau trace start §6- Startet die Aufnahme aller TNT-Positionen");
                p.sendMessage(BauFactory.S_PREFIX + "§8/bau trace show §6- Zeigt alle TNT-Positionen");
                p.sendMessage(BauFactory.S_PREFIX + "§8/bau trace hide §6- Versteckt die TNT-Positionen wieder");
                p.sendMessage(BauFactory.S_PREFIX + "§8/bau trace stop §6- Bricht die Aufnahme ab");
            } else {
                p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("Commands.TraceSystem.Message"));
            }
        }

    @Command(name = "bau.trace.start", inGameOnly = true)
    public void onStart(CommandArgs args) {
        Player p = args.getPlayer();
            FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
        if (BauFactory.getInstance().config.cfg.getBoolean("Commands.TraceSystem.Einstellung", false)) {
            if (!fw.getOwner().toString().equalsIgnoreCase(p.getUniqueId().toString())) {
                p.sendMessage(BauFactory.S_PREFIX + "§7§cDu darfst hier nicht den TNT-Tracer nutzen");
            } else {
                TNTAddon.newTracer(p.getWorld());
                p.sendMessage(BauFactory.S_PREFIX + "§7§aAufnahme gestartet");
            }
        } else {
            p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("Commands.TraceSystem.Message"));
        }
    }

    @Command(name = "bau.trace.stop", inGameOnly = true)
    public void onStop(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
        if (BauFactory.getInstance().config.cfg.getBoolean("Commands.TraceSystem.Einstellung", false)) {
            if (!fw.getOwner().toString().equalsIgnoreCase(p.getUniqueId().toString())) {
                p.sendMessage(BauFactory.S_PREFIX + "§7§cDu darfst hier nicht den TNT-Tracer nutzen");
            } else {
                TNTAddon t = TNTAddon.get(p.getWorld());
                if (t == null) {
                    p.sendMessage(BauFactory.S_PREFIX + "§7§cKein laufender TNT-Tracer");
                } else {
                    t.end();
                    p.sendMessage(BauFactory.S_PREFIX + "§7§aAufnahme abgebrochen");
                }
            }
        } else {
            p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("Commands.TraceSystem.Message"));
        }
    }

    @Command(name = "bau.trace.show", inGameOnly = true)
    public void onShow(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
        if (BauFactory.getInstance().config.cfg.getBoolean("Commands.TraceSystem.Einstellung", false)) {
            if (!fw.getOwner().toString().equalsIgnoreCase(p.getUniqueId().toString())) {
                p.sendMessage(BauFactory.S_PREFIX + "§7§cDu darfst hier nicht den TNT-Tracer nutzen");
            } else {
                TNTAddon t = TNTAddon.get(p.getWorld());
                if (t == null) {
                    p.sendMessage(BauFactory.S_PREFIX + "§7§cKein laufender TNT-Tracer");
                } else {
                    t.show();
                    p.sendMessage(BauFactory.S_PREFIX + "§7§aTNT-Positionen angezeigt");
                }
            }
        } else {
            p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("Commands.TraceSystem.Message"));
        }
    }
    @Command(name = "bau.trace.hide", inGameOnly = true)
    public void onHide(CommandArgs args) {
        Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
        if (BauFactory.getInstance().config.cfg.getBoolean("Commands.TraceSystem.Einstellung", false)) {
            if (!fw.getOwner().toString().equalsIgnoreCase(p.getUniqueId().toString())) {
                p.sendMessage(BauFactory.S_PREFIX + "§7§cDu darfst hier nicht den TNT-Tracer nutzen");
            } else {
                TNTAddon t = TNTAddon.get(p.getWorld());
                if (t == null) {
                    p.sendMessage(BauFactory.S_PREFIX + "§7§cKein laufender TNT-Tracer");
                } else {
                    t.hide();
                    p.sendMessage(BauFactory.S_PREFIX + "§7§aTNT-Positionen versteckt");
                }
            }
        } else {
            p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("Commands.TraceSystem.Message"));
        }
    }
    @Command(name = "bau.nv", aliases = {"nv"}, inGameOnly = true)
    public void onNightVision(CommandArgs args) {
        Player p = args.getPlayer();
        Iterator var3 = p.getActivePotionEffects().iterator();

        PotionEffect effect;
        do {
            if (!var3.hasNext()) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 255, false, false));
                p.sendMessage(BauFactory.S_PREFIX + "§7Nachtsicht aktiviert");
                return;
            }

            effect = (PotionEffect)var3.next();
        } while(!effect.getType().equals(PotionEffectType.NIGHT_VISION));

        p.sendMessage(BauFactory.S_PREFIX + "§7Nachtsicht deaktiviert");
        p.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }
    @Command(name = "bau.replace", aliases = "gs.replace", inGameOnly = true)
    public boolean onreplace(CommandArgs args) {
        final Player p = args.getPlayer();
        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu befindest dich auf keiner Bauwelt!");
            return false;
        } else {
            if (!p.getUniqueId().equals(p.getUniqueId())) {
            WorldPlayer wp = fw.getMember(p.getUniqueId());
            if (!fw.getOwner().toString().equalsIgnoreCase(p.getUniqueId().toString()) && wp.canReplace == false) {
                World world = Bukkit.getWorld(p.getUniqueId());
                    p.sendMessage("Du darfst in der Welt von " + p.getDisplayName() + " kein TNT und Schleim replacen!");
                    return false;
                }
            }
            World world = p.getWorld();
            int count = 0;
            for(ProtectedRegion rg : BauFactory.getWorldGuard().getRegionManager(world).getApplicableRegions(p.getLocation())) {
                count++;

                if(count != 0) {

                    String rgID = rg.getId();

                    if(rgID.equalsIgnoreCase("resetall")) {
                        return true;
                    }

                    com.sk89q.worldedit.world.World weWorld = new BukkitWorld(p.getWorld());
                    EditSession editSession = BauFactory.getWorldEdit().getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(p.getWorld()), Integer.MAX_VALUE);
                    CuboidSelection selection = new CuboidSelection(p.getWorld(), new Location(p.getWorld(), rg.getMinimumPoint().getBlockX(), rg.getMinimumPoint().getBlockY(), rg.getMinimumPoint().getBlockZ()), new Location(p.getWorld(), rg.getMaximumPoint().getBlockX(), rg.getMaximumPoint().getBlockY(), rg.getMaximumPoint().getBlockZ()));

                    Set<BaseBlock> targetObsidian = new HashSet<>();
                    targetObsidian.add(new BaseBlock(49));
                    Set<BaseBlock> targetBedrock = new HashSet<>();
                    targetBedrock.add(new BaseBlock(7));
                    try {

                        int tnt = editSession.replaceBlocks(selection.getRegionSelector().getRegion(), targetObsidian, new BaseBlock(46));
                        int slime = editSession.replaceBlocks(selection.getRegionSelector().getRegion(), targetBedrock, new BaseBlock(165));
                        editSession.flushQueue();

                        p.sendMessage(BauFactory.S_PREFIX + "In Region " + rgID + " wurden " + tnt + " Blöcke Obsidian zu TNT und " + slime + " Blöcke Bedrock zu Slime replacet!");

                    } catch(Exception ex) { ex.printStackTrace(); }
                }
            }
        }
        return false;
    }
    private boolean isLoaded(String world) {
        for (World w : Bukkit.getServer().getWorlds()) {
            if (w.getName().equals(world)) {
                return true;
            }
        }

        return false;
    }

    private boolean unloadWorld(String world) {
        if (isLoaded(world)) {
            World w = Bukkit.getWorld(world);
            for (Player p : w.getPlayers()) {
                p.teleport(BauFactory.getInstance().config.spawn);
            }

            for (Chunk c : w.getLoadedChunks()) {
                c.unload();
            }

            boolean unload = Bukkit.unloadWorld(w, true);
            return unload;
        }

        return false;
    }


    private void pasteSchematic(Player player, File schematic, Vector position) {

        int count = 0;

        World world = player.getWorld();

        for(ProtectedRegion rg:BauFactory.getWorldGuard().getRegionManager(world).getApplicableRegions(player.getLocation())) {
            count++;
            if(count != 0) {

                EditSession session = BauFactory.getWorldEdit().getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(world), Integer.MAX_VALUE);
                try {

                    CuboidClipboard format = MCEditSchematicFormat.getFormat(schematic).load(schematic);
                    format.paste(session, position, false);

                } catch (Exception ex) {
                    player.sendMessage(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }

    public boolean del(File dir){
        if (dir.isDirectory()){
            String[] entries = dir.list();
            for (int x=0;x<entries.length;x++){
                File aktFile = new File(dir.getPath(),entries[x]);
                del(aktFile);
            }
            if (dir.delete())
                return true;
            else
                return false;
        }
        else{
            if (dir.delete())
                return true;
            else
                return false;
        }
    }
}
