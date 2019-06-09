package net.thecobix.tag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

public class TagSerializeableObject extends Tag {
    
    public static final TagSerializeableObject BASE_OBJECT = new TagSerializeableObject("base", 0);

    public TagSerializeableObject(String name, Serializable obj)
    {
        super(name);
        this.value = obj;
    }

    @Override
    protected byte[] write() throws IOException
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buffer);
        writeVarInt(dos, 0x08);
        writeString(dos, this.name, StandardCharsets.UTF_8);
        byte[] ba = toByteArray((Serializable) this.value);
        writeVarInt(dos, ba.length);
        dos.write(ba);
        return buffer.toByteArray();
    }

    private byte[] toByteArray(Serializable obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        return bos.toByteArray();
    }
    
    private Object toObject(byte[] ba) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(ba);
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }
    
    @Override
    protected void read(byte[] data) throws IOException
    {
        ByteArrayInputStream buffer = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(buffer);
        if(readVarInt(dis) != 0x08)
            throw new IllegalStateException("Data is not representing the type TagSerializeableObject");
        this.name = readString(dis);
        byte[] ba = readByteArray(dis);
        try
        {
            this.value = toObject(ba);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void setValue(Serializable obj) {
        this.value = obj;
    }
    
}
