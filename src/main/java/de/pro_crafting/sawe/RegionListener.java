package de.pro_crafting.sawe;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import de.redstonetechnik.baufactory.content.FactoryWorld;
import de.redstonetechnik.baufactory.content.WorldPlayer;
import de.redstonetechnik.baufactory.main.BauFactory;

public class RegionListener implements Listener {
    private JavaPlugin plugin;

    public RegionListener(JavaPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void playerCommandHandler(PlayerCommandPreprocessEvent event) {
        WorldEditPlugin we = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        String command = event.getMessage().split(" ")[0];
        if (!isWorldEditCommand(command)) {
            return;
        }
        Player p = event.getPlayer();
        try {
            if (p.hasPermission("bau.team")) {
                return;
            }
            FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
            if (fw == null) {
                p.sendMessage("§cDu darfst hier kein Worldedit benutzen.");
                event.setCancelled(true);
                return;
            }
            WorldPlayer wp = fw.getMember(p.getUniqueId());
            if (wp != null) {
                if (wp.canWorldEdit == false || fw == null) {
                    p.sendMessage("§cDu darfst hier kein Worldedit benutzen.");
                    event.setCancelled(true);
                }
            } else {
                if (fw.getOwner().equals(p.getUniqueId())) {
                    return;
                }
                p.sendMessage("§cDu darfst hier kein Worldedit benutzen.");
                event.setCancelled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        try {
            if (p.hasPermission("bau.team")) {
                return;
            }
            FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
            if (fw == null) {
                p.sendMessage("§cDu darfst nicht bauen.");
                e.setCancelled(true);
                return;
            }
            WorldPlayer wp = fw.getMember(p.getUniqueId());
            if (wp != null) {
                if (wp.canBuild == false) {
                    p.sendMessage("§cDu darfst nicht bauen.");
                    e.setCancelled(true);
                }
            } else {
                if (fw.getOwner().equals(p.getUniqueId())) {
                    return;
                }
                e.setCancelled(true);
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onB(BlockPlaceEvent e) throws SQLException {
        Player p = e.getPlayer();
        if (p.hasPermission("bau.team")) {
            return;
        }
        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
        if (fw == null) {
            p.sendMessage("§cDu darfst nicht bauen.");
            e.setCancelled(true);
            return;
        }
        WorldPlayer wp = fw.getMember(p.getUniqueId());
        if (wp != null) {
            if (wp.canBuild == false) {
                p.sendMessage("§cDu darfst nicht bauen.");
                e.setCancelled(true);
            }
        } else {
            if (fw.getOwner().equals(p.getUniqueId())) {
                return;
            }
            e.setCancelled(true);
        }
    }

    private boolean isWorldEditCommand(String command) {
        if (command.startsWith("/")) {
            command = command.replaceFirst("/", "");
        }
        command = command.toLowerCase();
        return ((WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit")).getWorldEdit().getPlatformManager()
                .getCommandManager().getDispatcher().get(command) != null;
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(e.getEntity().getWorld().getName());
        if (fw != null) {
            if (fw.isTntDamage() == false) e.setCancelled(true);
        }
    }
    @EventHandler
    public void onFireDamage(BlockBurnEvent e) {
        FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(e.getIgnitingBlock().getWorld().getName());
        if (fw != null) {
            if (fw.isFireDamage() == false) e.setCancelled(true);
        }
    }

}
