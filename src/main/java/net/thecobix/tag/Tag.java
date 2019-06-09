package net.thecobix.tag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.Map.Entry;

public abstract class Tag {
    
    /*
     * Tag ids:
     * 
     * 0x01: String
     * 0x02: Int
     * 0x03: Double
     * 0x04: Long
     * 0x05: Short
     * 0x06: Float
     * 0x07: Compound
     * 0x08: Serializeable
     * 0x09: ByteArray
     * 
     */
    
    public Tag(String name)
    {
        this.name = name;
    }

    protected String name;
    protected Object value;
    
    protected abstract byte[] write() throws IOException;
    protected abstract void read(byte[] data) throws IOException;
    
    public String getName()
    {
        return name;
    }
    
    public Object getValue()
    {
        return value;
    }
    
    protected void writeString(DataOutputStream out, String string, Charset charset) throws IOException {
        byte [] bytes = string.getBytes(charset);
        writeVarInt(out, bytes.length);
        out.write(bytes);
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
    
    @Override
    public String toString()
    {
        return "Tag "+name+" with value "+value.toString();
    }
    
}
