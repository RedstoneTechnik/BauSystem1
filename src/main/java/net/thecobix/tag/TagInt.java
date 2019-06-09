package net.thecobix.tag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TagInt extends Tag {

    public static final TagInt BASE_OBJECT = new TagInt("base", 0);
    
    public TagInt(String name, int value)
    {
        super(name);
        this.value = new Integer(value);
    }

    @Override
    protected byte[] write() throws IOException
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buffer);
        writeVarInt(dos, 0x02);
        writeString(dos, this.name, StandardCharsets.UTF_8);
        writeVarInt(dos, (int) this.value);
        return buffer.toByteArray();
    }

    @Override
    protected void read(byte[] data) throws IOException
    {
        ByteArrayInputStream buffer = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(buffer);
        if(readVarInt(dis) != 0x02)
            throw new IllegalStateException("Data is not representing the type TagInt");
        this.name = readString(dis);
        this.value = new Integer(readVarInt(dis));
    }

    public Integer getValue() {
        return (Integer) this.value;
    }
    
    public void setValue(int value) {
        this.value = new Integer(value);
    }
    
}
