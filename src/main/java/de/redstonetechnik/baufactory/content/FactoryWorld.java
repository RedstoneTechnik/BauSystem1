package de.redstonetechnik.baufactory.content;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import de.redstonetechnik.baufactory.main.CobixTagFormatSupported;
import de.redstonetechnik.baufactory.main.Registry;
import de.redstonetechnik.baufactory.main.BauFactory;
import lombok.Getter;
import lombok.Setter;
import net.thecobix.tag.TagCompound;

public class FactoryWorld extends CobixTagFormatSupported {

    private int regId;

    @Getter
    public World wrapped;

    @Getter
    private Registry<WorldPlayer> members = new Registry<>(WorldPlayer.class, "members");

    @Getter
    private String worldAddress;

    @Getter
    private UUID owner;

    @Getter
    private boolean loading;

    @Getter
    @Setter
    private boolean tntDamage;
    @Getter
    @Setter
    private boolean fireDamage;

    private BukkitTask task;

    public FactoryWorld() {
        task = Bukkit.getScheduler().runTaskTimer(BauFactory.getInstance(), new LagDetectorRunnable(), 20, 20);
    }

    public FactoryWorld(UUID ownaz) {
        this.owner = ownaz;
        this.worldAddress = ownaz.toString();
        task = Bukkit.getScheduler().runTaskTimer(BauFactory.getInstance(), new LagDetectorRunnable(), 20, 20);
    }

    @Override
    public TagCompound save() {
        TagCompound out = new TagCompound(regId + "");
        out.put(members.save());
        out.putString("worldAddress", worldAddress);
        out.putString("owner", owner.toString());
        out.putShort("tntdmg", (short) (tntDamage ? 1 : 0));
        out.putShort("firedmg", (short) (fireDamage ? 1 : 0));
        return out;
    }

    @Override
    public void load(TagCompound tc) {
        members.load(tc.getTagCompound("members"));
        worldAddress = tc.getTagString("worldAddress").getValue();
        owner = UUID.fromString(tc.getTagString("owner").getValue());
        tntDamage = tc.getTagShort("tntdmg").getValue() == 1 ? true : false;
        fireDamage = tc.getTagShort("firedmg").getValue() == 1 ? true : false;
    }

    public void loadWorld(UUID ownaz) throws IOException {
        loading = true;
        File world = new File("plugins/BauSystem/worlds/" + ownaz.toString());
        File region = new File("plugins/WorldGuard/worlds/" + ownaz.toString());
        if (region.exists() && region.isDirectory()) {
        } else {
            FileUtils.copyDirectory(new File(BauFactory.getInstance().config.regionDir), new File("plugins/WorldGuard/worlds/" + ownaz.toString()));
        }
        if (world.exists() && world.isDirectory()) {
            FileUtils.moveDirectory(world, new File(ownaz.toString()));
            wrapped = Bukkit.createWorld(new WorldCreator(ownaz.toString()));
        } else {
            FileUtils.copyDirectory(new File(BauFactory.getInstance().config.backupDir), new File(ownaz.toString()));
            wrapped = Bukkit.createWorld(new WorldCreator(ownaz.toString()));
        }
        loading = false;
    }

    public void unloadWorld() {
        wrapped.save();
        for (final WorldPlayer wp : members.getAll().values()) {
            if (wp.getPlayer() != null) {
                if (wp.getPlayer().getWorld().getUID().equals(wrapped.getUID())) {
                    wp.getPlayer().sendMessage(BauFactory.S_PREFIX + "§cDie Welt wurde aus dem Arbeitsspeicher gelöscht...");
                    wp.getPlayer().teleport(BauFactory.getInstance().config.spawn);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(BauFactory.getInstance(), new Runnable() {
                        public void run() {
                            wp.getPlayer().setGameMode(GameMode.SURVIVAL);
                        }
                    }, 20L);
                }
            }
        }
        if (!unloadWorld(worldAddress)) {
            BauFactory.log("FactoryWorld", "Unable to unload " + worldAddress);
        }
        wrapped = null;
        try {
            FileUtils.moveDirectory(new File(worldAddress), new File("plugins/BauSystem/worlds/" + worldAddress));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WorldPlayer getMember(UUID uuid) {
        for (WorldPlayer wp : members.getAll().values()) {
            if (wp.getUuid().equals(uuid)) return wp;
        }
        return null;
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

    public void broadcast(String string) {
        for (Player p : wrapped.getPlayers()) {
            p.sendMessage(string);
        }
    }

}
