package de.redstonetechnik.baufactory.listener;

import de.redstonetechnik.baufactory.main.BauFactory;
import de.redstonetechnik.baufactory.object.RedstoneClock;
import de.redstonetechnik.baufactory.object.RedstoneClockController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

public class PistonListener implements Listener {
    @EventHandler
    public void onPistonExtendEvent(BlockPistonExtendEvent e) {
        if (RedstoneClockController.contains(e.getBlock().getLocation())) {
            RedstoneClock redstoneClock = RedstoneClockController.getRedstoneClock(e.getBlock().getLocation());
            if (redstoneClock.getLastStatus() == 1) {
                if (!redstoneClock.isTimedOut()) {
                    if (redstoneClock.getNumberOfClock() >= BauFactory.getInstance().getConfig().getInt("MaxPulses")) {
                        Util.removeRedstoneClock(e.getBlock());
                    } else {
                        redstoneClock.addOneToClock();
                        redstoneClock.updateStatus(0);
                    }
                } else {
                    RedstoneClockController.removeRedstoneByObject(redstoneClock);
                }
            }
        } else {
            try {
                RedstoneClockController.addRedstone(e.getBlock().getLocation());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onPistonRetractEvent(BlockPistonRetractEvent e) {
        if (RedstoneClockController.contains(e.getBlock().getLocation())) {
            RedstoneClock redstoneClock = RedstoneClockController.getRedstoneClock(e.getBlock().getLocation());
            redstoneClock.updateStatus(1);
        }
    }
}
