package de.redstonetechnik.baufactory.JoinListener;

import de.redstonetechnik.baufactory.api.ClanAPI;
import de.redstonetechnik.baufactory.api.ScoreboardAPI;
import de.redstonetechnik.baufactory.content.SystemManager;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class PlayerJoinListener implements Listener {
    public PlayerJoinListener() {
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Scoreboard board = ScoreboardAPI.getScoreboard();
        Team admin = ScoreboardAPI.getAdmin();
        Team dev = ScoreboardAPI.getDeveloper();
        Team mod = ScoreboardAPI.getModerator();
        Team sup = ScoreboardAPI.getSupporter();
        Team yt = ScoreboardAPI.getYouTuber();
        Team premium = ScoreboardAPI.getPremium();
        Team spieler = ScoreboardAPI.getPlayer();
        admin = board.registerNewTeam("aaa");
        dev = board.registerNewTeam("bbb");
        mod = board.registerNewTeam("ccc");
        sup = board.registerNewTeam("ddd");
        yt = board.registerNewTeam("eee");
        premium = board.registerNewTeam("fff");
        spieler = board.registerNewTeam("ggg");
        Iterator var11 = Bukkit.getOnlinePlayers().iterator();

        while(var11.hasNext()) {
            Player onlineplayers = (Player)var11.next();
            if (onlineplayers.hasPermission("prefix.admin")) {
                loadTeam(onlineplayers, board, admin, "aaa", ChatColor.translateAlternateColorCodes('&', SystemManager.getPlayerTag("admin")));
            } else if (onlineplayers.hasPermission("prefix.dev")) {
                loadTeam(onlineplayers, board, dev, "bbb", ChatColor.translateAlternateColorCodes('&', SystemManager.getPlayerTag("dev")));
            } else if (onlineplayers.hasPermission("prefix.mod")) {
                loadTeam(onlineplayers, board, mod, "ccc", ChatColor.translateAlternateColorCodes('&', SystemManager.getPlayerTag("mod")));
            } else if (onlineplayers.hasPermission("prefix.sup")) {
                loadTeam(onlineplayers, board, sup, "ddd", ChatColor.translateAlternateColorCodes('&', SystemManager.getPlayerTag("sup")));
            } else if (onlineplayers.hasPermission("prefix.yt")) {
                loadTeam(onlineplayers, board, yt, "eee", ChatColor.translateAlternateColorCodes('&', SystemManager.getPlayerTag("yt")));
            } else if (onlineplayers.hasPermission("prefix.premium")) {
                loadTeam(onlineplayers, board, premium, "fff", ChatColor.translateAlternateColorCodes('&', SystemManager.getPlayerTag("premium")));
            } else if (onlineplayers.hasPermission("prefix.spieler")) {
                loadTeam(onlineplayers, board, spieler, "ggg", ChatColor.translateAlternateColorCodes('&', SystemManager.getPlayerTag("spieler")));
            }
        }

    }

    private static void loadTeam(Player player, Scoreboard board, Team team, String teamname, String prefix) {
        team = board.getTeam(teamname);
        if (team == null) {
            team = board.registerNewTeam(teamname);
        }

        if (team.hasEntry(player.getName())) {
            ;
        }

        team.removeEntry(player.getName());
        team.setPrefix(prefix);
        if (ClanAPI.isPlayerInClan(player.getUniqueId())) {
            String clanTag = ClanAPI.getClanTag(player.getUniqueId());
            player.setCustomName(team.getPrefix() + player.getName());
            player.setPlayerListName(team.getPrefix() + player.getName() + " §7[§e" + clanTag + "§7]");
            team.addPlayer(player);
        } else {
            player.setCustomName(team.getPrefix() + player.getPlayerListName());
            player.setPlayerListName(team.getPrefix() + player.getName());
            team.addPlayer(player);
        }

        player.setScoreboard(board);
    }
}
