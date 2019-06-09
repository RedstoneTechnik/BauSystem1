/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package de.redstonetechnik.baufactory.commands;

import java.util.List;

import de.redstonetechnik.baufactory.content.FactoryWorld;
import de.redstonetechnik.baufactory.content.WorldPlayer;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.google.common.collect.ImmutableList;

import de.redstonetechnik.baufactory.main.BauFactory;

public class CommandTeleport extends BukkitCommand {
    public CommandTeleport() {
	super("tp");
	this.description = "Teleports the given player (or yourself) to another player or coordinates";
	this.usageMessage = "/tp [player] <target> and/or <x> <y> <z>";
    }

    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
	if ((args.length < 1) || (args.length > 4)) {
	    sender.sendMessage(ChatColor.RED + "Usage: " + this.usageMessage);
	    return false;
	}

	// SEAGIANTS
	if (sender instanceof Player) {
	    Player p = (Player) sender;
	    if (p.hasPermission("bukkit.command.teleport") == false) {
		FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
		if (fw == null) {
		    p.sendMessage("Du befindest dich auf keiner Bauwelt!");
		    return false;
		}
		WorldPlayer wp = fw.getMember(p.getUniqueId());
		if (wp == null && !fw.getOwner().equals(p.getUniqueId())) {
		    p.sendMessage("Du bist kein Mitglied dieser Welt!");
		    return false;
		}
		if (!fw.getOwner().equals(p.getUniqueId())) {
		    if (wp.canTeleport == false) {
			p.sendMessage("Du darfst dich auf dieser Welt nicht teleportieren!");
			return false;
		    }
		}
	    }
	}
	// END

	Player player = Bukkit.getPlayerExact(args[0]);

	if (player == null && args.length != 3) {
	    sender.sendMessage("Player not found: " + args[0]);
	    return true;
	}

	if (args.length == 1 || args.length == 3) {
	    if (sender instanceof Player) {
		player = (Player) sender;
	    }
	}

	if (args.length < 3) {
	    Player target = Bukkit.getPlayerExact(args[(args.length - 1)]);
	    if (target == null) {
		sender.sendMessage("Can't find player " + args[(args.length - 1)] + ". No tp.");
		return true;
	    }

	    // SEAGIANTS
	    if (!sender.hasPermission("bukkit.command.teleport")) {
		FactoryWorld fw = BauFactory.getInstance().getWorldByAddress(player.getWorld().getName());
		if (fw == null) {
		    sender.sendMessage("Der Spieler befindet sich auf keiner Bauwelt.");
		    return false;
		}
		FactoryWorld fwt = BauFactory.getInstance().getWorldByAddress(target.getWorld().getName());
		if (fwt == null) {
		    sender.sendMessage("Der Zielspieler befindet sich auf keiner Bauwelt.");
		    return false;
		}
		if (fw.getWrapped().getUID().equals(fwt.getWrapped().getUID()) == false) {
		    sender.sendMessage("Die Spieler sind nicht auf der selben Bauwelt.");
		    return false;
		}
		if (sender instanceof Player) {
		    Player p = (Player) sender;
		    FactoryWorld fwp = BauFactory.getInstance().getWorldByAddress(p.getWorld().getName());
		    if (fwp == null) {
			p.sendMessage("Du bist auf keiner Bauwelt");
			return false;
		    }
		    if (fwt.getWrapped().getUID().equals(fwp.getWrapped().getUID()) == false) {
			p.sendMessage("Das Ziel befindet sich nicht auf deiner Welt");
			return false;
		    }
		}
	    }
	    // END

		player.teleport(target, PlayerTeleportEvent.TeleportCause.COMMAND);
		Command.broadcastCommandMessage(sender,
				"Teleported " + player.getDisplayName() + " to " + target.getDisplayName());
	} else if (player.getWorld() != null) {
		Location playerLocation = player.getLocation();
		double x = getCoordinate(sender, playerLocation.getX(), args[(args.length - 3)]);
		double y = getCoordinate(sender, playerLocation.getY(), args[(args.length - 2)], 0, 0);
		double z = getCoordinate(sender, playerLocation.getZ(), args[(args.length - 1)]);

		if ((x == -30000001.0D) || (y == -30000001.0D) || (z == -30000001.0D)) {
			sender.sendMessage("Please provide a valid location!");
			return true;
		}

		playerLocation.setX(x);
		playerLocation.setY(y);
		playerLocation.setZ(z);

		player.teleport(playerLocation, PlayerTeleportEvent.TeleportCause.COMMAND);
		Command.broadcastCommandMessage(sender, String.format("Teleported %s to %.2f, %.2f, %.2f",
				new Object[] { player.getDisplayName(), Double.valueOf(x), Double.valueOf(y), Double.valueOf(z) }));
	}
		return true;
	}

	private double getCoordinate(CommandSender sender, double current, String input) {
		return getCoordinate(sender, current, input, -30000000, 30000000);
	}

	private double getCoordinate(CommandSender sender, double current, String input, int min, int max) {
		boolean relative = input.startsWith("~");
		double result = (relative) ? current : 0.0D;

		if ((!(relative)) || (input.length() > 1)) {
			boolean exact = input.contains(".");
			if (relative)
				input = input.substring(1);

			double testResult = getCoordinate(sender,current, input);
			if (testResult == -30000001.0D) {
				return -30000001.0D;
			}
			result += testResult;

			if ((!(exact)) && (!(relative)))
				result += 0.5D;
		}
		if ((min != 0) || (max != 0)) {
			if (result < min) {
				result = -30000001.0D;
			}

			if (result > max) {
				result = -30000001.0D;
			}
		}

		return result;
	}

	public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
		Validate.notNull(sender, "Sender cannot be null");
		Validate.notNull(args, "Arguments cannot be null");
		Validate.notNull(alias, "Alias cannot be null");

		if ((args.length == 1) || (args.length == 2)) {
			return super.tabComplete(sender, alias, args);
		}
		return ImmutableList.of();
	}
}