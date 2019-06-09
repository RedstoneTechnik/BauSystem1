package de.redstonetechnik.baufactory.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class BungeeUtil implements PluginMessageListener {
    
    private String[] lastPlayerList = null;
    private boolean wait = false;
    private boolean lastHasInvitation;
    
    public BungeeUtil()
    {
        Bukkit.getMessenger().registerIncomingPluginChannel(BauFactory.getInstance(), "BungeeCord", this);
    }

    public void sendToServer(Player p, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        p.sendPluginMessage(BauFactory.getInstance(), "BungeeCord", out.toByteArray());
    }
    
    public void broadcast(String msg, Player from) {
        sendMessage(msg, from);
    }
    
    public void sendMessage(String msg, Player from) {
	ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("BroadcastWS");
        out.writeUTF("channelMessage");
        out.writeUTF(Bukkit.getServerName());
        out.writeUTF(from.getUniqueId().toString());
        out.writeUTF(msg);
        from.sendPluginMessage(BauFactory.getInstance(), "BungeeCord", out.toByteArray());
    }
    
    public String[] getPlayers(String server) {
        this.wait = true;
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerList");
        out.writeUTF(server);
        Bukkit.getServer().sendPluginMessage(BauFactory.getInstance(), "BungeeCord", out.toByteArray());
        while(this.wait) {
            try
            {
                Thread.sleep(1);
            }
            catch (InterruptedException e)
            {
            }
        }
        return this.lastPlayerList;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player p, byte[] message)
    {
        if(!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if(subchannel.equals("PlayerList")) {
            String server = in.readUTF();
            String msg = in.readUTF();
            System.out.println(msg);
            this.lastPlayerList = msg.split(", ");
            this.wait = false;
        }
    }
    
}
