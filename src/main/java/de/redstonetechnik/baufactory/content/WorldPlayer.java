package de.redstonetechnik.baufactory.content;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.redstonetechnik.baufactory.main.CobixTagFormatSupported;
import de.redstonetechnik.baufactory.main.BauFactory;
import lombok.Getter;
import net.thecobix.tag.TagCompound;

public class WorldPlayer extends CobixTagFormatSupported {
    
    @Getter
    private UUID uuid;
    public boolean canWorldEdit, canChangeGamemode, canBuild, canTeleport, canTestblock, canReplace, canReset, canTime;
    @Getter
    private FactoryWorld factoryWorld;
    
    private int regId;
    
    public WorldPlayer() {
	//To be created by registries
    }
    
    public WorldPlayer(UUID id, FactoryWorld world) {
	this.uuid = id;
	this.factoryWorld = world;
    }
    
    public Player getPlayer() {
	return Bukkit.getPlayer(uuid);
    }

    @Override
    public TagCompound save() {
	TagCompound tc = new TagCompound(regId+"");
	tc.putString("uuid", uuid.toString());
	tc.putShort("canWorldEdit", (short) (canWorldEdit ? 1 : 0));
	tc.putShort("canChangeGamemode", (short) (canChangeGamemode ? 1 : 0));
	tc.putShort("canBuild", (short) (canBuild ? 1 : 0));
	tc.putShort("canTeleport", (short) (canTeleport ? 1 : 0));
	tc.putShort("canReset", (short) (canReset ? 1 : 0));
	tc.putShort("canReplace", (short) (canReplace ? 1 : 0));
	tc.putShort("canTestblock", (short) (canTestblock ? 1 : 0));
	tc.putShort("canTime", (short) (canTime ? 1 : 0 ));
	tc.putString("factoryWorld", factoryWorld.getOwner().toString());
	return tc;
    }

    @Override
    public void load(TagCompound tc) {
	uuid = UUID.fromString(tc.getTagString("uuid").getValue());
	canBuild = tc.getTagShort("canBuild").getValue() == 1 ? true : false;
	canTime = tc.getTagShort("canTime").getValue() == 1 ? true : false;
	canWorldEdit = tc.getTagShort("canWorldEdit").getValue() == 1 ? true : false;
	canTeleport = tc.getTagShort("canTeleport").getValue() == 1 ? true : false;
	canChangeGamemode = tc.getTagShort("canChangeGamemode").getValue() == 1 ? true : false;
	canReset = tc.getTagShort("canReset").getValue() == 1 ? true : false;
	canReplace = tc.getTagShort("canReplace").getValue() == 1 ? true : false;
	canTestblock = tc.getTagShort("canTestblock").getValue() == 1 ? true : false;
	Bukkit.getScheduler().runTaskLater(BauFactory.getInstance(), new Runnable() {
	    public void run() {
		factoryWorld = BauFactory.getInstance().getWorldByAddress(tc.getTagString("factoryWorld").getValue());
	    }
	}, 20L);
    }
    
}
