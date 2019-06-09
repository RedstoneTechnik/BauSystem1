package de.redstonetechnik.baufactory.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class RedstoneListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onRedstoneClock(BlockRedstoneEvent e) {
        if (e.getBlock().getType() == Material.REDSTONE_WIRE
                || checkRepeaterType(e.getBlock())) {
            if (e.getOldCurrent() == 0) {
                Util.checkAndUpdateRedstoneClockState(e.getBlock());
            }
        }
    }

    private boolean checkRepeaterType(Block block) {
        boolean result = false;
        try {
            if (block.getType() == Material.DIODE) {
                result = true;
            }
        } catch (NoSuchFieldError e) {
            //1.12.2 and older version compatibility
            if (block.getType() == Material.getMaterial("DIODE_BLOCK_ON")
                    || block.getType() == Material.getMaterial("DIODE_BLOCK_OFF")) {
                result = true;
            }
        }
        return result;
    }
}
