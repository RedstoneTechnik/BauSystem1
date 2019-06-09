package net.thecobix.tag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TagByteArray extends Tag {
    
    public static final TagByteArray BASE_OBJECT = new TagByteArray("base", "base".getBytes());

    public TagByteArray(String name, byte[] data)
    {
        super(name);
        value = data;
    }

    @Override
    protected byte[] write() throws IOException
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buffer);
        writeVarInt(dos, 0x09);
        writeString(dos, this.name, StandardCharsets.UTF_8);
        writeVarInt(dos, getValue().length);
        dos.write(getValue());
        return buffer.toByteArray();
    }

    @Override
    protected void read(byte[] data) throws IOException
    {
        ByteArrayInputStream buffer = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(buffer);
        if(readVarInt(dis) != 0x09)
            throw new IllegalStateException("Data is not representing the type TagByteArray");
        this.name = readString(dis);
        setValue(readByteArray(dis));
    }

    @Override
    public byte[] getValue()
    {
        return (byte[]) super.getValue();
    }
    
    public void setValue(byte[] data) {
        this.value = data;
    }
    
}
