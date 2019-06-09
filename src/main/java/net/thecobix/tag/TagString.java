package net.thecobix.tag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.ReadOnlyFileSystemException;

public class TagString extends Tag {
    
    public static final TagString BASE_OBJECT = new TagString("base", "base");
    
    public TagString(String name, String value)
    {
        super(name);
        this.value = value;
    }

    @Override
    protected byte[] write() throws IOException
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buffer);
        writeVarInt(dos, 0x01);
        writeString(dos, this.name, StandardCharsets.UTF_8);
        writeString(dos, (String) this.value, StandardCharsets.UTF_8);
        return buffer.toByteArray();
    }

    @Override
    protected void read(byte[] data) throws IOException
    {
        ByteArrayInputStream buffer = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(buffer);
        if(readVarInt(dis) != 0x01)
            throw new IllegalStateException("Data is not representing the type TagString");
        this.name = readString(dis);
        this.value = readString(dis);
    }
    
    public String getValue()
    {
        return (String) this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
}
