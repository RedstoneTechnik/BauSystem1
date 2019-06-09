package de.redstonetechnik.baufactory.listener;

import de.redstonetechnik.baufactory.main.BauFactory;
import de.redstonetechnik.baufactory.object.RedstoneClock;
import de.redstonetechnik.baufactory.object.RedstoneClockController;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

class Util {
    static void checkAndUpdateRedstoneClockState(Block block) {
        if (!RedstoneClockController.contains(block.getLocation())) {
            try {
                RedstoneClockController.addRedstone(block.getLocation());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            RedstoneClock redstoneClock = RedstoneClockController.getRedstoneClock(block.getLocation());
            if (!redstoneClock.isTimedOut()) {
                if (redstoneClock.getNumberOfClock()
                        >= BauFactory.getInstance().config.cfg.getInt("Features.Redstoneclock.MaxPulses")) {
                    removeRedstoneClock(block);
                } else {
                    redstoneClock.addOneToClock();
                }
            } else {
                RedstoneClockController.removeRedstoneByObject(redstoneClock);
            }
        }
    }

    static void removeRedstoneClock(Block block) {
        RedstoneClock redstoneClock = RedstoneClockController.getRedstoneClock(block.getLocation());
        if (BauFactory.getInstance().config.cfg.getBoolean("Features.Redstoneclock.AutoRemove")) {
            if (BauFactory.getInstance().config.cfg.getBoolean("Features.Redstoneclock.DropItems")) {
                block.breakNaturally();
            } else {
                block.setType(Material.AIR);
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(BauFactory.getInstance(), () -> {
                if (BauFactory.getInstance().config.cfg.getBoolean("Features.Redstoneclock.CreateSignWhenClockIsBreak")) {
                    Sign sign;
                    try {
                        block.setType(Material.SIGN, false);
                        BlockState blockState = block.getState();
                        sign = (Sign) blockState;
                    } catch (ClassCastException error) {
                        block.setType(Material.getMaterial("SIGN_POST"), false);
                        BlockState blockState = block.getState();
                        sign = (Sign) blockState;
                    }
                    sign.setLine(0, BauFactory.getInstance().config.cfg.getString("Features.Redstoneclock.Sign.Line1").replace("&", "§"));
                    sign.setLine(1, BauFactory.getInstance().config.cfg.getString("Features.Redstoneclock.Sign.Line2").replace("&", "§"));
                    sign.setLine(2, BauFactory.getInstance().config.cfg.getString("Features.Redstoneclock.Sign.Line3").replace("&", "§"));
                    sign.setLine(3, BauFactory.getInstance().config.cfg.getString("Features.Redstoneclock.Sign.Line4").replace("&", "§"));
                    sign.update(false, false);
                } else {
                    block.setType(Material.AIR);
                }
                RedstoneClockController.removeRedstoneByLocation(block.getLocation());
            }, 1L);
        }
        if (!redstoneClock.getDetected()) {
            redstoneClock.setDetected(true);

        }

    }
}