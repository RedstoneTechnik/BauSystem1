package de.redstonetechnik.baufactory.commands;

import de.redstonetechnik.baufactory.content.FactoryWorld;
import de.redstonetechnik.baufactory.content.UUIDFetcher;
import de.redstonetechnik.baufactory.content.WorldPlayer;
import de.redstonetechnik.baufactory.main.BauFactory;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.util.UUID;

public class CommandGui implements Listener {

    public Location warship1;
    public Location warship2;
    public Location wargear5;
    public Location wargear6;
    public Location wargear7;
    public Location wargear8;
    public Location miniwargear1;
    public Location miniwargear2;
    public Location miniwargear7;
    public Location miniwargear8;
    public Location airship1;
    public Location airship2;
    public Location airship3;
    public Location airship4;
    public Location airship5;
    public Location airship6;
    public Location airship7;
    public Location airship8;
    @Getter
    private World wrapped;
    @Getter
    private boolean loading;
    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equals(BauFactory.getInstance().configMessage.cfg.getString("GUI.mainmenu.Item1.Titel"))) {
            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("WarShip1.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        warship1 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("WarShip1.loc.x"), BauFactory.getInstance().config.cfg.getDouble("WarShip1.loc.y"), BauFactory.getInstance().config.cfg.getDouble("WarShip1.loc.z"));
                        p.teleport(warship1);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("WarShip1.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("WarShip2.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        warship2 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("WarShip2.loc.x"), BauFactory.getInstance().config.cfg.getDouble("WarShip2.loc.y"), BauFactory.getInstance().config.cfg.getDouble("WarShip2.loc.z"));
                        p.teleport(warship2);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("WarShip2.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("WarGear5.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        wargear5 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("WarGear5.loc.x"), BauFactory.getInstance().config.cfg.getDouble("WarGear5.loc.y"), BauFactory.getInstance().config.cfg.getDouble("WarGear5.loc.z"));
                        p.teleport(wargear5);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("WarGear5.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("WarGear6.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        wargear6 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("WarGear6.loc.x"), BauFactory.getInstance().config.cfg.getDouble("WarGear6.loc.y"), BauFactory.getInstance().config.cfg.getDouble("WarGear6.loc.z"));
                        p.teleport(wargear6);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("WarGear6.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("WarGear7.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        wargear7 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("WarGear7.loc.x"), BauFactory.getInstance().config.cfg.getDouble("WarGear7.loc.y"), BauFactory.getInstance().config.cfg.getDouble("WarGear7.loc.z"));
                        p.teleport(wargear7);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("WarGear7.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("WarGear8.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        wargear8 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("WarGear8.loc.x"), BauFactory.getInstance().config.cfg.getDouble("WarGear8.loc.y"), BauFactory.getInstance().config.cfg.getDouble("WarGear8.loc.z"));
                        p.teleport(wargear8);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("WarGear8.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear1.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        miniwargear1 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("MiniWarGear1.loc.x"), BauFactory.getInstance().config.cfg.getDouble("MiniWarGear1.loc.y"), BauFactory.getInstance().config.cfg.getDouble("MiniWarGear1.loc.z"));
                        p.teleport(miniwargear1);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear1.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear2.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        miniwargear2 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("MiniWarGear2.loc.x"), BauFactory.getInstance().config.cfg.getDouble("MiniWarGear2.loc.y"), BauFactory.getInstance().config.cfg.getDouble("MiniWarGear2.loc.z"));
                        p.teleport(miniwargear2);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear2.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear7.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        miniwargear7 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("MiniWarGear7.loc.x"), BauFactory.getInstance().config.cfg.getDouble("MiniWarGear7.loc.y"), BauFactory.getInstance().config.cfg.getDouble("MiniWarGear7.loc.z"));
                        p.teleport(miniwargear7);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear7.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear8.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        miniwargear8 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("MiniWarGear8.loc.x"), BauFactory.getInstance().config.cfg.getDouble("MiniWarGear8.loc.y"), BauFactory.getInstance().config.cfg.getDouble("MiniWarGear8.loc.z"));
                        p.teleport(miniwargear8);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear8.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("AirShip1.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        airship1 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("AirShip1.loc.x"), BauFactory.getInstance().config.cfg.getDouble("AirShip1.loc.y"), BauFactory.getInstance().config.cfg.getDouble("AirShip1.loc.z"));
                        p.teleport(airship1);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("AirShip1.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("AirShip2.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        airship2 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("AirShip2.loc.x"), BauFactory.getInstance().config.cfg.getDouble("AirShip2.loc.y"), BauFactory.getInstance().config.cfg.getDouble("AirShip2.loc.z"));
                        p.teleport(airship2);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("AirShip2.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("AirShip3.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        airship3 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("AirShip3.loc.x"), BauFactory.getInstance().config.cfg.getDouble("AirShip3.loc.y"), BauFactory.getInstance().config.cfg.getDouble("AirShip3.loc.z"));
                        p.teleport(airship3);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("AirShip3.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("AirShip4.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        airship4 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("AirShip4.loc.x"), BauFactory.getInstance().config.cfg.getDouble("AirShip4.loc.y"), BauFactory.getInstance().config.cfg.getDouble("AirShip4.loc.z"));
                        p.teleport(airship4);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("AirShip4.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("AirShip5.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        airship5 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("AirShip5.loc.x"), BauFactory.getInstance().config.cfg.getDouble("AirShip5.loc.y"), BauFactory.getInstance().config.cfg.getDouble("AirShip5.loc.z"));
                        p.teleport(airship5);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("AirShip5.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("AirShip6.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        airship6 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("AirShip6.loc.x"), BauFactory.getInstance().config.cfg.getDouble("AirShip6.loc.y"), BauFactory.getInstance().config.cfg.getDouble("AirShip6.loc.z"));
                        p.teleport(airship6);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("AirShip6.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("AirShip7.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        airship7 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("AirShip7.loc.x"), BauFactory.getInstance().config.cfg.getDouble("AirShip7.loc.y"), BauFactory.getInstance().config.cfg.getDouble("AirShip7.loc.z"));
                        p.teleport(airship7);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("AirShip7.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(BauFactory.getInstance().configMessage.cfg.getString("AirShip8.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        airship8 = new Location(p.getWorld(), BauFactory.getInstance().config.cfg.getDouble("AirShip8.loc.x"), BauFactory.getInstance().config.cfg.getDouble("AirShip8.loc.y"), BauFactory.getInstance().config.cfg.getDouble("AirShip8.loc.z"));
                        p.teleport(airship8);
                        p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("AirShip8.Message"));
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.BackButton.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        e.getView().close();
                        p.openInventory(mainmenu());
                        e.setCancelled(true);
                    }
                }
            }
        }
        if (e.getInventory().getTitle().equals(BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Titel"))) {
            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item1.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getUniqueId().toString());
                        if (BauFactory.getInstance().config.DefaultDir.equalsIgnoreCase(BauFactory.getInstance().config.backupDirTheme1)) {
                            p.sendMessage(BauFactory.S_PREFIX + "Es wurde in der Config keine Template Welt eingetragen.");
                            e.setCancelled(true);
                            p.closeInventory();
                            p.sendMessage(BauFactory.S_PREFIX + "DefaultDir: " + BauFactory.getInstance().config.DefaultDir);
                            p.sendMessage(BauFactory.S_PREFIX + "backupDirTheme1: " + BauFactory.getInstance().config.backupDirTheme1);
                            p.sendMessage(BauFactory.S_PREFIX + "Stimmt überein: " + (BauFactory.getInstance().config.DefaultDir.equalsIgnoreCase(BauFactory.getInstance().config.backupDirTheme1)));
                        } else {
                            if (isLoading() == false) {
                                p.sendMessage(BauFactory.S_PREFIX + "§aEinen Moment bitte... Deine Welt wird vorbereitet. " + isLoading());
                            } else {
                            }
                            try {
                                loading = true;
                                File world = new File("plugins/BauSystem/worlds/" + p.getUniqueId() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item1.Titel"));
                                File region = new File("plugins/WorldGuard/worlds/" + p.getUniqueId() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item1.Titel"));
                                if (region.exists() && region.isDirectory()) {
                                } else {
                                    FileUtils.copyDirectory(new File(BauFactory.getInstance().config.regionDirTheme1), new File("plugins/WorldGuard/worlds/" + p.getUniqueId() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item1.Titel")));
                                }
                                if (world.exists() && world.isDirectory()) {
                                    FileUtils.moveDirectory(world, new File(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item1.Titel")));
                                    wrapped = Bukkit.createWorld(new WorldCreator(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item1.Titel")));
                                } else {
                                    FileUtils.copyDirectory(new File(BauFactory.getInstance().config.backupDirTheme1), new File(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item1.Titel")));
                                    wrapped = Bukkit.createWorld(new WorldCreator(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item1.Titel")));
                                }
                                loading = false;
                            } catch (Exception e1) {
                                p.sendMessage(BauFactory.S_PREFIX + "§cBeim Laden der Welt ist ein Fehler aufgetreten.");
                                e1.printStackTrace();
                            }
                            e.setCancelled(true);
                            p.closeInventory();
                            Bukkit.getScheduler().scheduleSyncDelayedTask(BauFactory.getInstance(), new Runnable() {
                                public void run() {
                                    p.teleport(Bukkit.getWorld(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item1.Titel")).getSpawnLocation());
                                }
                            }, 20L);
                        }

                    }
                }
            }
        }
        if (e.getInventory().getTitle().equals(BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Titel"))) {
            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item2.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getUniqueId().toString());
                        if (BauFactory.getInstance().config.DefaultDir.equalsIgnoreCase(BauFactory.getInstance().config.backupDirTheme2)) {
                            p.sendMessage(BauFactory.S_PREFIX + "Es wurde in der Config keine Template Welt eingetragen.");
                            e.setCancelled(true);
                            p.closeInventory();
                        } else {
                            if (isLoading() == false) {
                                p.sendMessage(BauFactory.S_PREFIX + "§aEinen Moment bitte... Deine Welt wird vorbereitet. " + isLoading());
                            } else {
                            }
                            try {
                                loading = true;
                                File world = new File("plugins/BauSystem/worlds/" + p.getUniqueId() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item2.Titel"));
                                File region = new File("plugins/WorldGuard/worlds/" + p.getUniqueId() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item2.Titel"));
                                if (region.exists() && region.isDirectory()) {
                                } else {
                                    FileUtils.copyDirectory(new File(BauFactory.getInstance().config.regionDirTheme2), new File("plugins/WorldGuard/worlds/" + p.getUniqueId() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item2.Titel")));
                                }
                                if (world.exists() && world.isDirectory()) {
                                    FileUtils.moveDirectory(world, new File(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item2.Titel")));
                                    wrapped = Bukkit.createWorld(new WorldCreator(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item2.Titel")));
                                } else {
                                    FileUtils.copyDirectory(new File(BauFactory.getInstance().config.backupDirTheme2), new File(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item2.Titel")));
                                    wrapped = Bukkit.createWorld(new WorldCreator(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item2.Titel")));
                                }
                                loading = false;
                            } catch (Exception e1) {
                                p.sendMessage(BauFactory.S_PREFIX + "§cBeim Laden der Welt ist ein Fehler aufgetreten.");
                                e1.printStackTrace();
                            }
                            e.setCancelled(true);
                            p.closeInventory();
                            Bukkit.getScheduler().scheduleSyncDelayedTask(BauFactory.getInstance(), new Runnable() {
                                public void run() {
                                    p.teleport(Bukkit.getWorld(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item2.Titel")).getSpawnLocation());
                                }
                            }, 20L);
                        }

                    }
                }
            }
        }
        if (e.getInventory().getTitle().equals(BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Titel"))) {
            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item3.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getUniqueId().toString());
                        if (BauFactory.getInstance().config.DefaultDir.equalsIgnoreCase(BauFactory.getInstance().config.backupDirTheme3)) {
                            p.sendMessage(BauFactory.S_PREFIX + "Es wurde in der Config keine Template Welt eingetragen.");
                            e.setCancelled(true);
                            p.closeInventory();
                        } else {
                            if (isLoading() == false) {
                                p.sendMessage(BauFactory.S_PREFIX + "§aEinen Moment bitte... Deine Welt wird vorbereitet. " + isLoading());
                            } else {
                            }
                            try {
                                loading = true;
                                File world = new File("plugins/BauSystem/worlds/" + p.getUniqueId() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item3.Titel"));
                                File region = new File("plugins/WorldGuard/worlds/" + p.getUniqueId() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item3.Titel"));
                                if (region.exists() && region.isDirectory()) {
                                } else {
                                    FileUtils.copyDirectory(new File(BauFactory.getInstance().config.regionDirTheme3), new File("plugins/WorldGuard/worlds/" + p.getUniqueId() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item3.Titel")));
                                }
                                if (world.exists() && world.isDirectory()) {
                                    FileUtils.moveDirectory(world, new File(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item3.Titel")));
                                    wrapped = Bukkit.createWorld(new WorldCreator(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item3.Titel")));
                                } else {
                                    FileUtils.copyDirectory(new File(BauFactory.getInstance().config.backupDirTheme3), new File(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item3.Titel")));
                                    wrapped = Bukkit.createWorld(new WorldCreator(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item3.Titel")));
                                }
                                loading = false;
                            } catch (Exception e1) {
                                p.sendMessage(BauFactory.S_PREFIX + "§cBeim Laden der Welt ist ein Fehler aufgetreten.");
                                e1.printStackTrace();
                            }
                            e.setCancelled(true);
                            p.closeInventory();
                            Bukkit.getScheduler().scheduleSyncDelayedTask(BauFactory.getInstance(), new Runnable() {
                                public void run() {
                                    p.teleport(Bukkit.getWorld(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item3.Titel")).getSpawnLocation());
                                }
                            }, 20L);
                        }

                    }
                }
            }
        }
        if (e.getInventory().getTitle().equals(BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Titel"))) {
            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item4.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getUniqueId().toString());
                        if (BauFactory.getInstance().config.DefaultDir.equalsIgnoreCase(BauFactory.getInstance().config.backupDirTheme4)) {
                            p.sendMessage(BauFactory.S_PREFIX + "Es wurde in der Config keine Template Welt eingetragen.");
                            e.setCancelled(true);
                            p.closeInventory();
                        } else {
                            if (isLoading() == false) {
                                p.sendMessage(BauFactory.S_PREFIX + "§aEinen Moment bitte... Deine Welt wird vorbereitet. " + isLoading());
                            } else {
                            }
                            try {
                                loading = true;
                                File world = new File("plugins/BauSystem/worlds/" + p.getUniqueId() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item4.Titel"));
                                File region = new File("plugins/WorldGuard/worlds/" + p.getUniqueId() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item4.Titel"));
                                if (region.exists() && region.isDirectory()) {
                                } else {
                                    FileUtils.copyDirectory(new File(BauFactory.getInstance().config.regionDirTheme4), new File("plugins/WorldGuard/worlds/" + p.getUniqueId() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item4.Titel")));
                                }
                                if (world.exists() && world.isDirectory()) {
                                    FileUtils.moveDirectory(world, new File(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item4.Titel")));
                                    wrapped = Bukkit.createWorld(new WorldCreator(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item4.Titel")));
                                } else {
                                    FileUtils.copyDirectory(new File(BauFactory.getInstance().config.backupDirTheme4), new File(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item4.Titel")));
                                    wrapped = Bukkit.createWorld(new WorldCreator(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item1.Tite4")));
                                }
                                loading = false;
                            } catch (Exception e1) {
                                p.sendMessage(BauFactory.S_PREFIX + "§cBeim Laden der Welt ist ein Fehler aufgetreten.");
                                e1.printStackTrace();
                            }
                            e.setCancelled(true);
                            p.closeInventory();
                            Bukkit.getScheduler().scheduleSyncDelayedTask(BauFactory.getInstance(), new Runnable() {
                                public void run() {
                                    p.teleport(Bukkit.getWorld(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item4.Titel")).getSpawnLocation());
                                }
                            }, 20L);
                        }

                    }
                }
            }
        }
        if (e.getInventory().getTitle().equals(BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Titel"))) {
            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item5.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getUniqueId().toString());
                        if (BauFactory.getInstance().config.DefaultDir.equalsIgnoreCase(BauFactory.getInstance().config.backupDirTheme5)) {
                            p.sendMessage(BauFactory.S_PREFIX + "Es wurde in der Config keine Template Welt eingetragen.");
                            e.setCancelled(true);
                            p.closeInventory();
                        } else {
                            if (isLoading() == false) {
                                p.sendMessage(BauFactory.S_PREFIX + "§aEinen Moment bitte... Deine Welt wird vorbereitet. " + isLoading());
                            } else {
                            }
                            try {
                                loading = true;
                                File world = new File("plugins/BauSystem/worlds/" + p.getUniqueId() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item5.Titel"));
                                File region = new File("plugins/WorldGuard/worlds/" + p.getUniqueId() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item5.Titel"));
                                if (region.exists() && region.isDirectory()) {
                                } else {
                                    FileUtils.copyDirectory(new File(BauFactory.getInstance().config.regionDirTheme5), new File("plugins/WorldGuard/worlds/" + p.getUniqueId() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item1.Tite5")));
                                }
                                if (world.exists() && world.isDirectory()) {
                                    FileUtils.moveDirectory(world, new File(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item5.Titel")));
                                    wrapped = Bukkit.createWorld(new WorldCreator(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item5.Titel")));
                                } else {
                                    FileUtils.copyDirectory(new File(BauFactory.getInstance().config.backupDirTheme5), new File(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item5.Titel")));
                                    wrapped = Bukkit.createWorld(new WorldCreator(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item5.Titel")));
                                }
                                loading = false;
                            } catch (Exception e1) {
                                p.sendMessage(BauFactory.S_PREFIX + "§cBeim Laden der Welt ist ein Fehler aufgetreten.");
                                e1.printStackTrace();
                            }
                            e.setCancelled(true);
                            p.closeInventory();
                            Bukkit.getScheduler().scheduleSyncDelayedTask(BauFactory.getInstance(), new Runnable() {
                                public void run() {
                                    p.teleport(Bukkit.getWorld(p.getUniqueId().toString() + BauFactory.getInstance().configMessage.cfg.getString("GUI.world.Item5.Titel")).getSpawnLocation());
                                }
                            }, 20L);
                        }

                    }
                }
            }
        }
        if (e.getInventory().getTitle().equals(BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Titel"))) {
            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item1.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
                        WorldPlayer wp = fw.getMember(p.getUniqueId());
                        Player z = Bukkit.getPlayer(p.getUniqueId());
                        if (z != null) {
                            if (wp.canTime) {
                                p.getWorld().setTime((long) 6000);
                                p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item1.Message"));
                                e.setCancelled(true);
                                p.openInventory(zeit(p));
                            } else {
                                p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.DenyMessage"));
                                p.closeInventory();
                                e.setCancelled(true);
                            }
                        }
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item2.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
                        WorldPlayer wp = fw.getMember(p.getUniqueId());
                        Player z = Bukkit.getPlayer(p.getUniqueId());
                        if (z != null) {
                            if (wp.canTime) {
                                p.getWorld().setTime((long) 10000);
                                p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item2.Message"));
                                e.setCancelled(true);
                                p.openInventory(zeit(p));
                            } else {
                                p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.DenyMessage"));
                                e.setCancelled(true);
                                p.closeInventory();
                            }
                        }
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item3.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
                        WorldPlayer wp = fw.getMember(p.getUniqueId());
                        Player z = Bukkit.getPlayer(p.getUniqueId());
                        if (z != null) {
                            if (wp.canTime) {
                                p.getWorld().setTime((long) 18000);
                                p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item3.Message"));
                                e.setCancelled(true);
                                p.openInventory(zeit(p));
                            } else {
                                p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.DenyMessage"));
                                e.setCancelled(true);
                                p.closeInventory();
                            }
                        }
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item4.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
                        WorldPlayer wp = fw.getMember(p.getUniqueId());
                        Player z = Bukkit.getPlayer(p.getUniqueId());
                        if (z != null) {
                            if (wp.canTime) {
                                p.getWorld().setTime((long) 13000);
                                p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item4.Message"));
                                e.setCancelled(true);
                                p.openInventory(zeit(p));
                            } else {
                                p.sendMessage(BauFactory.S_PREFIX + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.DenyMessage"));
                                e.setCancelled(true);
                                p.closeInventory();
                            }
                        }
                    }
                }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.BackButton.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        e.getView().close();
                        p.openInventory(weltoption(p));
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§eStatus §2An")) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        p.sendMessage(BauFactory.S_PREFIX + "§6Bitte klicke auf das Item oben drüber.");
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§eStatus §4Aus")) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        p.sendMessage(BauFactory.S_PREFIX + "§6Bitte klicke auf das Item oben drüber.");
                        e.getView().close();
                        e.setCancelled(true);
                    }
                }
            }
        if (e.getInventory().getTitle().equals(BauFactory.getInstance().configMessage.cfg.getString("GUI.mainmenu.Item2.Titel"))) {
            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§aTNT Schaden")) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
                        if (fw == null) {
                            p.sendMessage(BauFactory.S_PREFIX + "§cDu hast keine Welt");
                            e.setCancelled(true);
                            return;
                        }
                        fw.setTntDamage(!fw.isTntDamage());
                        if (fw.isTntDamage()) {
                            p.sendMessage(BauFactory.S_PREFIX + "§aAuf deiner Welt ist jetzt TNT Schaden erlaubt.");
                        } else {
                            p.sendMessage(BauFactory.S_PREFIX + "§cAuf deiner Welt ist jetzt TNT Schaden verboten.");
                        }
                        e.getView().close();
                        p.openInventory(weltoption(p));
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§aFeuer Schaden")) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
                        if (fw == null) {
                            p.sendMessage(BauFactory.S_PREFIX + "§cDu hast keine Welt");
                            e.setCancelled(true);
                            return;
                        }
                        fw.setFireDamage(!fw.isFireDamage());
                        if (fw.isFireDamage()) {
                            p.sendMessage(BauFactory.S_PREFIX + "§aAuf deiner Welt ist jetzt Feuer Schaden erlaubt.");
                        } else {
                            p.sendMessage(BauFactory.S_PREFIX + "§cAuf deiner Welt ist jetzt Feuer Schaden verboten.");
                        }
                        e.getView().close();
                        p.openInventory(weltoption(p));
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        p.openInventory(zeit(p));
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§eStatus §2An")) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        p.sendMessage(BauFactory.S_PREFIX + "§4Du darfst das nicht");
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§eStatus §4Aus")) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        p.sendMessage(BauFactory.S_PREFIX + "§4Das darfst du nicht");
                        e.getView().close();
                        e.setCancelled(true);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.BackButton.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        e.getView().close();
                        p.openInventory(mainmenu());
                        e.setCancelled(true);
                    }
                }
            }
        }
        if (e.getInventory().getTitle().equals("§aBau Optionen")) {
            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.mainmenu.Item1.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        p.openInventory(bauabteilung());
                    }
                }
            }
        }
        if (e.getInventory().getTitle().equals("§aBau Optionen")) {
            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.mainmenu.Item2.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        p.openInventory(weltoption(p));
                    }
                }
            }
        }
        if (e.getInventory().getTitle().equals("§aBau Optionen")) {
            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.mainmenu.Item3.Titel"))) {
                        //code
                        Player player = (Player) e.getWhoClicked();
                            player.openInventory(spieleroption(player));
                        }
                    }
                }
            }
        if (e.getInventory().getTitle().equals(BauFactory.getInstance().configMessage.cfg.getString("GUI.mainmenu.Item3.Titel"))) {
            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    Player player = (Player) e.getWhoClicked();
                    if (e.getCurrentItem() != null) {
                        Material material = e.getCurrentItem().getType();
                        if (material == Material.SKULL_ITEM) {
                            SkullMeta skullMeta = (SkullMeta) getSkull(material.getData().getCanonicalName()).getItemMeta();
                            SkullTeleport(player, e.getCurrentItem());
                        }
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.BackButton.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        e.getView().close();
                        p.openInventory(mainmenu());
                        e.setCancelled(true);
                    }
                }
            }
        }
        if (e.getInventory().getTitle().equals(BauFactory.getInstance().configMessage.cfg.getString("GUI.mainmenu.Item2.Titel"))) {
            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    Player player = (Player) e.getWhoClicked();
                    if (e.getCurrentItem() != null) {
                        Material material = e.getCurrentItem().getType();
                        if (material == Material.WATCH) {
                            SkullMeta skullMeta = (SkullMeta) getSkull(material.getData().getCanonicalName()).getItemMeta();
                            SkullTeleport(player, e.getCurrentItem());
                        }
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Titel"))) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        e.getView().close();
                        p.openInventory(zeit(player));
                        e.setCancelled(true);
                    }
                }
            }
        }
        for (Player target : Bukkit.getOnlinePlayers()) {
            if (e.getInventory().getTitle().equals(target.getDisplayName())) {
                if (e.getCurrentItem().getItemMeta() != null) {

                    if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                        Player player = (Player) e.getWhoClicked();
                        ItemStack skull = new ItemStack(Material.SKULL_ITEM);
                        ItemMeta meta = skull.getItemMeta();
                        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(player.getUniqueId());
                        if (fw == null) {
                            player.sendMessage(BauFactory.S_PREFIX + "§cDu hast keine Welt");
                        }
                        UUID id = UUIDFetcher.getUUID(target.getDisplayName());
                        if (id == null) {
                            player.sendMessage(BauFactory.S_PREFIX + "§cUnbekannter Spieler");
                            return;
                        }
                                if (fw.getMember(id) != null) {
                                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§aSpieler hinzufügen")) {
                                        //code
                                        Player p = (Player) e.getWhoClicked();
                                        p.sendMessage(BauFactory.S_PREFIX + "§cDieser Spieler ist bereits Mitglied auf deiner Welt");
                                        p.openInventory(skullOwnerInventory(target, player));
                                        e.setCancelled(true);
                                    }
                            }else{
                            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§aSpieler hinzufügen")) {
                                //code
                                Player p = (Player) e.getWhoClicked();
                                WorldPlayer wp = new WorldPlayer(id, fw);
                                wp.canBuild = true;
                                fw.getMembers().register(wp);
                                p.sendMessage(BauFactory.S_PREFIX + ChatColor.RED + "§aDu hast " + target.getName() + " §azu der Welt §6" + ChatColor.RED + " §ahinzugefügt");
                                target.sendMessage(BauFactory.S_PREFIX + ChatColor.RED + "§aDu wurdest zu der Welt von §6" + p.getName() + ChatColor.RED + " §ahinzugefügt");
                                p.openInventory(skullOwnerInventory(target, player));
                                e.setCancelled(true);
                            }
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.BackButton.Titel"))) {
                            //code
                            Player p = (Player) e.getWhoClicked();
                            e.getView().close();
                            e.setCancelled(true);
                            player.closeInventory();
                            player.openInventory(spieleroption(p));
                        }
                        if (fw.getMember(target.getUniqueId()) == null) {
                            e.setCancelled(true);
                            return;
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§aToggle Worldedit")) {
                            //code
                            Player p = (Player) e.getWhoClicked();
                            WorldPlayer wp = fw.getMember(target.getUniqueId());
                            if (wp == null) {
                                p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist kein Mitglied deiner Welt");
                                return;
                            }
                            wp.canWorldEdit = !wp.canWorldEdit;
                            Player z = Bukkit.getPlayer(target.getUniqueId());
                            if (z != null) {
                                if (wp.canWorldEdit) {
                                    p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf jetzt auf deiner Welt Worldedit benutzten.");
                                    z.sendMessage(BauFactory.S_PREFIX + "§aDu kannst nun auf der Welt von §6" + p.getName() + "§a WorldEdit verwenden.");
                                    p.closeInventory();
                                    player.openInventory(skullOwnerInventory(target, player));
                                    e.setCancelled(true);
                                } else {
                                    p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf jetzt auf deiner Welt kein Worldedit mehr benutzten.");
                                    z.sendMessage(BauFactory.S_PREFIX + "§cDu kannst nun nicht mehr auf der Welt von §6" + p.getName() + "§c WorldEdit verwenden.");
                                    p.closeInventory();
                                    player.openInventory(skullOwnerInventory(target, player));
                                    e.setCancelled(true);
                                }
                            }
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§aToggle Teleport")) {
                            //code
                            Player p = (Player) e.getWhoClicked();
                            WorldPlayer wp = fw.getMember(target.getUniqueId());
                            if (wp == null) {
                                p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist kein Mitglied deiner Welt");
                                return;
                            }
                            wp.canTeleport = !wp.canTeleport;
                            Player z = Bukkit.getPlayer(target.getUniqueId());
                            if (z != null) {
                                if (wp.canTeleport) {
                                    p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler kann sich jetzt auf deiner Welt Teleportieren.");
                                    z.sendMessage(BauFactory.S_PREFIX + "§aDu kannst nun auf der Welt von §6" + p.getName() + "§a /tp verwenden.");
                                    p.closeInventory();
                                    player.openInventory(skullOwnerInventory(target, player));
                                    e.setCancelled(true);
                                } else {
                                    p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf sich jetzt nicht mehr auf deiner Welt Teleportieren.");
                                    z.sendMessage(BauFactory.S_PREFIX + "§cDu kannst nun nicht mehr auf der Welt von §6" + p.getName() + "§c /tp verwenden.");
                                    p.closeInventory();
                                    player.openInventory(skullOwnerInventory(target, player));
                                    e.setCancelled(true);
                                }
                            }
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§aToggle Build")) {
                            //code
                            Player p = (Player) e.getWhoClicked();
                            WorldPlayer wp = fw.getMember(target.getUniqueId());
                            if (wp == null) {
                                p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist kein Mitglied deiner Welt");
                                return;
                            }
                            wp.canBuild = !wp.canBuild;
                            Player z = Bukkit.getPlayer(target.getUniqueId());
                            if (z != null) {
                                if (wp.canBuild) {
                                    p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf jetzt Bauen auf deiner Welt.");
                                    z.sendMessage(BauFactory.S_PREFIX + "§aDu kannst nun auf der Welt von §6" + p.getName() + "§a bauen.");
                                    p.closeInventory();
                                    player.openInventory(skullOwnerInventory(target, player));
                                    e.setCancelled(true);
                                } else {
                                    p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf jetzt nicht mehr Bauen auf deiner Welt.");
                                    z.sendMessage(BauFactory.S_PREFIX + "§cDu kannst nun nicht mehr auf der Welt von §6" + p.getName() + "§c bauen.");
                                    p.closeInventory();
                                    player.openInventory(skullOwnerInventory(target, player));
                                    e.setCancelled(true);
                                }
                            }
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§aToggle ChangeGamemode")) {
                            //code
                            Player p = (Player) e.getWhoClicked();
                            WorldPlayer wp = fw.getMember(target.getUniqueId());
                            if (wp == null) {
                                p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist kein Mitglied deiner Welt");
                                return;
                            }
                            wp.canChangeGamemode = !wp.canChangeGamemode;
                            Player z = Bukkit.getPlayer(target.getUniqueId());
                            if (z != null) {
                                if (wp.canChangeGamemode) {
                                    p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf jetzt sein Gamemode auf deiner Welt wechseln.");
                                    z.sendMessage(BauFactory.S_PREFIX + "§aDu kannst nun auf der Welt von §6" + p.getName() + "§a deinen Spielmodus ändern.");
                                    p.closeInventory();
                                    player.openInventory(skullOwnerInventory(target, player));
                                    e.setCancelled(true);
                                } else {
                                    p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf jetzt nicht mehr sein Gamemode auf deiner Welt wechseln.");
                                    z.sendMessage(BauFactory.S_PREFIX + "§cDu kannst nun nicht mehr auf der Welt von §6" + p.getName() + "§c deinen Spielmodus ändern.");
                                    p.closeInventory();
                                    player.openInventory(skullOwnerInventory(target, player));
                                    e.setCancelled(true);
                                }
                            }
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§cSpieler entfernen")) {
                            //code
                            Player p = (Player) e.getWhoClicked();
                            fw.getMembers().unregister(fw.getMembers().getID(fw.getMember(target.getUniqueId())));
                            target.sendMessage(BauFactory.S_PREFIX + ChatColor.RED + "§aDu wurdest von der Welt von §6" + p.getName() + ChatColor.RED + " §aentfernt");
                            Player z = Bukkit.getPlayer(target.getUniqueId());
                            p.sendMessage(BauFactory.S_PREFIX + ChatColor.RED + "Der Spieler " + target.getName() + ChatColor.RED + " wurde von deiner welt entfernt");
                            e.setCancelled(true);
                            player.openInventory(skullOwnerInventory(target, player));
                            if (z != null) {
                                if (z.getWorld().getUID().equals(Bukkit.getWorld(p.getUniqueId().toString()).getUID())) {
                                    z.teleport(BauFactory.getInstance().config.spawn);
                                    z.getInventory().clear();
                                }
                            }
                            p.openInventory(spieleroption(p));
                            e.setCancelled(true);
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§aToggle Replace")) {
                            //code
                            Player p = (Player) e.getWhoClicked();
                            WorldPlayer wp = fw.getMember(target.getUniqueId());
                            if (wp == null) {
                                p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist kein Mitglied deiner Welt");
                                return;
                            }
                            wp.canReplace = !wp.canReplace;
                            Player z = Bukkit.getPlayer(target.getUniqueId());
                            if (z != null) {
                                if (wp.canReplace) {
                                    p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf jetzt auf deiner Welt Replacen.");
                                    z.sendMessage(BauFactory.S_PREFIX + "§aDu kannst nun auf der Welt von §6" + p.getName() + "§c /bau replace verwenden.");
                                    p.closeInventory();
                                    player.openInventory(skullOwnerInventory(target, player));
                                    e.setCancelled(true);
                                } else {
                                    p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf jetzt nicht mehr auf deiner Welt Replacen.");
                                    z.sendMessage(BauFactory.S_PREFIX + "§cDu kannst nun nicht mehr auf der Welt von §6" + p.getName() + "§c /bau replace verwenden.");
                                    p.closeInventory();
                                    player.openInventory(skullOwnerInventory(target, player));
                                    e.setCancelled(true);
                                }
                            }
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§aToggle Reset")) {
                            //code
                            Player p = (Player) e.getWhoClicked();
                            WorldPlayer wp = fw.getMember(target.getUniqueId());
                            if (wp == null) {
                                p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist kein Mitglied deiner Welt");
                                return;
                            }
                            wp.canReset = !wp.canReset;
                            Player z = Bukkit.getPlayer(target.getUniqueId());
                            if (z != null) {
                                if (wp.canReset) {
                                    p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf jetzt auf deiner Welt einzelne Regionen erneuern.");
                                    z.sendMessage(BauFactory.S_PREFIX + "§aDu kannst nun auf der Welt von §6" + p.getName() + "§c /bau reset verwenden.");
                                    p.closeInventory();
                                    player.openInventory(skullOwnerInventory(target, player));
                                    e.setCancelled(true);
                                } else {
                                    p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf jetzt nicht mehr auf deiner Welt einzelne Regionen erneuern.");
                                    z.sendMessage(BauFactory.S_PREFIX + "§cDu kannst nun nicht mehr auf der Welt von §6" + p.getName() + "§c /bau reset verwenden.");
                                    p.closeInventory();
                                    player.openInventory(skullOwnerInventory(target, player));
                                    e.setCancelled(true);
                                }
                            }
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§aToggle TestBlock")) {
                            //code
                            Player p = (Player) e.getWhoClicked();
                            WorldPlayer wp = fw.getMember(target.getUniqueId());
                            if (wp == null) {
                                p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist kein Mitglied deiner Welt");
                                return;
                            }
                            wp.canTestblock = !wp.canTestblock;
                            Player z = Bukkit.getPlayer(target.getUniqueId());
                            if (z != null) {
                                if (wp.canTestblock) {
                                    p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf jetzt auf deiner Welt TestBlöcke erneuern.");
                                    z.sendMessage(BauFactory.S_PREFIX + "§aDu kannst nun auf der Welt von §6" + p.getName() + "§c /bau testblock verwenden.");
                                    p.closeInventory();
                                    player.openInventory(skullOwnerInventory(target, player));
                                    e.setCancelled(true);
                                } else {
                                    p.sendMessage(BauFactory.S_PREFIX + "§aDer Spieler darf jetzt nicht mehr auf deiner Welt TestBlöcke erneuern.");
                                    z.sendMessage(BauFactory.S_PREFIX + "§cDu kannst nun nicht mehr auf der Welt von §6" + p.getName() + "§c /bau testblock verwenden.");
                                    p.closeInventory();
                                    player.openInventory(skullOwnerInventory(target, player));
                                    e.setCancelled(true);
                                }
                            }
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§eStatus §4An")) {
                            //code
                            Player p = (Player) e.getWhoClicked();
                            p.sendMessage(BauFactory.S_PREFIX + "§4Das darfst du nicht");
                            e.getView().close();
                            e.setCancelled(true);
                            player.closeInventory();
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§eStatus §4Aus")) {
                            //code
                            Player p = (Player) e.getWhoClicked();
                            p.sendMessage(BauFactory.S_PREFIX + "§4Das darfst du nicht");
                            e.getView().close();
                            e.setCancelled(true);
                            player.closeInventory();
                        }
                        }
                    }
                }
            }
        }

    public ItemStack getSkull(String name) {
        ItemStack skull = new ItemStack(397, 1, (short) 3);
        SkullMeta skullmeta = (SkullMeta) skull.getItemMeta();
        skullmeta.setDisplayName(name);
        skullmeta.setOwner(name);
        skull.setItemMeta(skullmeta);
        return skull;
    }

    public void SkullTeleport(Player player, ItemStack item) {
        SkullMeta skullmeta = (SkullMeta) item.getItemMeta();
        if (skullmeta.getDisplayName() != null) {
            if (Bukkit.getPlayer(skullmeta.getDisplayName()) != null) {
                Player target = Bukkit.getPlayer(skullmeta.getDisplayName());
                player.openInventory(skullOwnerInventory(target, player));
            }
        }
    }

    private Inventory skullOwnerInventory(Player p, Player player) {

        Inventory inv2 = Bukkit.createInventory(null, 18, p.getDisplayName());
        ItemStack skull = new ItemStack(Material.SKULL_ITEM);
        ItemMeta meta = skull.getItemMeta();
        meta = skull.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "§aSpieler hinzufügen");
        skull.setItemMeta(meta);
        inv2.addItem(skull);

        ItemStack skull1 = new ItemStack(Material.SKULL_ITEM);
        meta = skull1.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "§cSpieler entfernen");
        skull1.setItemMeta(meta);
        inv2.setItem(9, skull1);

        ItemStack togglewe = new ItemStack(Material.WOOD_AXE);
        meta = togglewe.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "§aToggle Worldedit");
        togglewe.setItemMeta(meta);
        inv2.setItem(1, togglewe);

        ItemStack toggletp = new ItemStack(Material.COMPASS);
        meta = toggletp.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "§aToggle Teleport");
        toggletp.setItemMeta(meta);
        inv2.setItem(2, toggletp);

        ItemStack togglebuild = new ItemStack(Material.GRASS);
        meta = togglebuild.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "§aToggle Build");
        togglebuild.setItemMeta(meta);
        inv2.setItem(3, togglebuild);

        ItemStack togglegm = new ItemStack(Material.LADDER);
        meta = togglegm.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "§aToggle ChangeGamemode");
        togglegm.setItemMeta(meta);
        inv2.setItem(4, togglegm);

        ItemStack togglerep = new ItemStack(Material.BREWING_STAND_ITEM);
        meta = togglerep.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "§aToggle Replace");
        togglerep.setItemMeta(meta);
        inv2.setItem(5, togglerep);

        ItemStack togglereset = new ItemStack(Material.BARRIER);
        meta = togglereset.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "§aToggle Reset");
        togglereset.setItemMeta(meta);
        inv2.setItem(6, togglereset);

        ItemStack toggletestblock = new ItemStack(Material.ENDER_STONE);
        meta = toggletestblock.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "§aToggle TestBlock");
        toggletestblock.setItemMeta(meta);
        inv2.setItem(7, toggletestblock);

        ItemStack back = new ItemStack(Material.SLIME_BALL);
        meta = back.getItemMeta();
        meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.BackButton.Titel"));
        back.setItemMeta(meta);
        inv2.setItem(8, back);


        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(player.getUniqueId());
        if (fw == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDu hast keine Welt");
        }
        if (fw.getMember(p.getUniqueId()) == null) {
            p.sendMessage(BauFactory.S_PREFIX + "§cDer Spieler ist noch kein Mitglied deiner Welt");
        } else {
            Player z = Bukkit.getPlayer(p.getUniqueId());
            if (z != null) {
                if ((fw.getMember(p.getUniqueId()).canWorldEdit)){
                    ItemStack tntst = new ItemStack(351, 1, (short) 10);
                    meta = tntst.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "§eStatus §2An");
                    tntst.setItemMeta(meta);
                    inv2.setItem(10, tntst);
                } else {
                    ItemStack tntst1 = new ItemStack(351, 1, (short) 8);
                    meta = tntst1.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "§eStatus §4Aus");
                    tntst1.setItemMeta(meta);
                    inv2.setItem(10, tntst1);
                }
                if ((fw.getMember(p.getUniqueId()).canTeleport)) {
                    ItemStack tntst = new ItemStack(351, 1, (short) 10);
                    meta = tntst.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "§eStatus §2An");
                    tntst.setItemMeta(meta);
                    inv2.setItem(11, tntst);
                } else {
                    ItemStack tntst1 = new ItemStack(351, 1, (short) 8);
                    meta = tntst1.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "§eStatus §4Aus");
                    tntst1.setItemMeta(meta);
                    inv2.setItem(11, tntst1);
                }
                if ((fw.getMember(p.getUniqueId()).canBuild)) {
                    ItemStack tntst = new ItemStack(351, 1, (short) 10);
                    meta = tntst.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "§eStatus §2An");
                    tntst.setItemMeta(meta);
                    inv2.setItem(12, tntst);
                } else {
                    ItemStack tntst1 = new ItemStack(351, 1, (short) 8);
                    meta = tntst1.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "§eStatus §4Aus");
                    tntst1.setItemMeta(meta);
                    inv2.setItem(12, tntst1);
                }
                if ((fw.getMember(p.getUniqueId()).canChangeGamemode)) {
                    ItemStack tntst = new ItemStack(351, 1, (short) 10);
                    meta = tntst.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "§eStatus §2An");
                    tntst.setItemMeta(meta);
                    inv2.setItem(13, tntst);
                } else {
                    ItemStack tntst1 = new ItemStack(351, 1, (short) 8);
                    meta = tntst1.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "§eStatus §4Aus");
                    tntst1.setItemMeta(meta);
                    inv2.setItem(13, tntst1);
                }
                if ((fw.getMember(p.getUniqueId()).canReplace)) {
                    ItemStack tntst = new ItemStack(351, 1, (short) 10);
                    meta = tntst.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "§eStatus §2An");
                    tntst.setItemMeta(meta);
                    inv2.setItem(14, tntst);
                } else {
                    ItemStack tntst1 = new ItemStack(351, 1, (short) 8);
                    meta = tntst1.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "§eStatus §4Aus");
                    tntst1.setItemMeta(meta);
                    inv2.setItem(14, tntst1);
                }
                if ((fw.getMember(p.getUniqueId()).canReset)) {
                    ItemStack tntst = new ItemStack(351, 1, (short) 10);
                    meta = tntst.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "§eStatus §2An");
                    tntst.setItemMeta(meta);
                    inv2.setItem(15, tntst);
                } else {
                    ItemStack tntst1 = new ItemStack(351, 1, (short) 8);
                    meta = tntst1.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "§eStatus §4Aus");
                    tntst1.setItemMeta(meta);
                    inv2.setItem(15, tntst1);
                }
                if ((fw.getMember(p.getUniqueId()).canTestblock)) {
                    ItemStack tntst = new ItemStack(351, 1, (short) 10);
                    meta = tntst.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "§eStatus §2An");
                    tntst.setItemMeta(meta);
                    inv2.setItem(16, tntst);
                } else {
                    ItemStack tntst1 = new ItemStack(351, 1, (short) 8);
                    meta = tntst1.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "§eStatus §4Aus");
                    tntst1.setItemMeta(meta);
                    inv2.setItem(16, tntst1);
                }
            }
        }
        return inv2;
    }

    private Inventory mainmenu() {

        Inventory inv = Bukkit.createInventory(null, 9, "§aBau Optionen");
        {
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
        }
        return inv;
    }

    private Inventory spieleroption(Player player) {
        FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(player.getUniqueId());
        Inventory inv = Bukkit.createInventory(null, 54, BauFactory.getInstance().configMessage.cfg.getString("GUI.mainmenu.Item3.Titel"));
        {
            inv.clear();
            int slot = 0;
            for (Player p : Bukkit.getOnlinePlayers()) {
                inv.setItem(slot, getSkull(p.getName()));
                slot++;
            }
            ItemStack skull1 = new ItemStack(Material.SLIME_BALL);
            ItemMeta meta1 = skull1.getItemMeta();
            meta1 = skull1.getItemMeta();
            meta1.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.BackButton.Titel"));
            skull1.setItemMeta(meta1);
            inv.setItem(53, skull1);
        }return inv;
    }

    private Inventory bauabteilung() {
        Inventory inv1 = Bukkit.createInventory(null, 54, BauFactory.getInstance().configMessage.cfg.getString("GUI.mainmenu.Item1.Titel"));
        {
        ItemStack warship1 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("WarShip1.Item")));
        ItemMeta meta = warship1.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("WarShip1.Titel"));
        warship1.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("WarShip1.Position"), warship1);
        ItemStack warship2 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("WarShip2.Item")));
        meta = warship2.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("WarShip2.Titel"));
        warship2.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("WarShip2.Position"), warship2);
        ItemStack wargear1 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("WarGear5.Item")));
        meta = wargear1.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("WarGear5.Titel"));
        wargear1.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("WarGear5.Position"), wargear1);
        ItemStack wargear2 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("WarGear6.Item")));
        meta = wargear2.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("WarGear6.Titel"));
        wargear2.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("WarGear6.Position"), wargear2);
        ItemStack wargear3 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("WarGear7.Item")));
        meta = wargear3.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("WarGear7.Titel"));
        wargear3.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("WarGear7.Position"), wargear3);
        ItemStack wargear4 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("WarGear8.Item")));
        meta = wargear4.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("WarGear8.Titel"));
        wargear4.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("WarGear8.Position"), wargear4);
        ItemStack miniwargear1 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear1.Item")));
        meta = miniwargear1.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear1.Titel"));
        miniwargear1.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("MiniWarGear1.Position"), miniwargear1);
        ItemStack miniwargear2 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear2.Item")));
        meta = miniwargear2.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear2.Titel"));
        miniwargear2.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("MiniWarGear2.Position"), miniwargear2);
        ItemStack miniwargear3 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear7.Item")));
        meta = miniwargear3.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear7.Titel"));
        miniwargear3.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("MiniWarGear7.Position"), miniwargear3);
        ItemStack miniwargear4 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear8.Item")));
        meta = miniwargear4.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("MiniWarGear8.Titel"));
        miniwargear4.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("MiniWarGear8.Position"), miniwargear4);
        ItemStack airship1 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("AirShip1.Item")));
        meta = airship1.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("AirShip1.Titel"));
        airship1.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("AirShip1.Position"), airship1);
        ItemStack airship2 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("AirShip2.Item")));
        meta = airship2.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("AirShip2.Titel"));
        airship2.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("AirShip2.Position"), airship2);
        ItemStack airship3 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("AirShip3.Item")));
        meta = airship3.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("AirShip3.Titel"));
        airship3.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("AirShip3.Position"), airship3);
        ItemStack airship4 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("AirShip4.Item")));
        meta = airship4.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("AirShip4.Titel"));
        airship4.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("AirShip4.Position"), airship4);
        ItemStack airship5 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("AirShip5.Item")));
        meta = airship5.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("AirShip5.Titel"));
        airship5.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("AirShip5.Position"), airship5);
        ItemStack airship6 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("AirShip6.Item")));
        meta = airship6.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("AirShip6.Titel"));
        airship6.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("AirShip6.Position"), airship6);
        ItemStack airship7 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("AirShip7.Item")));
        meta = airship7.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("AirShip7.Titel"));
        airship7.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("AirShip7.Position"), airship7);
        ItemStack airship8 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("AirShip8.Item")));
        meta = airship8.getItemMeta();
        meta.setDisplayName(BauFactory.getInstance().configMessage.cfg.getString("AirShip8.Titel"));
        airship8.setItemMeta(meta);
        inv1.setItem(BauFactory.getInstance().configMessage.cfg.getInt("AirShip8.Position"), airship8);
        ItemStack back = new ItemStack(Material.SLIME_BALL);
        meta = back.getItemMeta();
        meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.BackButton.Titel"));
        back.setItemMeta(meta);
        inv1.setItem(53, back);
    }return inv1;
    }
    private Inventory zeit(Player p){
            FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
            Inventory inv3 = Bukkit.createInventory(null, 18, BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Titel"));
        {
            ItemStack zeitday = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item1.Item")));
            ItemMeta meta = zeitday.getItemMeta();
            meta = zeitday.getItemMeta();
            meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item1.Titel"));
            zeitday.setItemMeta(meta);
            inv3.addItem(zeitday);
            ItemStack zeit6000 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item2.Item")));
            meta = zeit6000.getItemMeta();
            meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item2.Titel"));
            zeit6000.setItemMeta(meta);
            inv3.setItem(3, zeit6000);
            ItemStack zeit18000 = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item3.Item")));
            meta = zeit18000.getItemMeta();
            meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item3.Titel"));
            zeit18000.setItemMeta(meta);
            inv3.setItem(5, zeit18000);
            ItemStack zeitnight = new ItemStack(Material.valueOf(BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item4.Item")));
            meta = zeitnight.getItemMeta();
            meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Item4.Titel"));
            zeitnight.setItemMeta(meta);
            inv3.setItem(8, zeitnight);
            ItemStack back = new ItemStack(Material.SLIME_BALL);
            ItemMeta meta1 = back.getItemMeta();
            meta1 = back.getItemMeta();
            meta1.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.BackButton.Titel"));
            back.setItemMeta(meta1);
            inv3.setItem(13, back);
            if (p.getWorld().getTime() == 6000) {
            ItemStack tntst = new ItemStack(351, 1, (short) 10);
            meta = tntst.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "§eStatus §2An");
            tntst.setItemMeta(meta);
            inv3.setItem(9, tntst);
            p.closeInventory();
            p.openInventory(inv3);
        } else {
            ItemStack tntst1 = new ItemStack(351, 1, (short) 8);
            meta = tntst1.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "§eStatus §4Aus");
            tntst1.setItemMeta(meta);
            inv3.setItem(9, tntst1);
            p.closeInventory();
            p.openInventory(inv3);
        }
            if (p.getWorld().getTime() == 10000) {
                ItemStack tntst = new ItemStack(351, 1, (short) 10);
                meta = tntst.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "§eStatus §2An");
                tntst.setItemMeta(meta);
                inv3.setItem(12, tntst);
                p.closeInventory();
                p.openInventory(inv3);
            } else {
                ItemStack tntst1 = new ItemStack(351, 1, (short) 8);
                meta = tntst1.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "§eStatus §4Aus");
                tntst1.setItemMeta(meta);
                inv3.setItem(12, tntst1);
                p.closeInventory();
                p.openInventory(inv3);
            }
            if (p.getWorld().getTime() == 13000) {
                ItemStack tntst = new ItemStack(351, 1, (short) 10);
                meta = tntst.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "§eStatus §2An");
                tntst.setItemMeta(meta);
                inv3.setItem(17, tntst);
                p.closeInventory();
                p.openInventory(inv3);
            } else {
                ItemStack tntst1 = new ItemStack(351, 1, (short) 8);
                meta = tntst1.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "§eStatus §4Aus");
                tntst1.setItemMeta(meta);
                inv3.setItem(17, tntst1);
                p.closeInventory();
                p.openInventory(inv3);
            }
            if (p.getWorld().getTime() == 18000) {
                ItemStack tntst = new ItemStack(351, 1, (short) 10);
                meta = tntst.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "§eStatus §2An");
                tntst.setItemMeta(meta);
                inv3.setItem(14, tntst);
                p.closeInventory();
                p.openInventory(inv3);
            } else {
                ItemStack tntst1 = new ItemStack(351, 1, (short) 8);
                meta = tntst1.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "§eStatus §4Aus");
                tntst1.setItemMeta(meta);
                inv3.setItem(14, tntst1);
                p.closeInventory();
                p.openInventory(inv3);
            }
        }return inv3;
    }
    private Inventory weltoption(Player p) {
            FactoryWorld fw = BauFactory.getInstance().getWorldFromOwner(p.getUniqueId());
            Inventory inv2 = Bukkit.createInventory(null, 18, BauFactory.getInstance().configMessage.cfg.getString("GUI.mainmenu.Item2.Titel"));
        {
            ItemStack tnt = new ItemStack(Material.TNT);
            ItemMeta meta = tnt.getItemMeta();
            meta = tnt.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "§aTNT Schaden");
            tnt.setItemMeta(meta);
            inv2.addItem(tnt);
            ItemStack back = new ItemStack(Material.SLIME_BALL);
            meta = back.getItemMeta();
            meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.BackButton.Titel"));
            back.setItemMeta(meta);
            inv2.setItem(13, back);
            ItemStack fire = new ItemStack(Material.LAVA_BUCKET);
            meta = fire.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "§aFeuer Schaden");
            fire.setItemMeta(meta);
            inv2.setItem(8, fire);
            ItemStack time = new ItemStack(Material.WATCH);
            meta = time.getItemMeta();
            meta.setDisplayName(ChatColor.RED + BauFactory.getInstance().configMessage.cfg.getString("GUI.worldoption.watch.Titel"));
            time.setItemMeta(meta);
            inv2.setItem(4, time);
            p.openInventory(inv2);
            if (fw.isTntDamage()) {
                ItemStack tntst = new ItemStack(351, 1, (short) 10);
                meta = tntst.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "§eStatus §2An");
                tntst.setItemMeta(meta);
                inv2.setItem(9, tntst);
                p.closeInventory();
                p.openInventory(inv2);
            } else {
                ItemStack tntst1 = new ItemStack(351, 1, (short) 8);
                meta = tntst1.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "§eStatus §4Aus");
                tntst1.setItemMeta(meta);
                inv2.setItem(9, tntst1);
                p.closeInventory();
                p.openInventory(inv2);
            }
            if (fw.isFireDamage()) {
                ItemStack tntst = new ItemStack(351, 1, (short) 10);
                meta = tntst.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "§eStatus §2An");
                tntst.setItemMeta(meta);
                inv2.setItem(17, tntst);
                p.closeInventory();
                p.openInventory(inv2);
            } else {
                ItemStack tntst1 = new ItemStack(351, 1, (short) 8);
                meta = tntst1.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "§eStatus §4Aus");
                tntst1.setItemMeta(meta);
                inv2.setItem(17, tntst1);
                p.closeInventory();
                p.openInventory(inv2);
            }
        }return inv2;
    }
}