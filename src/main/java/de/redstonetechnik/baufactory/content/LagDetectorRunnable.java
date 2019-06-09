package de.redstonetechnik.baufactory.content;

import de.redstonetechnik.baufactory.main.BauFactory;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

public class LagDetectorRunnable implements Runnable {
	private HashMap<UUID, Long> timerstart = new HashMap();

	public LagDetectorRunnable() {
	}

	public void run() {
		Iterator var1 = Bukkit.getWorlds().iterator();

		while (var1.hasNext()) {
			World world = (World) var1.next();
			world.setStorm(false);
			world.setThundering(false);
			world.setMonsterSpawnLimit(0);
			world.setAnimalSpawnLimit(0);
			world.setGameRuleValue("announceAdvancements", "false");

			UUID worldOwner;
			try {
				worldOwner = UUID.fromString(world.getName());
			} catch (IllegalArgumentException var8) {
				continue;
			}

			int players = 0;
			int other = 0;
			Iterator var6 = world.getEntities().iterator();

			Entity e;
			while (var6.hasNext()) {
				e = (Entity) var6.next();
				if (e instanceof Player) {
					++players;
				} else {
					++other;
				}
			}

			if (this.timerstart.containsKey(worldOwner)) {
				if (players == 0) {
					if (System.currentTimeMillis() - (Long) this.timerstart.get(worldOwner) > 6000L) {
						unloadWorld(worldOwner);
						continue;
					}
				} else {
					this.timerstart.remove(worldOwner);
				}
			} else if (players == 0) {
				this.timerstart.put(worldOwner, System.currentTimeMillis());
			}

			if (other > 800) {
				var6 = world.getEntities().iterator();

				while (var6.hasNext()) {
					e = (Entity) var6.next();
					if (!(e instanceof Player)) {
						e.remove();
					}
				}
			}
		}

	}


	public static void unloadWorld(UUID owner) {
		World world = Bukkit.getWorld(owner.toString());
		if (world != null) {
			Iterator var2 = world.getPlayers().iterator();

			while (var2.hasNext()) {
				Player p = (Player) var2.next();
				p.teleport(BauFactory.getInstance().config.spawn);
				p.setGameMode(GameMode.ADVENTURE);
			}

			Chunk[] var6 = world.getLoadedChunks();
			int var7 = var6.length;

			for (int var4 = 0; var4 < var7; ++var4) {
				Chunk c = var6[var4];
				c.unload();
			}

			if (!Bukkit.unloadWorld(world, true)) {
				System.out.println("Unloading world failed!");
			}

			System.gc();
		}

	}
	public static void broadcast(UUID owner, String string) {
		Iterator var2 = getWorld(owner).getPlayers().iterator();

		while(var2.hasNext()) {
			Player p = (Player)var2.next();
			p.sendMessage(string);
		}

	}
	public static World getWorld(UUID owner) {
		return Bukkit.getWorld(owner.toString());
	}
}
