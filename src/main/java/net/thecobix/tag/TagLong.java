package net.thecobix.tag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class TagLong extends Tag {
    
    public static final TagLong BASE_OBJECT = new TagLong("base", 0);

    public TagLong(String name, long value)
    {
        super(name);
        this.value = new Long(value);
    }

    @Override
    protected byte[] write() throws IOException
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buffer);
        writeVarInt(dos, 0x04);
        writeString(dos, this.name, StandardCharsets.UTF_8);
        byte[] b = toByteArray((long) this.value);
        writeVarInt(dos, b.length);
        dos.write(b);
        return buffer.toByteArray();
    }

    private byte[] toByteArray(long l) {
        byte[] b = new byte[8];
        ByteBuffer.wrap(b).putLong(l);
        return b;
    }
    
    @Override
    protected void read(byte[] data) throws IOException
    {
        ByteArrayInputStream buffer = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(buffer);
        if(readVarInt(dis) != 0x04)
            throw new IllegalStateException("Data is not representing the type TagLong");
        this.name = readString(dis);
        byte[] ba = readByteArray(dis);
        this.value = new Long(toLong(ba));
    }
    
    private long toLong(byte[] ba)
    {
        return ByteBuffer.wrap(ba).getLong();
    }

    public Long getValue() {
        return (Long) this.value;
    }
    
    public void setValue(long value) {
        this.value = new Long(value);
    }

}
