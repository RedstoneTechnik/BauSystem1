package de.redstonetechnik.baufactory.api;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardAPI {
    private static Team admin;
    private static Team dev;
    private static Team mod;
    private static Team sup;
    private static Team yt;
    private static Team premium;
    private static Team player;

    public ScoreboardAPI() {
    }

    public static Scoreboard getScoreboard() {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        return board;
    }

    public static Team getAdmin() {
        return admin;
    }

    public static Team getDeveloper() {
        return dev;
    }

    public static Team getModerator() {
        return mod;
    }

    public static Team getSupporter() {
        return sup;
    }

    public static Team getYouTuber() {
        return yt;
    }

    public static Team getPremium() {
        return premium;
    }

    public static Team getPlayer() {
        return player;
    }
}

