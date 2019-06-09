package de.redstonetechnik.baufactory.listener;

import de.redstonetechnik.baufactory.main.BauFactory;
import de.redstonetechnik.baufactory.object.RedstoneClock;
import de.redstonetechnik.baufactory.object.RedstoneClockController;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class ObserverListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onRedstoneComparatorClock(BlockRedstoneEvent e) {
        if (e.getBlock().getType() == Material.OBSERVER && e.getOldCurrent() == 0) {
            if (!RedstoneClockController.contains(e.getBlock().getLocation())) {
                try {
                    RedstoneClockController.addRedstone(e.getBlock().getLocation());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
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
                } else {
                    redstoneClock.updateStatus(1);
                }
            }
        }
    }

}