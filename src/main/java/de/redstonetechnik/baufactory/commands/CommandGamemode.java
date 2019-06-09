package de.redstonetechnik.baufactory.commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import com.google.common.collect.ImmutableList;

import de.redstonetechnik.baufactory.content.FactoryWorld;
import de.redstonetechnik.baufactory.content.WorldPlayer;
import de.redstonetechnik.baufactory.main.BauFactory;

public class CommandGamemode extends BukkitCommand {
    private static final List<String> GAMEMODE_NAMES = ImmutableList.of("adventure", "creative", "survival",
	    "spectator");

    public CommandGamemode() {
	super("gm");
	this.description = "Ändert den Spielmodus eines Spielers";
	this.usageMessage = "/gm <mode> [player]";
    }

    public boolean execute(CommandSender sender, String currentAlias, String[] args) {

	// SEAGIANTS
	if (sender instanceof Player) {
	    Player p = (Player) sender;
	    if (p.hasPermission("bukkit.command.gamemode") == false) {
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
		    if (wp.canChangeGamemode == false) {
			p.sendMessage("Du darfst deinen Spielmodus nicht ändern!");
			return false;
		    }
		}
	    }
	}
	// END

	if (args.length == 0) {
	    sender.sendMessage(ChatColor.RED + "Benutzung: " + this.usageMessage);
	    return false;
	}

	String modeArg = args[0];
	String playerArg = sender.getName();

	if (args.length == 2 && sender.hasPermission("bukkit.command.gamemode")) {
	    playerArg = args[1];
	}

	Player player = Bukkit.getPlayerExact(playerArg);

	if (player != null) {
	    int value = -1;
	    try {
		value = Integer.parseInt(modeArg);
	    } catch (NumberFormatException localNumberFormatException) {
	    }
	    GameMode mode = GameMode.getByValue(value);

	    if (mode == null) {
		if ((modeArg.equalsIgnoreCase("creative")) || (modeArg.equalsIgnoreCase("c")))
		    mode = GameMode.CREATIVE;
		else if ((modeArg.equalsIgnoreCase("adventure")) || (modeArg.equalsIgnoreCase("a")))
		    mode = GameMode.ADVENTURE;
		else if ((modeArg.equalsIgnoreCase("spectator")) || (modeArg.equalsIgnoreCase("sp")))
		    mode = GameMode.SPECTATOR;
		else {
		    mode = GameMode.SURVIVAL;
		}
	    }

	    if (mode != player.getGameMode()) {
		player.setGameMode(mode);

		if (mode != player.getGameMode()) {
		    sender.sendMessage("Spielmodus setzen für " + player.getName() + " fehlgeschlagen!");
		} else if (player == sender)
		    Command.broadcastCommandMessage(sender, "Setzte eigenen Spielmodus zu " + mode.toString());
		else
		    Command.broadcastCommandMessage(sender,
			    "Setzte " + player.getName() + "'s Spielmodus zu " + mode.toString());
	    } else {
		sender.sendMessage(player.getName() + " ist bereits im " + mode.toString() + "mode");
	    }
	} else {
	    sender.sendMessage("Spieler konnte nicht gefunden werden: " + playerArg);
	}

	return true;
    }

    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
	Validate.notNull(sender, "Sender cannot be null");
	Validate.notNull(args, "Arguments cannot be null");
	Validate.notNull(alias, "Alias cannot be null");

	if (args.length == 1)
	    return ((List) StringUtil.copyPartialMatches(args[0], GAMEMODE_NAMES,
		    new ArrayList(GAMEMODE_NAMES.size())));
	if (args.length == 2) {
	    return super.tabComplete(sender, alias, args);
	}
	return ImmutableList.of();
    }
}