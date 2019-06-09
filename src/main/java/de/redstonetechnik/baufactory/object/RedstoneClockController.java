package de.redstonetechnik.baufactory.object;

import com.google.common.collect.Maps;
import org.bukkit.Location;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class RedstoneClockController {
    private static final ConcurrentMap<Location, RedstoneClock> activeMap = Maps.newConcurrentMap();

    public static void addRedstone(Location location) throws Exception {
        if (contains(location)) {
        } else {
            activeMap.put(location, new RedstoneClock(location));
        }
    }

    public static boolean contains(Location location) {
        return activeMap.containsKey(location);
    }

    public static void removeRedstoneByLocation(Location location) {
        activeMap.remove(location);
    }

    public static void removeRedstoneByObject(RedstoneClock rc) {
        if (activeMap.containsValue(rc)) {
            activeMap.remove(rc.getLocation());
        }
    }

    public static RedstoneClock getRedstoneClock(Location location) {
        return activeMap.get(location);
    }

    public static Map<Location, RedstoneClock> getHashMap() {
        return activeMap;
    }

    public static Collection<RedstoneClock> getAll() {
        return activeMap.values();
    }

    public static Collection<Location> getAllLoc() {
        return activeMap.keySet();
    }

}
