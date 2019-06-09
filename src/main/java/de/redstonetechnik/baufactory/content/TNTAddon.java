package de.redstonetechnik.baufactory.content;

import de.redstonetechnik.baufactory.main.BauFactory;
import de.redstonetechnik.baufactory.content.TNTAddon;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

 public class TNTAddon implements Listener, Runnable {
     private static final Map<World, TNTAddon> allTracer = new HashMap();
     private final World world;
     private final BukkitTask task;
     private final Set<Location> locations;
     private boolean printed;

     private TNTAddon(World world) {
         this.world = world;
         this.locations = new HashSet();
         this.printed = false;
         this.task = Bukkit.getScheduler().runTaskTimer(BauFactory.getInstance(), this, 1L, 1L);
         Bukkit.getPluginManager().registerEvents(this, BauFactory.getInstance());
         allTracer.put(world, this);
     }

     private void stop() {
         this.task.cancel();
         HandlerList.unregisterAll(this);
     }

     public void end() {
         this.stop();
         if (!this.printed) {
             this.locations.clear();
             allTracer.remove(this.world);
         }

     }

     public void show() {
         this.stop();
         this.printed = true;
         Set<Location> unsetLoc = new HashSet();
         Iterator var2 = this.locations.iterator();

         while(var2.hasNext()) {
             Location l = (Location)var2.next();
             Block b = this.world.getBlockAt(l);
             if (b.getType() != Material.AIR) {
                 unsetLoc.add(l);
             } else {
                 b.setType(Material.STAINED_GLASS);
                 b.setData((byte)1);
             }
         }

         this.locations.removeAll(unsetLoc);
     }

     public void hide() {
         if (this.printed) {
             Iterator var1 = this.locations.iterator();

             while(var1.hasNext()) {
                 Location l = (Location)var1.next();
                 Block b = this.world.getBlockAt(l);
                 if (b.getType() == Material.STAINED_GLASS && b.getData() == 1) {
                     b.setType(Material.AIR);
                 }
             }

             this.printed = false;
             this.end();
         }
     }

     @EventHandler
     public void onUnloadWorld(WorldUnloadEvent e) {
         if (e.getWorld().equals(this.world)) {
             this.printed = false;
             this.end();
         }

     }

     public void run() {
         if (this.locations.size() < 20000) {
             Iterator var1 = this.world.getEntitiesByClass(TNTPrimed.class).iterator();

             while(var1.hasNext()) {
                 Entity e = (Entity)var1.next();
                 this.locations.add(e.getLocation());
             }

             if (this.locations.size() >= 20000) {
                 var1 = this.world.getPlayers().iterator();

                 while(var1.hasNext()) {
                     Player p = (Player)var1.next();
                     p.sendMessage("§6BauSystem§8» §7§cEs werden keine weiteren Positionen mehr erfasst (20.000 Block Limit)");
                 }
             }
         }

     }

     public static void newTracer(World w) {
         if (allTracer.containsKey(w)) {
             TNTAddon existing = (TNTAddon)allTracer.get(w);
             existing.end();
         }

         new TNTAddon(w);
     }

     public static TNTAddon get(World w) {
         return (TNTAddon)allTracer.get(w);
     }
 }

 //#Code by. Yaruma3341 muss aber persönlich sagen das es schon so efficent ist das ich nichts weiter dran ändern wollte oder musste.