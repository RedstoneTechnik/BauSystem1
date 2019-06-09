package net.thecobix.tag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map.Entry;

public abstract class Extension {

    public static ArrayList<Extension> extensions = new ArrayList<>();
    
    public abstract Tag readTag(Class<? extends Tag> clazz, byte[] data) throws IOException;
    public abstract Entry<Class<? extends Tag>, byte[]> splitData(DataInputStream dis, int id, byte[] data, int start) throws IOException;
    
    protected static byte[] subSequence(byte[] fully, int start, int end) {
        byte[] out = new byte[(end - start)+2];
        int ind = -1;
        for(int i = 0; i < fully.length; i++)
        {
            if(i >= start && i <= end) {
                ind ++;
                out[ind] = fully[i];
            }
        }
        return out;
    }
    
    protected byte[] readByteArray(DataInputStream in) {
        try
        {
            return read(in, readVarInt(in));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    protected void writeVarInt(DataOutputStream out, int paramInt) throws IOException {
        while (true) {
            if ((paramInt & 0xFFFFFF80) == 0) {
              out.writeByte(paramInt);
              return;
            }

            out.writeByte(paramInt & 0x7F | 0x80);
            paramInt >>>= 7;
        }
    }
    
    protected String readString(DataInputStream dis)
    {
        int i = 0;
        try
        {
            i = readVarInt(dis);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (i < 0)
        {
            throw new NullPointerException("The received encoded string buffer length is less than zero! Weird string!");
        }
        else
        {
            String s = new String(read(dis, i), StandardCharsets.UTF_8);
            return s;
        }
    }
    
    protected byte[] read(DataInputStream in, int amount) {
        byte[] out = new byte[amount];
        for(int i = 0; i < out.length; i++)
        {
            try
            {
                out[i] = in.readByte();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return out;
    }

    protected int readVarInt(DataInputStream in) throws IOException {
        int i = 0;
        int j = 0;
        while (true) {
            int k = in.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) throw new RuntimeException("VarInt too big");
            if ((k & 0x80) != 128) break;
        }
        return i;
    }
    
    protected int readVarIntSize(DataInputStream in) throws IOException {
        int i = 0;
        int j = 0;
        int s = 0;
        while (true) {
            s ++;
            int k = in.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) throw new RuntimeException("VarInt too big");
            if ((k & 0x80) != 128) break;
        }
        return s;
    }
    
    protected Entry<Integer, Integer> readVarIntAndSize(DataInputStream in) throws IOException {
        int i = 0;
        int j = 0;
        int s = 0;
        while (true) {
            s ++;
            int k = in.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) throw new RuntimeException("VarInt too big");
            if ((k & 0x80) != 128) break;
        }
        return new AbstractMap.SimpleEntry(s, i);
    }
    
    public static boolean isRegistered(Class<? extends Extension> clazz) {
        for(Extension e : extensions) {
            try { 
                e.getClass().asSubclass(clazz);
                return true;
            } catch(ClassCastException ex) {
                continue;
            }
        }
        return false;
    }
    
}
